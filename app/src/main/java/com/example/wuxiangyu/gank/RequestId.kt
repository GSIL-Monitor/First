package com.example.wuxiangyu.gank

import java.util.concurrent.atomic.AtomicInteger

class RequestId(val value: Int, val call: Any?) {
    companion object {
        private val generator: AtomicInteger = AtomicInteger(1)
        fun get(call: Any?= null): RequestId {
            val id = generator.getAndIncrement()
            return RequestId(id, call)
        }
    }
}