package com.car.notver.weight;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {
    public static String getPrefString(Context context, String key, final String defaultValue) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, final String key, final String value) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        settings.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean(Context context, final String key, final boolean defaultValue) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }



    public static boolean setPrefBoolean(Context context, final String key, final boolean value) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        settings.edit().putBoolean(key, value).commit();
        return value;
    }

    public static void setPrefInt(Context context, final String key, final int value) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        settings.edit().putInt(key, value).commit();
    }

    public static int getPrefInt(Context context, final String key, final int defaultValue) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, final String key, final float value) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        settings.edit().putFloat(key, value).commit();
    }

    public static float getPrefFloat(Context context, final String key, final float defaultValue) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, final String key, final long value) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        settings.edit().putLong(key, value).commit();
    }

    public static long getPrefLong(Context context, final String key, final long defaultValue) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    public static void clearPreference(Context context, final SharedPreferences p) {
        final Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }

    public static void removePerference(Context context, final String key) {
        final SharedPreferences settings =context.getSharedPreferences("north", Context.MODE_PRIVATE);
        settings.edit().remove(key).commit();
    }

}
