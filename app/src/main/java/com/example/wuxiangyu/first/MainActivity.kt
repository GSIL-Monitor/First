package com.example.wuxiangyu.first

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<View>(R.id.btnClick)
        var tv = findViewById<TextView>(R.id.tvHeight)
        button.setOnClickListener {
//            SecondActivity.launch(this@MainActivity)
            Log.e("cc", "成" + tv.height)
            Log.e("cc", "top" + button.height)
            tv.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.lyric_scale))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

        fun launch(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
