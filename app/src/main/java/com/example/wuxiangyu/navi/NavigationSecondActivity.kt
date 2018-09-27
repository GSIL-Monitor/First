package com.example.wuxiangyu.navi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.wuxiangyu.first.R

class NavigationSecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textview = TextView(this)
        textview.text = "haha"
        setContentView(textview)
    }
}