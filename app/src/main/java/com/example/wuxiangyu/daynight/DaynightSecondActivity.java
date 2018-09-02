package com.example.wuxiangyu.daynight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuxiangyu.first.R;

public class DaynightSecondActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvContent;
    private Button mBtnClick;
    private Button mBtnChange;
    Handler handler = new Handler();
    private ImageView ivDay;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, DaynightSecondActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daynight_second);
        initView();
    }

    private void initView() {
        mTvContent = (TextView) findViewById(R.id.tvContent);
        mBtnClick = (Button) findViewById(R.id.btnChangeMode);
        mBtnClick.setOnClickListener(this);
        mBtnChange = findViewById(R.id.btnChangeText);
        ivDay = findViewById(R.id.ivDay);
        mBtnChange.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnChangeMode:
                //todo change mode

                changeDayNight();
                break;
            case R.id.btnChangeText:
                mTvContent.setText("change text");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View view = View.inflate(DaynightSecondActivity.this, R.layout.common_toast_bg, null);
                        Log.e("xxxx", "isDead: " + DaynightSecondActivity.this.isFinishing());
                        Toast toast = Toast.makeText(DaynightSecondActivity.this, "haha", Toast.LENGTH_SHORT);
                        toast.setView(view);
                        toast.show();
                    }
                }, 5000);
                break;
        }
    }
    private void showDialog () {
        new AlertDialog.Builder(this)
                .setMessage("消息")
                .setNegativeButton("sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DaynightSecondActivity.this, "sure" ,Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DaynightSecondActivity.this, "cancel" ,Toast.LENGTH_SHORT).show();

                    }
                })
                .show();
    }

    private void changeDayNight() {
        boolean isDay = SpUtils.getDayNight();
        SpUtils.setDayNight(!isDay);
//        DaynightUtils.setTheme(this, !isDay);
//        recreate();
        setContentView(R.layout.activity_daynight_second);
        refreshUi();

//        ivDay.setColorFilter(Color.argb(0,0,0,0));
    }

    private void refreshUi() {

    }
}
