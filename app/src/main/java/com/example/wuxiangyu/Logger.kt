package com.example.wuxiangyu

import android.util.Log

class Logger {
    companion object {

        fun e(tag: String, msg: String) {
            Log.e(tag, msg)
        }
        fun i(tag: String, msg: String) {
            Log.i(tag, msg)
        }
    }
}