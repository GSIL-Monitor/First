package com.example.wuxiangyu.navisecond.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.navisecond.BaseFragment

class NavigationsFirstSubAFragment : BaseFragment() {
    lateinit var tvFirst: TextView
    lateinit var btnSecondNavigations: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigations_first_suba, container, false)
        initView(view)

        return view
    }

    private fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnSecondNavigations = view.findViewById(R.id.btnSecondNavigations)
        btnSecondNavigations.setOnClickListener{
            navController?.navigate(R.id.action_navigationsFirstSubAFragment_to_navigationsSecondActivity)
        }
    }
}