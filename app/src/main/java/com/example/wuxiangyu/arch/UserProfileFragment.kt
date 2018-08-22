package com.example.wuxiangyu.arch

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wuxiangyu.first.R

class UserProfileFragment: Fragment() {
    companion object {
        val TAG = "ProductListViewModel"
    }
    lateinit var tvContent: TextView

    lateinit var viewModel: UserProfileViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId = "my_id"

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.product_fragment, container, false)
        initView(view)
        initData()
        return view
    }

    private fun initData() {
        viewModel = UserProfileViewModel()
        viewModel.user?.observe(this, Observer<User> {
            //todo

        })
    }

    private fun initView(view: View) {
        tvContent = view.findViewById(R.id.tvContent)
        tvContent.text = "xxxxx"
    }
}