package com.example.wuxiangyu.navi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.base.BaseFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var tvNextActivity: Button
    private lateinit var tvNextGraph: Button
    private var param1: String? = null
    private var param2: String? = null
    override fun initView(view: View) {
        tvNextActivity = view.findViewById(R.id.tvNextActivity)
        tvNextActivity.setOnClickListener {
            //todo
            navController?.navigate(R.id.action_secondFragment_to_navigationSecondActivity, null);
        }
        tvNextGraph = view.findViewById(R.id.tvNextGraph)
        tvNextGraph.setOnClickListener {
            navController?.navigate(R.id.action_secondFragment_to_login_graph, null);
        }
    }
}
