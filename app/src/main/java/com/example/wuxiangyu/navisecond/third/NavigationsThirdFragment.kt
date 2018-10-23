package com.example.wuxiangyu.navisecond.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.base.BaseFragment

class NavigationsThirdFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_navigations_third
    }

    lateinit var tvFirst: TextView
    lateinit var btnGoSubA: Button

    override fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnGoSubA = view.findViewById(R.id.btnGoSubA)
        btnGoSubA.setOnClickListener {
            navController?.navigate(R.id.action_navigationsThirdFragment_to_navigationsThirdSubAFragment)
        }
    }
}