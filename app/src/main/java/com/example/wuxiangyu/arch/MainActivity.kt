package com.example.wuxiangyu.arch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wuxiangyu.haha.MainActivity
import com.example.wuxiangyu.haha.R

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main1)
        if (savedInstanceState != null) {
            val fragment = UserProfileFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, UserProfileFragment.TAG)
        }
        MainActivity.launch(this)
    }
}