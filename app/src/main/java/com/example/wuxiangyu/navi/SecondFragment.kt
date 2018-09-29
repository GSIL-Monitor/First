package com.example.wuxiangyu.navi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.navisecond.BaseFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : BaseFragment() {
    private lateinit var tvNextActivity: Button
    private lateinit var tvNextGraph: Button
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        tvNextActivity = view.findViewById(R.id.tvNextActivity)
        tvNextActivity.setOnClickListener {
            //todo
            navController?.navigate(R.id.action_secondFragment_to_navigationSecondActivity, null);
        }
        tvNextGraph = view.findViewById(R.id.tvNextGraph)
        tvNextGraph.setOnClickListener {
            navController?.navigate(R.id.action_secondFragment_to_login_graph, null);
        }
        return view
    }
}
