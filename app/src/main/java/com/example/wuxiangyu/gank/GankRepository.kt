package com.example.wuxiangyu.gank

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.wuxiangyu.MyApplication
import com.example.wuxiangyu.base.IResponseCallback
import com.example.wuxiangyu.gank.database.GankDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GankRepository {

    private val db = GankDatabase.of(MyApplication.instance)
    private val gankService = RetrofitManager.create(GankService::class.java)

    fun getAndroidGankFromRoom(): List<GankAndroidItemBean> {
        return db.ganAndroidDao().getAllGankAndroid()
    }

    fun getAndroidGankFromRoomWithLiveData(): LiveData<List<GankAndroidItemBean>> {
        return db.ganAndroidDao().getAllGankAndroidWithLiveData()
    }
    fun getAndroidGankFromServer(callback: IResponseCallback<List<GankAndroidItemBean>>) {
        val call = gankService.getAndroidByPage(10)
        call.enqueue(object : Callback<GankAndroidBean> {
            override fun onFailure(call: Call<GankAndroidBean>, t: Throwable) {
                callback?.onFail("error")
            }

            override fun onResponse(
                call: Call<GankAndroidBean>,
                response: Response<GankAndroidBean>
            ) {

                val data = response.body()
                if (data != null) {
                    db.ganAndroidDao().saveGankAndroid(data.results)
                    callback?.onSuccess(data.results)
                }
            }

        })
    }
}