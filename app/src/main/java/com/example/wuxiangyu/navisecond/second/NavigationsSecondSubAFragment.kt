package com.example.wuxiangyu.navisecond.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.base.BaseFragment

class NavigationsSecondSubAFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_navigations_second_suba
    }

    lateinit var tvFirst: TextView
    lateinit var btnGoSubB: Button

    override fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnGoSubB = view.findViewById(R.id.btnGoSubB)
        btnGoSubB.setOnClickListener{
            navController?.navigate(R.id.action_navigationsSecondSubAFragment_to_navigationsSecondSubBFragment)
        }
    }
}