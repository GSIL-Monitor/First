package com.example.wuxiangyu.navisecond.first

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.wuxiangyu.haha.R
import com.example.wuxiangyu.base.BaseFragment

class NavigationsFirstFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_navigations_first
    }

    lateinit var tvFirst: TextView
    lateinit var btnGoSubA: Button
    lateinit var btnSecondSubA: Button
    lateinit var btnDeeplink: Button
    lateinit var btnDeeplinkActivity: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigations_first, container, false)

        return view
    }

    override fun initView(view: View) {
        tvFirst = view.findViewById(R.id.tvFirst)
        btnGoSubA = view.findViewById(R.id.btnGoSubA)
        btnSecondSubA = view.findViewById(R.id.btnSecondSubA)
        btnDeeplink = view.findViewById(R.id.btnDeeplink)
        btnDeeplinkActivity = view.findViewById(R.id.btnDeeplinkActivity)
        btnGoSubA.setOnClickListener{
            //deeplink和action可以二选一，所以有deeplink，可以不需要有action
            navController?.navigate(R.id.action_navigationsFirstFragment_to_navigationsFirstSubAFragment)
        }
        btnSecondSubA.setOnClickListener{
            navController?.navigate(R.id.action_navigationsFirstFragment_to_navigationsSecondSubAFragment)
        }
        //deeplink跳到 本navigation内的 activity
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

        //deeplink跳到本navigation内的NavigationsSecondActivity
        btnDeeplinkActivity.setOnClickListener{
            val intent = Intent()
            intent.data = Uri.parse("seu://seu.com/navigationssecond")
            val bundle = Bundle()

            val extraBundle = Bundle()
            extraBundle.putString("myargs", "haha")
//            intent.putExtra("myargs", "haha")
            bundle.putBundle("android-support-nav:controller:deepLinkExtras", extraBundle)

            intent.putExtras(bundle)
            navController?.onHandleDeepLink(intent)
        }
    }
}