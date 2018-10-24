package com.example.wuxiangyu.gank

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

class GankAndroidBean : BaseResponse() {
    val results = ArrayList<GankAndroidItemBean>()
}

@Entity(tableName = "gank_android")
class GankAndroidItemBean {
    @PrimaryKey
    var _id = ""
    var createAt = ""
    var desc = ""
    //    val images = ArrayList<String>()
    var source = ""
    var type = ""
    var url = ""
    var used = false
    var who = ""
}