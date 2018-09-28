package com.example.wuxiangyu.navi

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.wuxiangyu.first.R

class NavigationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        var host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.myfragment) as NavHostFragment

        var navController = host.navController
        var navigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        NavigationUI.setupWithNavController(navigationView, navController)
    }
}