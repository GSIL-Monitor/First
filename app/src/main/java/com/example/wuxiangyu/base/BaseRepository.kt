package com.example.wuxiangyu.base

import android.util.SparseArray
import com.example.wuxiangyu.gank.BaseResponse
import com.example.wuxiangyu.gank.RequestId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class BaseRepository {

    private val calls = SparseArray<Call<out BaseResponse>>()

    /**
     * 最基本的网络请求和返回
     */
    fun <T : BaseResponse> enqueue(call: Call<T>, callback: IResponseCallback<T>?) {
        val requestId = RequestId.get(call)

        calls.put(requestId.value, call)

        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                calls.remove(requestId.value)
                //处理错误的逻辑
                callback?.onFail("error ")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                calls.remove(requestId.value)
                //
                val data = response.body()
                if (data != null && !data.error) {
                    callback?.onSuccess(data)
                } else {
                    callback?.onFail("返回错误")
                }
            }
        })
    }
}