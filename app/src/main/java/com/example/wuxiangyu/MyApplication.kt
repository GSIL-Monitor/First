package com.example.wuxiangyu

import android.app.Application

class MyApplication: Application() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}