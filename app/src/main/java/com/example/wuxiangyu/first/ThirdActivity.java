package com.example.wuxiangyu.first;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    LyricView lyricView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        lyricView = findViewById(R.id.lyricView);
        lyricView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyricView.startAnimation();
            }
        });
    }


    private void master() {
        System.out.println("master");
    }

    private void version() {
        System.out.println("version1");
        System.out.println("master-version1");
    }
}
