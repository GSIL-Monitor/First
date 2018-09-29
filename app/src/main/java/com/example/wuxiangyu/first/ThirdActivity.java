package com.example.wuxiangyu.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    LyricView lyricView;
    TextView tvAlpha;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigations_third);
        lyricView = findViewById(R.id.lyricView);
        lyricView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyricView.startAnimation();
            }
        });
        tvAlpha = findViewById(R.id.tvAlpha);
        initData();
    }

    private void initData() {
        tvAlpha.postDelayed(new Runnable() {
            @Override
            public void run() {

                tvAlpha.postDelayed(this, 1000);
            }
        }, 3000);

    }

    void test() {
        //version1 commit2
        //master commiter3
    }
}
