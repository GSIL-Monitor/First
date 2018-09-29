package com.example.wuxiangyu.navisecond

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.wuxiangyu.first.R

class NavigationsActivity: AppCompatActivity() {
    lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigations)
        initView()
    }

    private fun initView() {
        bottomNavigation = findViewById(R.id.bottomNavigation)
        bottomNavigation.inflateMenu(R.menu.menu_bottom_nav)

        //todo bind
//        NavigationUI.setupWithNavController()
    }
}