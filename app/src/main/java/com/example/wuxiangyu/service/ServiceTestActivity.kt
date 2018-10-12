package com.example.wuxiangyu.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.wuxiangyu.first.R


class ServiceTestActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "ServiceTestActivity"
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnBind: Button
    private lateinit var btnUnbind: Button
    private lateinit var myIntent: Intent
    val conn = object: ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e(TAG, "onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e(TAG, "onServiceConnected")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicetest)
        initView()
        myIntent = Intent(this, TestService::class.java)
    }

    private fun initView() {
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnBind = findViewById(R.id.btnBind)
        btnUnbind = findViewById(R.id.btnUnbind)
        btnStart.setOnClickListener(this)
        btnStop.setOnClickListener(this)
        btnBind.setOnClickListener(this)
        btnUnbind.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnStart -> {
                startService(myIntent)
            }
            R.id.btnStop -> {
                stopService(myIntent)
            }
            R.id.btnBind -> {
                bindService(myIntent, conn, Context.BIND_AUTO_CREATE);
            }
            R.id.btnUnbind -> {
                unbindService(conn)
            }
        }
    }
}