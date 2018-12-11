package com.example.wuxiangyu.media.opengl

import android.app.ActivityManager
import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wuxiangyu.Logger
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class FirstOpenGLProjectActivity : AppCompatActivity(), GLSurfaceView.Renderer {
    val TAG = "FirstOpenGLProjectActivity"
    override fun onDrawFrame(gl: GL10?) {
        Logger.e(TAG, "onDrawFragme")
        //清空屏幕，会擦除所有颜色，并用glclearcolor的颜色填充整个屏幕
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        Logger.e(TAG, "onSurfaceChanged, $width, $height")
        //设置视口，设置用来渲染的surface大小
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        Logger.e(TAG, "onSurfaceCreated")
        //设置清空屏幕使用的颜色
        GLES20.glClearColor(255.0f, 0.0f, 0.0f, 1.0f)
    }

    private var renderSet = false
    private lateinit var glSurfaceView: GLSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glSurfaceView = GLSurfaceView(this)
        setContentView(glSurfaceView)
        if (isSupportEs2()) {
            glSurfaceView.setEGLContextClientVersion(2)
            glSurfaceView.setRenderer(this)
            renderSet = true
        } else {
            return
        }
    }

    fun isSupportEs2(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo
        return configurationInfo.reqGlEsVersion >= 0x20000
    }

    override fun onResume() {
        super.onResume()
        if (renderSet) {
            glSurfaceView.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (renderSet) {
            glSurfaceView.onPause()
        }
    }
}