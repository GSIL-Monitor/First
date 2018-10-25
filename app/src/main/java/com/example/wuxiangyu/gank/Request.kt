package com.example.wuxiangyu.gank

class Request<T> {
    private var requestObserver: RequestObserver<T>? = null
    fun observer(observer: RequestObserver<T>) {
        this.requestObserver = observer
    }

    fun broadcaste(t: T?) {
        requestObserver?.observer(t)
    }
}