package com.roy.safeyapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 全局的SharedPreferences的工具类
 * Created by Roy Sun on 2016/5/16.
 */
public class SpUtils {

    public static final String NAME = "safetyApp";
    private static SharedPreferences mPreferences;

    public static void putString(Context context, String key, String value) {

        SharedPreferences sp = getPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

}
