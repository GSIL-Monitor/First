package com.example.wuxiangyu.navisecond

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wuxiangyu.Logger

open class BaseFragment: Fragment() {
    val TAG = this.javaClass.simpleName?: "BaseFragment"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.i(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Logger.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        Logger.i(TAG, "onResume")
        super.onResume()
    }

    override fun onStart() {
        Logger.i(TAG, "onStart")
        super.onStart()
    }

    override fun onPause() {
        Logger.i(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Logger.i(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logger.i(TAG, "onDestroy")
        super.onDestroy()
    }
}