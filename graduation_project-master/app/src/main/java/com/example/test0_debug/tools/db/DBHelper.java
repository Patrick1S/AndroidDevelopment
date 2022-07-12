package com.example.test0_debug.tools.db;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.test0_debug.tools.OtherTools;

public class DBHelper extends SQLiteOpenHelper {
    public static final int THEME_NIGHT = 1;
    public static final int THEME_LIGHT = 0;
    public final static int FORGETTING = 0;
    public final static int VAGUENESS = 1;
    public final static int KNOWING = 2;
    public final static int MEMORIZING = 3;
    public final static int FAVORITES = 1;
    public final static int NO_FAVORITES = 0;
    //服务请求常量
    public final static String SERVICE_SIGN = "sign";
    public final static int DOWNLOAD_EXAMPLE_SENTENCE = 1;
    public final static int SET_EBBINGHAUS_MEMORY = 2;
    private static final String TAG = "myTAG";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table logs("+
                "user_id integer default 1," +
                "user text ,"+
                "appkey text,"+
                "date_num integer default 19971010"+
        ");");
        db.execSQL("create table users(" +
                "user_id integer primary key autoincrement," +
                "user text," +
                "appkey text," +
                "class_id text," +
                "word_position integer default 0," +
                "word_limit integer default 100," +
                "today_progress integer default 1," +
                "date_num integer default 19971010," +
                "theme_night integer default 0" +
                ");");
        db.execSQL("insert into users(user,appkey,class_id) values ('11590'," +
                "'fd9a685a09cefb3c3833b6a4b1171392',null) ");
        db.execSQL("create table booklist(" +
                "msg text," +
                "title text," +
                "class_id text," +
                "word_num text," +
                "course_num text," +
                "existence_item integer" +
                ") ;");
        db.execSQL("create table wordlist(" +
                "word_id integer primary key autoincrement," +
                "msg text," +
                "class_id text," +
                "course_num text," +
                "symbol text,sound text," +
                "name text," +
                "class_title text," +
                "desc_ text," +
                "favorites integer default 0, " +
                "time_stamp integer default 1618674655870," +  //时间戳，默认值2021/04/18 00:00
                "memory integer default 0," +
                "word_operation integer default 0," +
                "example_sentence text default 'null'" +
                ") ;");
        //favorites
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    /*获取当前书名*/
    public static String getBookTitle(Context context, int user_id) {
        String bookTitle = "无";
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select title from booklist where class_id in(" +
                "select class_id from users where user_id='" + user_id + "') ;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            bookTitle = cursor.getString(cursor.getColumnIndex("title"));
        cursor.close();
        db.close();
        dbHelper.close();
        return bookTitle;
    }

    /**
     * 1.查询wordList数据表
     * 2.返回类型为void
     * 满足以上两个条件的方法都被安排在非主线程，以减少主线程卡顿
     */

    /*判断是否存在例句*/
    public static String getExampleSentence(Context context, int word_id) {
        String example_sentence = "null";
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select  example_sentence from wordlist where word_id='" + word_id + "';";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            example_sentence = cursor.getString(cursor.getColumnIndex("example_sentence"));
        cursor.close();
        db.close();
        dbHelper.close();
        return example_sentence;
    }

    /*例句存储*/
    public static void setExampleSentence(Context context, String example_sentence, int word_id) {
        new Thread(() -> {
            DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "update wordlist set example_sentence='" + example_sentence + "' where word_id=" + word_id + ";";
            db.execSQL(sql);
            db.close();
            dbHelper.close();
        }).start();
    }

    /*单词数目*/
    public static int getWordNum(Context context, String class_id) {
        int word_num = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_num from booklist where class_id='" + class_id + "';";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            word_num = Integer.parseInt(cursor.getString(cursor.getColumnIndex("word_num")));
        cursor.close();
        db.close();
        dbHelper.close();
        return word_num;
    }

    /*获取当前主题*/
    public static int getThemeNight(Context context, int user_id) {
        int theme = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select theme_night from users where user_id=" + user_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            theme = cursor.getInt(cursor.getColumnIndex("theme_night"));
        cursor.close();
        db.close();
        dbHelper.close();
        return theme;
    }

    /*设置黑夜主题*/
    public static void setThemeNight(Context context, int theme_night, int user_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update users set theme_night=" + theme_night + " where user_id=" + user_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*设置时间号, 若时间号改变则更新date_num并返回true, 此时将重置today_progress=1*******************/
    public static void setDateNum(Context context, int user_id) {
        int date_num = OtherTools.getDate();
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select date_num from users where user_id=" + user_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        boolean a = cursor.moveToFirst() &&
                (cursor.getInt(cursor.getColumnIndex("date_num")) != date_num);
        cursor.close();
        if (a) {
            db.execSQL("update users set date_num=" + date_num + " where user_id=" + user_id + ";");
            db.execSQL("update users set today_progress=1 where user_id=" + user_id + ";");
            db.close();
            dbHelper.close();
        }
    }

    /*设置收藏**********************/
    public static void setFavorites(Context context, int isFavorites, int word_id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String sql = "update wordlist set favorites=" + isFavorites + " where word_id=" + word_id + ";";
                db.execSQL(sql);
                db.close();
                dbHelper.close();
            }
        }).start();

    }

    /*获取收藏值*/
    public static int getFavorites(Context context, int word_id) {
        int favorites = DBHelper.NO_FAVORITES;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select favorites from wordlist where word_id=" + word_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            favorites = cursor.getInt(cursor.getColumnIndex("favorites"));
        cursor.close();
        db.close();
        dbHelper.close();
        return favorites;
    }

    /*users表重置，重置还应有bookList相关字段，但其暂未实现*/
    public static void reset(Context context, int user_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update users set today_progress=1,word_position=0 where user_id=" + user_id + ";";
        Log.d("myTAG", "reset: " + sql);
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*根据word_id记录wordList中的word_operation, 已在非主线程*/
    public static void setWordOperation(Context context, int word_operation, int word_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update wordlist set word_operation=" + word_operation + " where word_id=" + word_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*根据word_id获取wordList中的word_operation**************************/
    public static int getWordOperation(Context context, int word_id) {
        int word_operation = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_operation from wordlist where word_id=" + word_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            word_operation = cursor.getInt(cursor.getColumnIndex("word_operation"));
        cursor.close();
        db.close();
        dbHelper.close();
        return word_operation;
    }

    /*根据users中的user_id从bookList中获取书名****/
    public static String getTitle(Context context, int user_id) {
        String title = "";
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select title from booklist where class_id in(" +
                "select class_id from users where user_id=" + user_id + ");";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            title = cursor.getString(cursor.getColumnIndex("title"));
        cursor.close();
        db.close();
        dbHelper.close();
        return title;
    }

    /*设置users中today_progress值**********************/
    public static void setTodayProgress(Context context, int today_progress, int user_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update users set today_progress=" + today_progress + " where user_id=" + user_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*获取users中today_progress值 *****************************/
    public static int getTodayProgress(Context context, int user_id) {
        int todayProgress = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select today_progress from users where user_id=" + user_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            todayProgress = cursor.getInt(cursor.getColumnIndex("today_progress"));
        cursor.close();
        db.close();
        dbHelper.close();
        return todayProgress;
    }

    /*设置wordList中time_stamp值, 当前时间戳，已在非主线程*****/
    public static void setTimeStamp(Context context, long time_stamp, int word_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update wordlist set time_stamp=" + time_stamp + " where word_id=" + word_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*获取wordList中time_stamp值, 已在非主线程**************************/
    public static long getTimeStamp(Context context, int word_id) {

        long timeStamp = 1618674655869L;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select time_stamp from wordlist where word_id=" + word_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            timeStamp = cursor.getLong(cursor.getColumnIndex("time_stamp"));
        cursor.close();
        db.close();
        dbHelper.close();
        return timeStamp;
    }

    /*通过Ebbinghaus设置wordList.db中的记忆值,同时记录当前时间戳，已在非主线程***************************/
    public static void setMemoryByEbbinghaus(Context context, int word_id) {
        long currentTimeStamp = OtherTools.getCurrentTimeMillis();
        long relativeTimeStamp = currentTimeStamp - getTimeStamp(context, word_id);
        int memory = OtherTools.ebbinghaus(relativeTimeStamp);
        Log.d("myTAG", "word_id: " + word_id + "   memory: " + memory);
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update wordlist set memory=" + memory + " where word_id=" + word_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();

    }

    /*设置wordList.db中的记忆值，已在非主线程***************************/
    public static void setMemory(Context context, int memory, int word_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update wordlist set memory=" + memory + " where word_id=" + word_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*获取wordList.db中的记忆值，已在非主线程***************************/
    public static int getMemory(Context context, int word_id) {
        int memory = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select memory from wordlist where word_id=" + word_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            memory = cursor.getInt(cursor.getColumnIndex("memory"));
        cursor.close();
        db.close();
        dbHelper.close();
        return memory;
    }

    /*设置users中word_limit值**************************/
    public static void setWordLimit(Context context, int wordLimit, int user_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update users set word_limit=" + wordLimit + " where user_id=" + user_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*获取users中word_limit值**************************/
    public static int getWordLimit(Context context, int user_id) {
        int wordLimit = 100;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_limit from users where user_id=" + user_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            wordLimit = cursor.getInt(cursor.getColumnIndex("word_limit"));
        cursor.close();
        db.close();
        dbHelper.close();
        return wordLimit;
    }

    /*记录本次wordList中所记单词位置**************************/
    public static void setWordPosition(Context context, int word_position, int user_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update users set word_position=" + word_position + " where user_id=" + user_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*获取本次wordList中所记单词位置*************************/
    public static int getWordPosition(Context context, int user_id) {
        int wordPosition = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_position from users where user_id=" + user_id + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            wordPosition = cursor.getInt(cursor.getColumnIndex("word_position"));
        cursor.close();
        db.close();
        dbHelper.close();
        return wordPosition;
    }


    /*通过userId读取用户当前Book，book名使用class_id表示*/
    public static String getBookClassId(Context context, int userId) {
        String bookClassId = "";
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select class_id from users where user_id=" + userId + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            bookClassId = cursor.getString(cursor.getColumnIndex("class_id"));
        cursor.close();
        db.close();
        dbHelper.close();
        return bookClassId;
    }

    /*通过userId设置用户当前Book，book名使用class_id表示*/
    public static void setBookClassId(Context context, int userId, String class_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update users set class_id='" + class_id + "' where user_id=" + userId + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*判断bookList表中是否已存在对应数据*********/
    public static boolean getExistence_item(Context context, String class_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select existence_item from booklist where class_id='" + class_id + "';";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() && cursor.getCount() != 0) {
            if (cursor.getInt(cursor.getColumnIndex("existence_item")) == 1) {
                cursor.close();
                db.close();
                dbHelper.close();
                return true;
            }
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return false;
    }

    /*设置bookList.db中是否已存在对应数据*********/
    public static void setExistence_item(Context context, String class_id, int existence_item) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "update booklist set existence_item=" + existence_item + " where class_id=" + class_id + ";";
        db.execSQL(sql);
        db.close();
        dbHelper.close();
    }

    /*sqlite字符转义*/
    public static String sqliteEscape(String keyWord) {
        keyWord = keyWord.replace("/", "//");
        keyWord = keyWord.replace("'", "''");
        keyWord = keyWord.replace("[", "/[");
        keyWord = keyWord.replace("]", "/]");
        keyWord = keyWord.replace("%", "/%");
        keyWord = keyWord.replace("&", "/&");
        keyWord = keyWord.replace("_", "/_");
        keyWord = keyWord.replace("(", "/(");
        keyWord = keyWord.replace(")", "/)");
        return keyWord;
    }
}
