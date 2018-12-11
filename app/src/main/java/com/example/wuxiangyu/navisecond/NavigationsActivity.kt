package com.example.wuxiangyu.navisecond

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.FrameLayout
import com.example.wuxiangyu.Logger
import com.example.wuxiangyu.haha.R
import kotlinx.android.synthetic.main.activity_navigations.*

class NavigationsActivity : AppCompatActivity() {
    companion object {
        const val TAG = "NavigationsActivity"
    }
    private lateinit var flContainer: FrameLayout
    private val navigationUtils = NavigationUtils()

    private var handler = object : Handler() {
        override fun handleMessage(msg: Message?) {

        }
    }
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
        btnHandler.setOnClickListener {
            MainThreadPoster.postRunnableDelay(Runnable {
                Logger.e(TAG, "haha")
            }, "haha", 5000)
        }
        btnRemove.setOnClickListener {
            handler.removeCallbacksAndMessages("haha")
        }
        btnHandlerAll.setOnClickListener {
            MainThreadPoster.postRunnableDelay(Runnable {
                Logger.e(TAG, "haha1")
            }, "haha1", 5000)
        }
        btnRemoveAll.setOnClickListener {
            MainThreadPoster.removeCallbacksAndMessages(null)
        }
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
            R.id.gankFragment -> R.navigation.nagivations_gank
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