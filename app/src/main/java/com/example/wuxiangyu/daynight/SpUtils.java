package com.example.wuxiangyu.daynight;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wuxiangyu.MyApp;

public class SpUtils {
    private static final String SETTING_SP = "setting";
    static SharedPreferences sp = MyApp.Companion.getInstance().getSharedPreferences(SETTING_SP, Context.MODE_PRIVATE);
    public static void setDayNight(boolean isNight) {
        sp.edit().putBoolean("dayNight", isNight).apply();
    }

    public static boolean getDayNight() {
        return sp.getBoolean("dayNight", false);
    }
}
