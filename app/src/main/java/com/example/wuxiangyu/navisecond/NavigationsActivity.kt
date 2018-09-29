package com.example.wuxiangyu.navisecond

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.wuxiangyu.first.R

class NavigationsActivity : AppCompatActivity() {
    companion object {
        const val TAG = "NavigationsActivity"
    }
    private lateinit var flContainer: FrameLayout
    private val navigationUtils = NavigationUtils()

    lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigations)
        initView()
    }

    private fun initView() {
        flContainer = findViewById(R.id.flContainer)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        bottomNavigation.inflateMenu(R.menu.navigations_menu)

//        var host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragment) as NavHostFragment

//        var navController = NavController(this)
//        navController.setGraph(R.navigation.navigations_graph)
//        var navigationView: BottomNavigationView = findViewById(R.id.bottomNavigation)
//        navController.navigatorProvider.addNavigator(navController)
//        NavigationUI.setupWithNavController(navigationView, navController)


        setBottomListener(bottomNavigation)
        changeHostFragment(R.id.firstFragment)
    }

    private fun setBottomListener(navigationView: BottomNavigationView) {
        navigationView.setOnNavigationItemReselectedListener(object : BottomNavigationView.OnNavigationItemReselectedListener {
            override fun onNavigationItemReselected(item: MenuItem) {
                //重复点击
                val itemId = item.itemId
            }

        })

        navigationView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                //切换
                val itemId = item.itemId
                changeHostFragment(itemId)
                return true
            }
        })
    }

    private fun changeHostFragment(itemId: Int) {
        var navId =
        when (itemId) {
            R.id.firstFragment -> R.navigation.nagivations_first
            R.id.secondFragment ->  R.navigation.nagivations_second
            R.id.thirdFragment -> R.navigation.nagivations_third
            else -> R.navigation.nagivations_default
        }
        val navHostFragment = navigationUtils.getNavHostFragment(navId)
        val ft = supportFragmentManager.beginTransaction()
        ft.setPrimaryNavigationFragment(navHostFragment)//控制back回退按钮：app:defaultNavHost="true"

        if (!navHostFragment.isAdded) {
            ft.add(R.id.flContainer, navHostFragment)
        }
        for ( fragment : Fragment in supportFragmentManager.fragments) {
            ft.hide(fragment)
        }
        ft.show(navHostFragment)
//        ft.addToBackStack(null)//会和setprimarynavigation冲突
        ft.commit()
    }


}