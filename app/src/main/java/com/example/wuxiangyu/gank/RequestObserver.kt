package com.example.wuxiangyu.gank

interface RequestObserver<T> {
    fun observer(t: T?)
}