package com.example.test0_debug.tools.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.text.DecimalFormat;

public class DBStatistics {
    /*获取本书收藏数*/
    public static int getFavoritesWordNum(Context context) {//favorites
        int word_num = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where class_id='" +
                DBHelper.getBookClassId(context, 1) + "' and favorites=1;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor!=null) {
            word_num = cursor.getCount();
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return word_num;
    }
    /*获取该单词本所有单词数*/
    public static int getTotalWordNum(Context context) {
        int word_num = 0;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_num from booklist where class_id='" +
                DBHelper.getBookClassId(context, 1) + "';";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            word_num = Integer.parseInt(cursor.getString(cursor.getColumnIndex("word_num")));
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return word_num;
    }

    /*本日剩余*/
    public static int getRestWordNum(Context context) {
        return DBHelper.getWordLimit(context, 1) - DBHelper.getTodayProgress(context, 1) + 1;

    }

    /*已学*/
    public static int getLearnedStatistic(Context context) {
        int count;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where class_id='" +
                DBHelper.getBookClassId(context, 1) + "' and  memory>0;";
        Cursor cursor = db.rawQuery(sql, null);
        count = cursor.getCount();
        cursor.close();
        db.close();
        dbHelper.close();
        return count;
    }

    /*书本进度*/
    public static double getProgressStatistics(Context context, String class_id) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where class_id='" + class_id + "' and memory!=0;";
        Cursor cursor = db.rawQuery(sql, null);
        int learned = cursor.getCount();
        sql = "select word_num from booklist where class_id='" + class_id + "';";
        cursor = db.rawQuery(sql, null);
        int total = Integer.parseInt(cursor.getString(cursor.getColumnIndex("word_num")));
        cursor.close();
        db.close();
        dbHelper.close();
        return (double) learned / total;
    }

    public static String getProgressStatistics(Context context) {
        String class_id = DBHelper.getBookClassId(context, 1);
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where class_id='" + class_id + "' and memory!=0;";
        Cursor cursor = db.rawQuery(sql, null);
        int learned = cursor.getCount();
        sql = "select word_num from booklist where class_id='" + class_id + "';";
        cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int total = Integer.parseInt(cursor.getString(cursor.getColumnIndex("word_num")));
            cursor.close();
            db.close();
            dbHelper.close();
            double a = (double) learned / total*100;
            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(a);
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return "0";
    }

    /*熟悉程度单词量*/
    public static int getMemorizingStatistic(Context context) {
        int count;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where word_operation=" + DBHelper.MEMORIZING + ";";
        Cursor cursor = db.rawQuery(sql, null);
        count = cursor.getCount();
        cursor.close();
        db.close();
        dbHelper.close();
        return count;
    }

    /*知道程度单词量*/
    public static int getKnowingStatistic(Context context) {
        int count;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where word_operation=" + DBHelper.KNOWING + ";";
        Cursor cursor = db.rawQuery(sql, null);
        count = cursor.getCount();
        cursor.close();
        db.close();
        dbHelper.close();
        return count;
    }

    /*模糊程度单词量*/
    public static int getVaguenessStatistic(Context context) {
        int count;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where word_operation=" + DBHelper.VAGUENESS + ";";
        Cursor cursor = db.rawQuery(sql, null);
        count = cursor.getCount();
        cursor.close();
        db.close();
        dbHelper.close();
        return count;
    }

    /*遗忘程度单词量*/
    public static int getForgettingStatistic(Context context) {
        int count;
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select word_id from wordlist where memory!=0 and word_operation=" + DBHelper.FORGETTING + ";";
        Cursor cursor = db.rawQuery(sql, null);
        count = cursor.getCount();
        cursor.close();
        db.close();
        dbHelper.close();
        return count;
    }
}
