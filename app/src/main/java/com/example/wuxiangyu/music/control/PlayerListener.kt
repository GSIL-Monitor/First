package com.example.wuxiangyu.music.control

import com.example.wuxiangyu.music.TrackInfo

interface PlayerListener {

    fun onPlayStart(track: TrackInfo)
    fun onRenderStart(track: TrackInfo)
    fun onPrepared(track: TrackInfo)
    fun onPlaybackStateChanged(playbackState: Int)
    fun onCompletion(track: TrackInfo)
    fun onPrepare(track: TrackInfo)
    fun onBufferingUpdate(track: TrackInfo, percent: Int)
    fun onVidewStatusException(status: Int)
    fun onError(error: Exception)
    fun onLoadStateChanged(track: TrackInfo, loadState: Int)
    fun onLoadPlayInfoStart(track: TrackInfo)
    fun onLoadPlayInfoSuccess(track: TrackInfo)
    fun onLoadPlayInfoFailed(track: TrackInfo)
    fun onLoadLyricSuccess(lyric: String)
}