package com.example.wuxiangyu.first

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        Log.e("Wxy", "Ccc")
        super.onDestroy()
    }

    companion object {

        fun launch(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, SecondActivity::class.java)
            activity.startActivity(intent)
        }
    }
    fun test() {
        //master commit4
    }
}
