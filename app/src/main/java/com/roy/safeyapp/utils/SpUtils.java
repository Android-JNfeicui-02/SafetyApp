package com.roy.safeyapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.roy.safeyapp.ui.HomeActivity;

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


    /**
     * 获得一个String类型的数据, 如果没有,则返回null
     * @param context
     *  上下文
     * @param key
     *  sp里的key
     * @return 拿到返回的结果
     */
    public static String getString(Context context, String key) {

        return getString(context,key, null);
    }

    /**
     * 获得String类型的数据
     * @param context
     *  上下文
     * @param key
     *  sp的key
     * @param defValue
     *  sp里的value
     * @return
     */
    private static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = getPreferences(context);

        return sp.getString(key,defValue);
    }
}
