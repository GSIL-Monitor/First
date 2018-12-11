package com.example.wuxiangyu.media

import android.graphics.Rect
import android.hardware.Camera
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import com.example.wuxiangyu.haha.R
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.IOException
import android.hardware.Camera.Parameters.FOCUS_MODE_AUTO
import com.google.common.collect.Lists


class CameraActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private val camera by lazy { Camera.open() }
    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {


    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        camera.release()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        try {
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        initView()
    }

    //surfaceview
    private fun initView() {
        camera.setDisplayOrientation(90)
        surfaceview.holder.addCallback(this)
        btnCamera.setOnClickListener {
            camera.cancelAutoFocus()
            val parameters = camera.parameters
            parameters.focusMode = Camera.Parameters.FOCUS_MODE_AUTO
            parameters.focusAreas = Lists.newArrayList(Camera.Area(Rect(0, 0, 500, 500), 1000))

//            if (meteringAreaSupported) {
//                parameters.meteringAreas = Lists.newArrayList(Camera.Area(meteringRect, 1000))
//            }

            camera.parameters = parameters
            camera.autoFocus(object : Camera.AutoFocusCallback {
                override fun onAutoFocus(success: Boolean, camera: Camera?) {

                }
            })
        }
    }
}