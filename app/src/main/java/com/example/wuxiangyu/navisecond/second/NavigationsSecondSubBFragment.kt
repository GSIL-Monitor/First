package com.example.wuxiangyu.navisecond.second

import android.view.View
import android.widget.TextView
import com.example.wuxiangyu.haha.R
import com.example.wuxiangyu.base.BaseFragment

class NavigationsSecondSubBFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_navigations_second_subb
    }

    lateinit var tvFirst: TextView
    override fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
    }
}