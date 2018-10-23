package com.example.wuxiangyu.navisecond.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.base.BaseFragment

class NavigationsThirdSubAFragment : BaseFragment() {
    override fun getLayoutId(): Int {
       return R.layout.fragment_navigations_third_suba
    }

    lateinit var tvFirst: TextView
    override fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
    }
}