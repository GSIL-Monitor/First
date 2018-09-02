package com.example.wuxiangyu

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.example.wuxiangyu.daynight.SpUtils

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        initDarkTheme()
    }
    private fun initDarkTheme() {
        val isDarkTheme = SpUtils.getDayNight()
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    companion object {
        var instance: MyApp? = null
            private set
    }
}
