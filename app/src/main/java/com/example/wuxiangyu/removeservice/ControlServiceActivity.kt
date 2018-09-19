package com.example.wuxiangyu.removeservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.wuxiangyu.first.R

class ControlServiceActivity : AppCompatActivity() {
    lateinit var btnSendMessageToService: Button
    lateinit var btnBindService: Button
    var remoteService: RemoteService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controlservice)
        myBindService()
        initView()
    }

    private fun myBindService() {
        val intent = Intent(this, RemoteService::class.java)
//        startService(intent)
        bindService(intent, conn, BIND_AUTO_CREATE)
    }

    val conn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e("xxxx", "onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e("xxxx", "onServiceConnected")
            val binder = service as RemoteService.MyBinder
            remoteService = binder.getBinder()
//            remoteService?.register(object : RegisterServiceListener {
//                override fun getServiceName(name: String) {
//                    Toast.makeText(this@ControlServiceActivity, name, Toast.LENGTH_SHORT).show()
//                }
//
//            })
        }

    }

    private fun initView() {
        btnSendMessageToService = findViewById(R.id.btnSendMessageToService)
        btnSendMessageToService.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                remoteService?.getServiceName()
            }
        })
    }

    override fun onDestroy() {
        unbindService(conn)
        super.onDestroy()
    }
}