package com.example.wuxiangyu.navisecond.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.navisecond.BaseFragment

class NavigationsThirdFragment : BaseFragment() {
    lateinit var tvFirst: TextView
    lateinit var btnGoSubA: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigations_third, container, false)
        initView(view)

        return view
    }

    private fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnGoSubA = view.findViewById(R.id.btnGoSubA)
        btnGoSubA.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigationsThirdFragment_to_navigationsThirdSubAFragment)
        }
    }
}