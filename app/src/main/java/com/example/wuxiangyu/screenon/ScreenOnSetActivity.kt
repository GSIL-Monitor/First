package com.example.wuxiangyu.screenon

import android.app.ActivityManager
import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView

import com.example.wuxiangyu.Logger
import com.example.wuxiangyu.MyApplication

class ScreenOnSetActivity : AppCompatActivity() {
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val mIntent = Intent(MyApplication.instance, ScreenOnShowActivity::class.java)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mIntent.action = Intent.ACTION_MAIN
            mIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(mIntent)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        initWindow()
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        tv.text = "set view"
        setContentView(tv)
        val intent = Intent()
        intent.setClass(this, ScreenService::class.java)
        startService(intent)

        tv.setOnClickListener {
            val intent = Intent()
            intent.setAction("miui.intent.action.APP_PERM_EDITOR")
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.putExtra("extra_pkgname", "com.example.wuxiangyu.haha")
            startActivity(intent)
        }
    }


    private fun initWindow() {}
}
