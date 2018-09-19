package com.example.wuxiangyu.music

import android.app.Activity
import android.app.Application
import android.os.Bundle

object ActivityLifecycleHandler : Application.ActivityLifecycleCallbacks {
    private var resumed: Int = 0
    private var paused: Int = 0
    private var started: Int = 0
    private var stopped: Int = 0
    var isImmersionPlayerActivityVisible = false
    var topActivity: Activity? = null
    interface OnVisibleStateChangeListener {
        fun onVisibleStateStated(visible: Boolean)
    }
    var onVisibleStateChangeListener: OnVisibleStateChangeListener? = null
    var isInBg = false

    private val isBackGround: Boolean
        get() = started <= stopped

    override fun onActivityPaused(activity: Activity?) {
        ++paused
    }

    override fun onActivityResumed(activity: Activity?) {
        ++resumed
        topActivity = activity
    }

    override fun onActivityStarted(activity: Activity?) {
        ++started
        if (isInBg) {
            isInBg =false
            onVisibleStateChangeListener?.onVisibleStateStated(true)
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        if (activity == topActivity) {
            topActivity = null
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
        ++stopped
        if (isBackGround) {
            isInBg = true
            onVisibleStateChangeListener?.onVisibleStateStated(false)
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

    }
}