package com.example.wuxiangyu.music

interface VideoEngineListener {
    fun onPrepare()//准备
    fun onPrepared()//准备好了

    fun onRenderStart()//开始渲染了
    fun onBufferingUpdate(percent: Int)
    fun onPlaybackStateChange()//播放状态改变，总共4种：
    fun onLoadStateChanged()
    fun onError()
    fun onComplete()

}