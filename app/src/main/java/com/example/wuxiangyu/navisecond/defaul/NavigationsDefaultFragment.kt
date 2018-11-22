package com.example.wuxiangyu.navisecond.defaul

import android.view.View
import android.widget.TextView
import com.example.wuxiangyu.haha.R
import com.example.wuxiangyu.base.BaseFragment

class NavigationsDefaultFragment : BaseFragment() {
    lateinit var tvFirst: TextView
    override fun initView(view: View) {
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_navigations_default
    }

}