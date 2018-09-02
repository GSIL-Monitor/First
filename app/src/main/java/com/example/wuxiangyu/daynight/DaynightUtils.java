package com.example.wuxiangyu.daynight;

import android.app.Activity;

import com.example.wuxiangyu.MyApp;
import com.example.wuxiangyu.first.R;

public class DaynightUtils {
    public static void setTheme(Activity activity, boolean isDay) {
        if (isDay) {
            activity.setTheme(R.style.AppTheme_Day);
        } else {
            activity.setTheme(R.style.AppTheme_Night);
        }
    }
}
