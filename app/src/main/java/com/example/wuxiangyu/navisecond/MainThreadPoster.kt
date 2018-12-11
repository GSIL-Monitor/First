package com.example.wuxiangyu.navisecond

import android.os.Handler
import android.os.Looper
import android.os.SystemClock


object MainThreadPoster : Handler(Looper.getMainLooper()) {
    fun postRunnable(runnable: Runnable, token: Any) {
        postRunnableDelay(runnable, token, 0)
    }

    fun postRunnableDelay(runnable: Runnable, token: Any, delay: Long) {
        postAtTime(runnable, token, SystemClock.uptimeMillis() + delay)
    }

    fun postRunnableAtFixRate(runnable: Runnable, token: Any, delay: Long, period: Long) {
        postAtTime(object : Runnable {
            override fun run() {
                runnable.run()
                postAtTime(this, token, SystemClock.uptimeMillis() + period)
            }
        }, token, SystemClock.uptimeMillis() + delay)
    }

    fun clear(token: Any) {
        removeCallbacksAndMessages(token)
    }
}