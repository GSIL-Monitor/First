package com.example.wuxiangyu.gank

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.net.wifi.p2p.WifiP2pGroup
import android.net.wifi.p2p.WifiP2pManager
import android.preference.SwitchPreference
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.wuxiangyu.base.BaseFragment
import com.example.wuxiangyu.first.R

class GankFragment : BaseFragment(), GankAdapter.GankItemClickListener {
    override fun itemClick(bean: GankAndroidItemBean) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(bean.url)
        startActivity(intent)
    }

    private lateinit var rvContent: RecyclerView
    private lateinit var gankAdapter: GankAdapter
    private lateinit var swipeLayout: SwipeRefreshLayout

    private lateinit var viewModel: GankViewModel
    override fun getLayoutId(): Int {
        return R.layout.fragment_gank
    }

    override fun initView(root: View) {
        rvContent = root.findViewById(R.id.rvContent)
        swipeLayout = root.findViewById(R.id.swipeLayout)
        gankAdapter = GankAdapter(context!!, this)
        rvContent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvContent.adapter = gankAdapter
        swipeLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.loadInfo()
            }

        })
    }

    override fun initData() {
        viewModel = ViewModelProviders.of(this).get(GankViewModel::class.java)
//        viewModel.initWitLiveData()
        viewModel.init()
        swipeLayout.isRefreshing = true
        viewModel.loadInfo()
        viewModel.ganList?.observe(this, Observer {
            swipeLayout.isRefreshing = false
            if (it != null) {
                gankAdapter.refresh(it)
            }
        })

    }
}