package com.example.wuxiangyu.navisecond

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
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
        bottomNavigation.inflateMenu(R.menu.navigations_menu)

        var host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragment) as NavHostFragment

        var navController = host.navController
        var navigationView: BottomNavigationView = findViewById(R.id.bottomNavigation)

        NavigationUI.setupWithNavController(navigationView, navController)
        //todo bind
//        NavigationUI.setupWithNavController()
    }
}