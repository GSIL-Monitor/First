package com.example.wuxiangyu.daynight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wuxiangyu.first.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initTheme();
    }

    private void initTheme() {
//        boolean isDay = SpUtils.getDayNight();
//        DaynightUtils.setTheme(this, isDay);
    }
}
