package com.example.test0_debug.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OtherTools {
    public final static long ONE_DAY_MILLIS = 86400000L;
    public final static long TWO_DAY_MILLIS = 172800000L;
    public final static long SIX_DAY_MILLIS = 518400000L;
    public final static long THIRTY_DAY_MILLIS = 2592000000L;
    public final static long TWENTY_MIN_MILLIS = 1200000L;
    public final static long FIFTEEN_MIN_MILLIS = 900000L;
    public final static long EIGHT_HOUR_MILLIS = 28800000L;
    public final static long ONE_HOUR_MILLIS = 3600000L;

    public final static int EXISTENCE = 1;
    public final static int NONEXISTENCE = 0;

    /*判断单词是否出现被安排在在下一个，击中率为(100-memory)/100*/
    public static boolean random_hit(int memory) {
        if (memory < 0)
            memory = 0;
        int num = (int) (Math.random() * 101);
        return num <= 100 - memory;
    }

    /*获取当前时间戳  ***********************/
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /*艾宾浩斯 ****************************/
    public static int ebbinghaus(long relativeTimeStamp) {
        if (relativeTimeStamp > THIRTY_DAY_MILLIS)
            return 15;
        else if (relativeTimeStamp > SIX_DAY_MILLIS)
            return 21;
        else if (relativeTimeStamp > TWO_DAY_MILLIS)
            return 25;
        else if (relativeTimeStamp > ONE_DAY_MILLIS)
            return 28;
        else if (relativeTimeStamp > EIGHT_HOUR_MILLIS)
            return 34;
        else if (relativeTimeStamp > ONE_HOUR_MILLIS)
            return 36;
        else if (relativeTimeStamp > TWENTY_MIN_MILLIS)
            return 44;
        else if (relativeTimeStamp > FIFTEEN_MIN_MILLIS)
            return 58;
        else
            return 100;
    }

    /*获取当前时间*********/
    public static int getDate() {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        return Integer.parseInt(simpleDateFormat.format(date));
    }
    public static boolean getNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    /*获取登陆状态*/
    public static boolean getLoginStatus(Context context){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean("login_status",false);
    }
    /*存储登陆状态*/
    public static void setLoginStatus(Context context,boolean loginStatus){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean("login_status",loginStatus);
        editor.apply();
    }
}
