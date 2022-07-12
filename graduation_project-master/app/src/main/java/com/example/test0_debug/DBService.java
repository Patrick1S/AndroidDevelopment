package com.example.test0_debug;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import com.example.test0_debug.tools.httpHelper.xmlParse.PullParse;
import com.example.test0_debug.tools.db.DBHelper;
import com.example.test0_debug.tools.httpHelper.ApiAddress;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DBService extends Service {
    private static final String TAG = "myTAG";
    private SQLiteDatabase db;


    public DBService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper dbHelper = new DBHelper(this, "Users.db", null, 1);
        db = dbHelper.getReadableDatabase();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String sql;
        Cursor cursor;
        switch (intent.getIntExtra(DBHelper.SERVICE_SIGN, 0)) {
            case DBHelper.DOWNLOAD_EXAMPLE_SENTENCE:
                String class_id = intent.getStringExtra("class_id");
                sql = "select name from wordlist where class_id='" + class_id +
                        "'and example_sentence ='null';";
                cursor = db.rawQuery(sql, null);
                if (cursor.moveToFirst() && cursor.getCount() != 0) {
                    Log.d(TAG, "download example sentence");
                    final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(40, cursor.getCount(), 1, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnable>(100));
                    String word_name=cursor.getString(cursor.getColumnIndex("name"));

                    do {
                        try {
                            Thread.sleep(1000);
                        }catch (Exception ignored){}
                        Runnable runnable = () -> {
                            try {
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder().url(ApiAddress.getIcibaAddress(word_name)).build();
                                Response response = client.newCall(request).execute();
                                String example_sentence=PullParse.parseXmlWithPull(Objects.requireNonNull(response.body()).string(), true);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };
                        threadPoolExecutor.execute(runnable);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                stopSelf(startId);
                break;
            case DBHelper.SET_EBBINGHAUS_MEMORY:
                new Thread(() -> {
                    String sql1 = "select memory,word_id from wordlist where class_id='" +
                            intent.getStringExtra("class_id") + "' and memory!=0;";
                    Cursor cursor1 = db.rawQuery(sql1, null);
                    if (cursor1.moveToFirst()) {
                        do {
                            int word_id = cursor1.getInt(cursor1.getColumnIndex("word_id"));
                            DBHelper.setMemoryByEbbinghaus(getBaseContext(), word_id);
                        } while (cursor1.moveToNext());

                    }
                    cursor1.close();
                    stopSelf(startId);
                }).start();

                break;
            default:
                //stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}