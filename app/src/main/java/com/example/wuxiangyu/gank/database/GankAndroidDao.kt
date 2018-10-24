package com.example.wuxiangyu.gank.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.wuxiangyu.gank.GankAndroidItemBean

@Dao
interface GankAndroidDao {
    @Query("SELECT * FROM gank_android")
    fun getAllGankAndroid(): List<GankAndroidItemBean>

    @Query("SELECT * FROM gank_android")
    fun getAllGankAndroidWithLiveData(): LiveData<List<GankAndroidItemBean>>

    @Insert
    fun saveGankAndroid(items: ArrayList<GankAndroidItemBean>)
}