package com.example.wuxiangyu.navisecond.first

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.navisecond.BaseFragment

class NavigationsFirstFragment : BaseFragment() {
    lateinit var tvFirst: TextView
    lateinit var btnGoSubA: Button
    lateinit var btnSecondSubA: Button
    lateinit var btnDeeplink: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigations_first, container, false)
        initView(view)

        return view
    }

    private fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnGoSubA = view.findViewById(R.id.btnGoSubA)
        btnSecondSubA = view.findViewById(R.id.btnSecondSubA)
        btnDeeplink = view.findViewById(R.id.btnDeeplink)
        btnGoSubA.setOnClickListener{
            navController?.navigate(R.id.action_navigationsFirstFragment_to_navigationsFirstSubAFragment)
        }
        btnSecondSubA.setOnClickListener{
            navController?.navigate(R.id.action_navigationsFirstFragment_to_navigationsSecondSubAFragment)
        }
        btnDeeplink.setOnClickListener{
            //deeplink来跳转
//            navController?.navigate(R.id.action_navigationsFirstFragment_to_navigationsSecondSubAFragment)
//            val navDeepLinkBuilder = navController?.createDeepLink()
            val intent = Intent()
            intent.data = Uri.parse("seu://seu.com/firstsub")
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
    }
}