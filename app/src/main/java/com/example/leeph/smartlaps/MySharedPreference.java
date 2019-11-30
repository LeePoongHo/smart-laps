package com.example.leeph.smartlaps;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreference {
    private static MySharedPreference ourInstance = new MySharedPreference();
    private static String SharePrefereancesName = "smartlaps";


    public static MySharedPreference getInstance() {
        if (ourInstance == null)
            ourInstance = new MySharedPreference();

        return ourInstance;
    }

    private MySharedPreference() {
    }

    /**
     * 값 가져오기
     *
     * @param context
     * @param key
     */
    public String getPreferences(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SharePrefereancesName, MODE_PRIVATE);
        return pref.getString(key, "");
    }

    /**
     * 값 저장하기
     *
     * @param context
     * @param key
     * @param value
     */
    public void setPreferences(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(SharePrefereancesName, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 값 삭제하기
     *
     * @param context
     * @param key
     */
    public void removePreferences(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(SharePrefereancesName, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 값 전부 삭제하기
     *
     * @param context
     */
    public void removeAllPreferences(Context context) {
        SharedPreferences pref = context.getSharedPreferences(SharePrefereancesName, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}

