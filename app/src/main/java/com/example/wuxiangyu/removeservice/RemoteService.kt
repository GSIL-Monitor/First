package com.example.wuxiangyu.removeservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class RemoteService: Service() {
    private var registerListener: RegisterServiceListener? = null
    inner class MyBinder: Binder() {
        fun getBinder(): RemoteService {
            return this@RemoteService
        }
    }
    override fun onBind(intent: Intent?): IBinder {
        return MyBinder()
    }

    fun register(registerServiceListener: RegisterServiceListener) {
        registerListener = registerServiceListener
    }


    fun getServiceName() {
        registerListener?.getServiceName("haha")
    }

}