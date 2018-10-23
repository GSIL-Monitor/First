package com.example.wuxiangyu.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.wuxiangyu.Logger

abstract class BaseFragment : Fragment() {
    val TAG = this.javaClass.simpleName ?: "BaseFragment"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.i(TAG, "onCreateView")
        val view = inflater.inflate(getLayoutId(), container, false)

        return view
    }
    abstract fun getLayoutId(): Int
    open fun initView(root: View) {}
    open fun initData() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Logger.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
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

    protected var navController: NavController? = null
        get() {
            return try {
                val fragment = this.parentFragment as NavHostFragment
                fragment.navController
            } catch (e: Exception) {
                null
            }
        }
}