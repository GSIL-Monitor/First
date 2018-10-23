package com.example.wuxiangyu.gank

import com.example.wuxiangyu.base.IResponseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GankRepository {

    private val gankService = RetrofitManager.create(GankService::class.java)
    fun getAndroidGank(callback: IResponseCallback<List<GankAndroidItemBean>>) {
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
                    callback?.onSuccess(data.results)
                }
            }

        })
    }
}