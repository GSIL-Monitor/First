package com.example.wuxiangyu.gank

class GankAndroidBean: BaseResponse() {
    val results = ArrayList<GankAndroidItemBean>()
}

class GankAndroidItemBean {
    val _id = ""
    val createAt = ""
    val desc = ""
    val images = ArrayList<String>()
    val source = ""
    val type = ""
    val url = ""
    val used = false
    val who = ""
}