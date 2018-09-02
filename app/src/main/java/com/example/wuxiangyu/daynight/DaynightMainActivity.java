package com.example.wuxiangyu.daynight;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wuxiangyu.first.R;

public class DaynightMainActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvContent;
    private Button mBtnClick;
    ImageView ivColorFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daynight_main);
        initView();
    }

    private void initView() {
        mTvContent = (TextView) findViewById(R.id.tvContent);
        mBtnClick = (Button) findViewById(R.id.btnClick);
        mBtnClick.setOnClickListener(this);
        ivColorFilter = findViewById(R.id.ivColorFilter);
        ivColorFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColorFilter();
            }
        });
    }

    private void changeColorFilter() {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0.5f);
        ivColorFilter.setColorFilter(new ColorMatrixColorFilter(matrix));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnClick:
                //todo
            DaynightSecondActivity.launch(DaynightMainActivity.this);
                break;
        }
    }
}
