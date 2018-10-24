package com.example.wuxiangyu

import android.app.Application
import com.facebook.stetho.Stetho

class MyApplication: Application() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}