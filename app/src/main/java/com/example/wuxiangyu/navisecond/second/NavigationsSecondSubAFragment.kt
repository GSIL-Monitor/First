package com.example.wuxiangyu.navisecond.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.navisecond.BaseFragment

class NavigationsSecondSubAFragment : BaseFragment() {
    lateinit var tvFirst: TextView
    lateinit var btnGoSubB: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigations_second_suba, container, false)
        initView(view)

        return view
    }

    private fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnGoSubB = view.findViewById(R.id.btnGoSubB)
        btnGoSubB.setOnClickListener{
            navController?.navigate(R.id.action_navigationsSecondSubAFragment_to_navigationsSecondSubBFragment)
        }
    }
}