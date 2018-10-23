package com.example.wuxiangyu.base

interface IResponseCallback<T> {
    fun onSuccess(t: T)
    fun onFail(errorString: String)
}