package com.example.test0_debug.tools.httpHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.test0_debug.tools.db.DBHelper;


public class ApiAddress {
    private String appKey ="";
    private String uid="";

    public ApiAddress(Context context) {
        DBHelper dbHelper = new DBHelper(context, "Users.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users;", null);
        if (cursor.getCount() != 0&&cursor.moveToFirst()) {
            appKey = cursor.getString(cursor.getColumnIndex("appkey"));
            uid = cursor.getString(cursor.getColumnIndex("user"));
        }else {
            Toast.makeText(context, "初始化失败，缺少用户参数", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    public String getAppkey() {
        return appKey;
    }

    public String getUid() {
        return uid;
    }
    public String getBookListAddress(String name){

        return "https://rw.ylapi.cn/reciteword/list.u?uid="+uid+"&appkey="+ appKey +"&name="+name;
    }
    public String getBookListAddress(){

        return "http://rw.ylapi.cn/reciteword/list.u?uid="+uid+"&appkey="+ appKey;
    }
    public String getWordListAddress(String class_id,String course){
        return "http://rw.ylapi.cn/reciteword/wordlist.u?uid="+uid+"&appkey="+ appKey
                +"&class_id="+class_id+"&course="+course;
    }
    /*爱词霸API*/
    public static String getIcibaAddress(String word){
        return "http://dict-co.iciba.com/api/dictionary.php?w="+word
                +"&key=0EAE08A016D6688F64AB3EBB2337BFB0";
    }
}
