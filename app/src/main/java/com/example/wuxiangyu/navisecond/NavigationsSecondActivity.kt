package com.example.wuxiangyu.navisecond

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.wuxiangyu.haha.R

class NavigationsSecondActivity : AppCompatActivity() {
    private lateinit var tvContent: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigations_second)
        initView()
    }

    private fun initView() {
        tvContent = findViewById(R.id.tvContent)
       val extraString = intent.getStringExtra("myargs")
        if (!extraString.isNullOrEmpty()) {
            tvContent.text = extraString
        }
    }
}