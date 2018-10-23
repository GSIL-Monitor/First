package com.example.wuxiangyu.gank

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.wuxiangyu.Logger
import com.example.wuxiangyu.base.IResponseCallback

class GankViewModel: ViewModel() {

    private val gankRepository = GankRepository()

    var ganList = MutableLiveData<List<GankAndroidItemBean>>()

    fun init() {
//        ganList =
    }

    fun loadInfo() {
        gankRepository.getAndroidGank(object : IResponseCallback<List<GankAndroidItemBean>> {
            override fun onSuccess(t: List<GankAndroidItemBean>) {
                ganList.postValue(t)
            }

            override fun onFail(errorString: String) {
                Logger.e("loadinfo", "error")
            }

        })
    }
}