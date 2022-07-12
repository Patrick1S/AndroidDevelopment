package com.example.test0_debug.book_list;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test0_debug.R;

import com.example.test0_debug.tools.httpHelper.jsonParse.WordList;
import com.example.test0_debug.tools.db.DBHelper;
import com.example.test0_debug.tools.httpHelper.ApiAddress;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    private static final String TAG = "myTAG";
    private final List<BookListDatas> mHistoryList;
    private final Context context;
    private final TextView textView;
    private final DBHelper dbHelper;
    private boolean isUpdated = false;

    public BookListAdapter(Context context, List<BookListDatas> mHistoryList, TextView textView) {
        this.mHistoryList = mHistoryList;
        this.context = context;
        this.textView = textView;
        dbHelper = new DBHelper(context, "Users.db", null, 1);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.recordView.setOnClickListener(v -> {
        });
        holder.content.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            BookListDatas bookListDatas = mHistoryList.get(position);
            //如果更换了书本则会重置今日进度，书本名，书本位置
            if (!bookListDatas.getClass_id().equals(DBHelper.getBookClassId(context, 1))) {
                DBHelper.setBookClassId(context, 1, bookListDatas.getClass_id());
                DBHelper.setTodayProgress(context, 1, 1);
                DBHelper.setWordPosition(context, 0, 1);
                textView.setText(bookListDatas.getTitle());
            }
            /*if it have had word data in the database, we load it directly. if not, we get data from api*/
            if (!DBHelper.getExistence_item(context, bookListDatas.getClass_id())) {
                isUpdated = false;
                for (int i = 1; i <= Integer.parseInt(bookListDatas.getCourse_num()); i++) {
                    new MyAsyncTask(bookListDatas.getClass_id()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            new ApiAddress(context).getWordListAddress(bookListDatas.getClass_id(), i + ""));
                }
            }
            /*Intent intent = new Intent(context, WordListActivity.class);
            intent.putExtra("class_id", bookListDatas.getClass_id());
            intent.putExtra("course_num",bookListDatas.getCourse_num());
            context.startActivity(intent);*/
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookListDatas bookListDatas = mHistoryList.get(position);
        holder.content.setText(bookListDatas.getTitle());
    }

    @Override
    public int getItemCount() {
        return mHistoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        View recordView;

        public ViewHolder(View itemView) {
            super(itemView);
            recordView = itemView;
            content = itemView.findViewById(R.id.record_content);
        }
    }

    /*leak field problem have not solved*/
    private class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private final String class_id;

        public MyAsyncTask(String class_id) {
            this.class_id = class_id;
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
                //publishProgress(++i);
                return Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("myTAG", Arrays.toString(values) + " is downloading... ");

        }

        @Override
        protected void onPostExecute(String params) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Gson gson = new Gson();
            WordList wordList;
            wordList = gson.fromJson(params, WordList.class);
            ArrayList<com.example.test0_debug.tools.httpHelper.jsonParse.WordListData> dataList_word = wordList.getDatas();
            StringBuilder sql = new StringBuilder(
                    "insert into wordlist (msg,class_id,course_num,symbol,sound,name,class_title,desc_) values ");
            for (int i = 0; i < dataList_word.size(); i++) {
                sql.append("('").append(wordList.getMsg()).append("',").
                        append("'").append(wordList.getDatas().get(i).getClass_id()).append("',").
                        append("'").append(wordList.getDatas().get(i).getCourse()).append("',").
                        append("'").append(wordList.getDatas().get(i).getSymbol()).append("',").
                        append("'").append(wordList.getDatas().get(i).getSound()).append("',").
                        append("'").append(wordList.getDatas().get(i).getName()).append("',").
                        append("'").append(wordList.getDatas().get(i).getClass_title()).append("',").
                        append("'").append(wordList.getDatas().get(i).getDesc()).append("'),");
            }
            sql.deleteCharAt(sql.length() - 1).append(";");
            db.execSQL(sql.toString());
            if (!isUpdated) {
                db.execSQL("update booklist set existence_item=1 where class_id=" + class_id + ";");
                textView.setText(wordList.getDatas().get(0).getClass_title());
                isUpdated = true;
                Log.d("myTAG", class_id + " of bookList is updating ");
            }
            db.close();
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            if (dbHelper != null)
                dbHelper.close();
        }
    }


}


