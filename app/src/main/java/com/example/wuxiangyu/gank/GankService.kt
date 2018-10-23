package com.example.wuxiangyu.gank

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GankService {

    @GET("/api/random/data/Android/{id}")
    fun getAndroidByPage(@Path("id") id: Int): Call<GankAndroidBean>
}