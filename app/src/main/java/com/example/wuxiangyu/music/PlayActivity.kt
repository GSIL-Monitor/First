package com.example.wuxiangyu.music

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import com.example.wuxiangyu.first.R

class PlayActivity : AppCompatActivity(), View.OnClickListener {
    val TAG = "PlayActivty"
    lateinit var ivPlayingPlay: ImageView
    lateinit var ivPlayingPrev: ImageView
    lateinit var ivPlayingNext: ImageView
    lateinit var seekBar: SeekBar

    lateinit var ivPlayingMode: ImageView
    lateinit var btnStartService: Button
    lateinit var btnStopService: Button
    lateinit var btnBindService: Button
    lateinit var btnUnbindService: Button

    companion object {
        fun launch(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, PlayActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        initView()
    }

    private fun initView() {
        ivPlayingPlay = findViewById(R.id.ivPlayingPlay)
        ivPlayingPrev = findViewById(R.id.ivPlayingPrev)
        ivPlayingNext = findViewById(R.id.ivPlayingNext)
        ivPlayingMode = findViewById(R.id.ivPlayingMode)
        btnStartService = findViewById(R.id.btnStartService)
        btnStopService = findViewById(R.id.btnStopService)
        btnBindService = findViewById(R.id.btnBindService)
        btnUnbindService = findViewById(R.id.btnUnbindService)
        seekBar = findViewById(R.id.seekBar)

        ivPlayingPlay.setOnClickListener(this)
        ivPlayingNext.setOnClickListener(this)
        ivPlayingPrev.setOnClickListener(this)
        btnStartService.setOnClickListener(this)
        btnStopService.setOnClickListener(this)
        btnBindService.setOnClickListener(this)
        btnUnbindService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            ivPlayingNext -> onNext()
            ivPlayingPrev -> onPre()
            ivPlayingMode -> onMode()
            ivPlayingPlay -> onPlay()
            btnStartService -> meStartService()
            btnStopService -> meStopService()
            btnBindService -> meBindService()
            btnUnbindService -> meUnbindService()
        }
    }

    private fun meBindService() {
        val intent = Intent(this, PlayerService::class.java)
        bindService(intent, conn, BIND_AUTO_CREATE)
    }

    private fun meUnbindService() {
        unbindService(conn)
    }

    val conn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e(TAG, "onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e(TAG, "onServiceConnected")
        }

    }

    private fun meStartService() {
        val intent = Intent(this, PlayerService::class.java)
        startService(intent)
    }

    private fun meStopService() {
        val intent = Intent(this, PlayerService::class.java)
        stopService(intent)
    }

    private fun onNext() {

    }

    private fun onPre() {

    }

    private fun onMode() {

    }

    private fun onPlay() {

    }


}