package com.example.test0_debug.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test0_debug.R;
import com.example.test0_debug.word_list.WordListData;
import com.example.test0_debug.tools.OtherTools;
import com.example.test0_debug.tools.httpHelper.xmlParse.PullParse;
import com.example.test0_debug.tools.db.DBHelper;
import com.example.test0_debug.tools.httpHelper.ApiAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReviewFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "myTAG";
    private final List<WordListData> wordDataList = new ArrayList<>();
    private final List<WordListData> hitDataList = new ArrayList<>();
    private int wordLimit = 0;
    private int wordProgress = 1;
    private int wordPosition = 0;
    private int wordId = 1;
    private boolean isFromHitDataList = false;
    private TextView show_name;
    private TextView show_symbol;
    private TextView show_desc;
    private TextView task_view;
    private TextView show_exa_sentence;
    private MediaPlayer mediaPlayer;
    private Button knowing;
    private Button vagueness;
    private Button memorizing;
    private Button forgetting;
    private Context context;
    private boolean isCreated = false;
    private boolean show_exa_sentenceIsClickable = true;//解决点击与滑动冲突

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreated) {
            // 如果换书则重新加载数据
            boolean a = wordDataList.isEmpty() || !wordDataList.get(0).getClass_id().
                    equals(DBHelper.getBookClassId(context, 1));
            if (a) {
                initData(true);
            } else if (wordLimit < DBHelper.getWordLimit(context, 1)) {
                //没有换书但是单词上限被扩大，则wordDataList将追加所扩展的数据
                int start = wordPosition + wordLimit;
                int end = DBHelper.getWordLimit(context, 1);
                String class_id = DBHelper.getBookClassId(context, 1);
                wordProgress = DBHelper.getTodayProgress(context, 1);
                wordLimit = DBHelper.getWordLimit(context, 1);
                displayTaskInfo(wordProgress, wordLimit);

                new Thread(() -> {
                    DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    String sql = "select * from wordlist where class_id='" + class_id
                            + "' limit " + start + "," + end + " ;";
                    Cursor cursor = db.rawQuery(sql, null);
                    if (cursor.moveToFirst()) {
                        do {
                            addWordDataList(cursor);
                        } while (cursor.moveToNext());
                        cursor.close();
                        db.close();
                        dbHelper.close();
                    }
                }).start();

            } else if (wordLimit > DBHelper.getWordLimit(context, 1)) {
                wordProgress = DBHelper.getTodayProgress(context, 1);
                wordLimit = DBHelper.getWordLimit(context, 1);
                displayTaskInfo(wordProgress, wordLimit);
            }
        } else if (!isVisibleToUser && isCreated) {
            wordPosition = wordId - 1;
            DBHelper.setWordPosition(context, wordPosition, 1);
            DBHelper.setTodayProgress(context, wordProgress, 1);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
        isCreated = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.review_fragment, container, false);
        initView(v);
        initData(false);

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + wordProgress);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        /*碎片被销毁时存储wordProgress和wordLimit*/
        DBHelper.setTodayProgress(context, wordProgress, 1);
        DBHelper.setWordLimit(context, wordLimit, 1);
    }

    private void initView(View v) {
        show_name = v.findViewById(R.id.show_name);
        show_symbol = v.findViewById(R.id.show_symbol);
        show_desc = v.findViewById(R.id.show_desc);
        task_view = v.findViewById(R.id.task_view);
        show_exa_sentence = v.findViewById(R.id.example_sentence);
        show_exa_sentence.setMovementMethod(ScrollingMovementMethod.getInstance());
        show_exa_sentence.setOnClickListener(this);
        memorizing = v.findViewById(R.id.memorizing);
        knowing = v.findViewById(R.id.knowing);
        vagueness = v.findViewById(R.id.vagueness);
        forgetting = v.findViewById(R.id.forgetting);
        Button sound = v.findViewById(R.id.sound);
        Button favorites = v.findViewById(R.id.favorites);
        favorites.setOnClickListener(this);
        memorizing.setOnClickListener(this);
        knowing.setOnClickListener(this);
        vagueness.setOnClickListener(this);
        sound.setOnClickListener(this);
        forgetting.setOnClickListener(this);
        show_desc.setOnClickListener(this);
        buttonOperation(false);
    }

    /*初始化wordPosition********************/
    private void initData(boolean replaced) {
        if (!replaced)
            initWordDataList();//上一次的复习内容的再复习
        else {
            wordDataList.clear();//更换书本时将wordDataList清空
            hitDataList.clear();
        }
        wordPosition = DBHelper.getWordPosition(context, 1);
        wordProgress = DBHelper.getTodayProgress(context, 1);
        wordLimit = DBHelper.getWordLimit(context, 1);
        if (wordPosition >= wordLimit)
            wordPosition = wordLimit - 1;//至少显示一条数据
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from wordlist where class_id='" + DBHelper.getBookClassId(context, 1)
                + "' limit " + wordPosition + "," + wordLimit + " ;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            wordLimit = Math.min(wordLimit, cursor.getCount());
            wordId = cursor.getInt(cursor.getColumnIndex("word_id"));
            show_exa_sentenceIsClickable = true;
            show_exa_sentence.setText("点击显示释义");
            show_exa_sentence.setGravity(Gravity.CENTER);
            show_name.setText(cursor.getString(cursor.getColumnIndex("name")));
            show_symbol.setText(cursor.getString(cursor.getColumnIndex("symbol")));
            mediaPlayer = MediaPlayer.create(context, Uri.parse(
                    cursor.getString(cursor.getColumnIndex("sound"))));
            displayTaskInfo(wordProgress, wordLimit);
            new Thread(() -> {
                do {
                    addWordDataList(cursor);
                } while (cursor.moveToNext());
                cursor.close();
                db.close();
                dbHelper.close();
            }).start();
        } else {
            show_exa_sentence.setText("请前往词本选书");
            show_exa_sentence.setGravity(Gravity.CENTER);
            show_exa_sentenceIsClickable = false;
        }
    }

    @SuppressLint({"NonConstantResourceId", "RtlHardcoded"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sound:
                if (wordDataList.size() != 0) {
                    mediaPlayer.start();
                } else {
                    Toast.makeText(context, "发音初始化失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.example_sentence:
                if (show_exa_sentenceIsClickable) {
                    buttonOperation(wordDataList.size() != 0);
                    show_exa_sentence.setGravity(Gravity.LEFT);
                    if (isFromHitDataList && hitDataList.size() != 0) {
                        show_desc.setText(hitDataList.get(0).getDesc());
                        if (hitDataList.get(0).getExampleSentence().equals("null")) {
                            new MyAsyncTask().execute(ApiAddress.getIcibaAddress(hitDataList.get(0).getName()));
                        } else {
                            show_exa_sentence.setText(DBHelper.getExampleSentence(context, hitDataList.get(0).getWordId()));
                        }
                    } else {
                        show_desc.setText(wordDataList.get(0).getDesc());
                        if (wordDataList.get(0).getExampleSentence().equals("null")) {
                            new MyAsyncTask().execute(ApiAddress.getIcibaAddress(wordDataList.get(0).getName()));
                        } else {
                            show_exa_sentence.setText(DBHelper.getExampleSentence(context, wordDataList.get(0).getWordId()));
                        }
                    }
                }
                break;
            case R.id.memorizing:
                if (wordDataList.size() != 0)
                    wordOperation(100, DBHelper.MEMORIZING);
                break;
            case R.id.knowing:
                if (wordDataList.size() != 0)
                    wordOperation(80, DBHelper.KNOWING);
                break;
            case R.id.vagueness:
                if (wordDataList.size() != 0)
                    wordOperation(60, DBHelper.VAGUENESS);
                break;
            case R.id.forgetting:
                if (wordDataList.size() != 0)
                    wordOperation(40, DBHelper.FORGETTING);
                break;
            case R.id.favorites:
                if (hitDataList.size() != 0 && wordDataList.size() != 0) {
                    Toast.makeText(context, "无单词数据", Toast.LENGTH_SHORT).show();
                    break;
                }
                DBHelper.setFavorites(context, DBHelper.FAVORITES, wordId);
                Toast.makeText(context, "已收藏", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void wordOperation(int possibility, int word_operation) {
        buttonOperation(false);
        Log.d(TAG, "wordOperation: " + wordId);
        if (wordProgress < wordLimit) {
            new Thread(() -> {
                DBHelper.setTimeStamp(context, OtherTools.getCurrentTimeMillis(), wordId);
                DBHelper.setMemory(context, 100, wordId);
                DBHelper.setWordOperation(context, word_operation, wordId);
                if (OtherTools.random_hit(possibility)) {
                    hitDataList.add(wordDataList.get(0));
                }
            }).start();
            prepareNextData();
        } else if (wordProgress == wordLimit) {
            Toast.makeText(context, "任务已完成", Toast.LENGTH_SHORT).show();
            wordDataList.clear();
            hitDataList.clear();
        }
    }

    /* 1.下一个单词有50%的概率展示的是来自hitDataList的第一个数据；
     * 2.展示完成后需要从hitDataList中移除该单词；
     * 3.以上操作首先要保证hitDataList非空，否则isFromHitDataList永远是false；
     * 4.显示任务进度，当word只来自WordDataList时，wordProgress产生自增；
     * 5.初始化下一项数据的 MediaPlayer；
     * 6.记录wordDataList位置，wordPosition；   */
    private void prepareNextData() {
        if (hitDataList.size() != 0)
            isFromHitDataList = OtherTools.random_hit(50);
        if (isFromHitDataList && hitDataList.size() != 0) {
            Log.d("myTAG", "showNextData: word is From hitDataList");
            wordId = hitDataList.get(0).getWordId();
            show_name.setText(hitDataList.get(0).getName());
            show_symbol.setText(hitDataList.get(0).getSymbol());
            mediaPlayer = MediaPlayer.create(context, Uri.parse(hitDataList.get(0).getSound()));
            show_desc.setText(null);
            show_exa_sentence.setText("点击显示释义");
            show_exa_sentence.setGravity(Gravity.CENTER);
        } else {//isFromWordDataList
            if (wordDataList.size() != 0) {
                Log.d("myTAG", "showNextData: word is From wordDataList");
                wordId = wordDataList.get(0).getWordId();
                show_name.setText(wordDataList.get(0).getName());
                show_symbol.setText(wordDataList.get(0).getSymbol());
                show_desc.setText(null);
                show_exa_sentence.setText("点击显示释义");
                show_exa_sentence.setGravity(Gravity.CENTER);
                mediaPlayer = MediaPlayer.create(context, Uri.parse(wordDataList.get(0).getSound()));
                displayTaskInfo(++wordProgress, wordLimit);
            } else if (hitDataList.size() != 0) {
                Log.d("myTAG", "showNextData: word is From hitDataList");
                wordId = hitDataList.get(0).getWordId();
                show_name.setText(hitDataList.get(0).getName());
                show_symbol.setText(hitDataList.get(0).getSymbol());
                mediaPlayer = MediaPlayer.create(context, Uri.parse(hitDataList.get(0).getSound()));
                show_desc.setText(null);
                show_exa_sentence.setText("点击显示释义");
                show_exa_sentence.setGravity(Gravity.CENTER);
            } else {
                Toast.makeText(context, "任务已完成", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addWordDataList(Cursor cursor) {
        WordListData wordListData = new WordListData();
        wordListData.setWordId(cursor.getInt(cursor.getColumnIndex("word_id")));
        wordListData.setDesc(cursor.getString(cursor.getColumnIndex("desc_")));
        wordListData.setName(cursor.getString(cursor.getColumnIndex("name")));
        wordListData.setSymbol(cursor.getString(cursor.getColumnIndex("symbol")));
        wordListData.setMsg(cursor.getString(cursor.getColumnIndex("msg")));
        wordListData.setClass_id(cursor.getString(cursor.getColumnIndex("class_id")));
        wordListData.setSound(cursor.getString(cursor.getColumnIndex("sound")));
        wordListData.setMemory(cursor.getInt(cursor.getColumnIndex("memory")));
        wordListData.setExampleSentence(cursor.getString(cursor.getColumnIndex("example_sentence")));
        wordListData.setFavorites(cursor.getInt(cursor.getColumnIndex("favorites")));
        wordDataList.add(wordListData);
    }

    private void initWordDataList() {
        new Thread(() -> {
            DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "select * from wordlist where class_id='" + DBHelper.getBookClassId(context, 1)
                    + "' and memory<40 and word_operation!=" + DBHelper.MEMORIZING + " limit " + wordPosition + " ;";
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst() && cursor.getCount() != 0) {
                do {
                    int memory = DBHelper.getMemory(context, cursor.getInt(cursor.getColumnIndex("word_id")));
                    if (OtherTools.random_hit(memory)) {
                        addWordDataList(cursor);
                    }
                } while (cursor.moveToNext());

            }
            cursor.close();
            db.close();
            dbHelper.close();
            wordId = wordDataList.isEmpty() ? 0 : wordDataList.get(0).getWordId();
        }).start();
    }

    private void displayTaskInfo(int progress, int wordLimit) {
        if (progress > wordLimit)
            progress = wordLimit;
        String info = progress + " / " + wordLimit;
        task_view.setText(info);

    }

    private void buttonOperation(boolean visibility) {

        if (visibility) {
            show_exa_sentenceIsClickable = false;//解决冲突
            show_desc.setClickable(false);
            //show_exa_sentence.setClickable(false);//不起作用
            memorizing.setVisibility(View.VISIBLE);
            knowing.setVisibility(View.VISIBLE);
            vagueness.setVisibility(View.VISIBLE);
            forgetting.setVisibility(View.VISIBLE);
        } else {
            show_exa_sentenceIsClickable = true;
            show_desc.setClickable(true);
            //show_exa_sentence.setClickable(true);
            memorizing.setVisibility(View.INVISIBLE);
            knowing.setVisibility(View.INVISIBLE);
            vagueness.setVisibility(View.INVISIBLE);
            forgetting.setVisibility(View.INVISIBLE);
            if (hitDataList.size() != 0 && isFromHitDataList) {
                Log.d(TAG, "hitDataList " + hitDataList.get(0).getName() + " is removed");
                hitDataList.remove(0);
            } else if (wordDataList.size() != 0) {
                Log.d(TAG, "wordDataList " + wordDataList.get(0).getName() + " is removed");
                wordDataList.remove(0);
            }
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {
        public MyAsyncTask() {
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(params[0]).build();
            try {
                Response response = client.newCall(request).execute();
                // publishProgress(++i);
                return Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                return e.getMessage();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //    更新ProgressDialog的进度条

        }

        @Override
        protected void onPostExecute(String params) {
            String text = "null";
            if (params != null) {
                try {
                    text = PullParse.parseXmlWithPull(params, true);
                    DBHelper.setExampleSentence(context, text, wordId);
                } catch (Exception e) {
                    Log.d(TAG, "onPostExecute: " + text);
                }
                show_exa_sentence.setText(text);

            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("myTAG", "onCancelled: ");
        }

    }

}
