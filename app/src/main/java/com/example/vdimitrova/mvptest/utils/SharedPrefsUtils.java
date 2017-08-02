package com.example.vdimitrova.mvptest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vdimitrova on 18.05.17.
 */

public class SharedPrefsUtils {
    //shared prefs name
    private static final String NAME_PREF = "mvp_prefs";

    /**
     * Method for getting boolean value from prefs
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBooleanFromPrefs(Context context, String key) {
        return getSharedPrefs(context).getBoolean(key, false);
    }

    /**
     * Method for setting boolean value from prefs
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setBooleanToPrefs(Context context, String key, boolean value) {
        getSharedPrefs(context).edit().putBoolean(key, value).apply();
    }

    /**
     * Method for setting string value from prefs
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setStringToPrefs(Context context, String key, String value) {
        getSharedPrefs(context).edit().putString(key, value).apply();
    }


    /**
     * Method for getting string value from prefs
     *
     * @param context
     * @param key
     * @return
     */
    public static String getStringFromPrefs(Context context, String key) {
        return getSharedPrefs(context).getString(key, "");
    }

    /**
     * Method for getting string value from prefs with default value
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getStringFromPrefs(Context context, String key, String defaultValue) {
        return getSharedPrefs(context).getString(key, defaultValue);
    }

    /**
     * Method for setting long value from prefs
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setLongToPrefs(Context context, String key, long value) {
        getSharedPrefs(context).edit().putLong(key, value).apply();
    }

    /**
     * Method for getting long value from prefs
     *
     * @param context
     * @param key
     * @return
     */
    public static long getLongFromPrefs(Context context, String key) {
        return getSharedPrefs(context).getLong(key, 0);
    }

    /**
     * Method for setting int value from prefs
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setIntToPrefs(Context context, String key, int value) {
        getSharedPrefs(context).edit().putInt(key, value).apply();
    }

    /**
     * Method for getting int value from prefs
     *
     * @param context
     * @param key
     * @return
     */
    public static int getIntFromPrefs(Context context, String key) {
        return getSharedPrefs(context).getInt(key, 0);
    }

    private static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
    }
}
