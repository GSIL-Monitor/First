package com.example.wuxiangyu.notification

import android.app.Notification
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import com.example.wuxiangyu.haha.R
import com.example.wuxiangyu.screenon.ScreenOnSetActivity
import com.example.wuxiangyu.screenon.ScreenService
import kotlinx.android.synthetic.main.activity_noyificiation.*

class NotificationActivity : AppCompatActivity() {
    val NOTIFICATION_ID = 888
    lateinit var mNotificationManagerCompat: NotificationManagerCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noyificiation)
        initView()
    }

    private fun initView() {
        tvShowNotification.setOnClickListener {
            showNotification()
        }
        mNotificationManagerCompat = NotificationManagerCompat.from(applicationContext)
    }

    private fun showNotification() {
        val snoozeIntent = Intent(this, ScreenOnSetActivity::class.java)
        val snoozePendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, snoozeIntent, 0)
        val CHANNEL_ONE_ID = "com.kjtech.app.N1"
        val CHANNEL_ONE_NAME = "Channel One"
        var notificationChannel: NotificationChannel?
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME, IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }
        var mBuilder = NotificationCompat.Builder(this, CHANNEL_ONE_ID)
            .setSmallIcon(R.drawable.ic_android)
            .setContentTitle("text title")
            .setContentText("text content")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_android, "haha", snoozePendingIntent)

        val notification = mBuilder
            .build()

        mNotificationManagerCompat.notify(NOTIFICATION_ID, notification)
    }
}