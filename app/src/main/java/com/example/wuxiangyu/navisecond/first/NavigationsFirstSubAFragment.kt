package com.example.wuxiangyu.navisecond.first

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.haha.R
import com.example.wuxiangyu.base.BaseFragment

class NavigationsFirstSubAFragment : BaseFragment() {
    override fun getLayoutId(): Int {
       return R.layout.fragment_navigations_first_suba
    }

    lateinit var tvFirst: TextView
    lateinit var btnSecondNavigations: Button
    lateinit var btnGoToFirstDeepLink: Button
    override fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnSecondNavigations = view.findViewById(R.id.btnSecondNavigations)
        btnGoToFirstDeepLink = view.findViewById(R.id.btnGoToFirstDeepLink)
        btnSecondNavigations.setOnClickListener{
            navController?.navigate(R.id.action_navigationsFirstSubAFragment_to_navigationsSecondActivity)
        }
        btnGoToFirstDeepLink.setOnClickListener{
            val intent = Intent()
            intent.data = Uri.parse("seu://seu.com/first")
            val bundle = Bundle()

            val extraBundle = Bundle()
            extraBundle.putString("myargs", "haha")
//            intent.putExtra("myargs", "haha")
            bundle.putBundle("android-support-nav:controller:deepLinkExtras", extraBundle)

            intent.putExtras(bundle)
            /*
            这个是操作不了的，因为该deeplink不做当前controller中
             */
//            intent.data = Uri.parse("seu://seu.com/secondsub")
            navController?.onHandleDeepLink(intent)

        }
        arguments?.let {
            val argumentFromNavigationXml = it.getString("myargs")
            if (!argumentFromNavigationXml.isNullOrEmpty()) {
                tvFirst.text = argumentFromNavigationXml
            }
        }

    }
}