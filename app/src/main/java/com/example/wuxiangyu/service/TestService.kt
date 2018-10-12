package com.example.wuxiangyu.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class TestService: Service() {
    private val TAG = "TestService"
    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG, "onBind")
        return MyBinder(this)
    }

    override fun onCreate() {
        Log.e(TAG, "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        super.onDestroy()
    }
}

class MyBinder(var ervice: TestService): Binder() {
    fun getService(): TestService {
        return ervice
    }
}