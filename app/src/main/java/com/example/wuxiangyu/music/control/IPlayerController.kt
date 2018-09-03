package com.example.wuxiangyu.music.control

import com.example.wuxiangyu.music.TrackInfo

interface IPlayerController {
    fun play()
    fun loadData(needPlay: Boolean)
    fun loadLyric()
    fun stop()
    fun pause()
    fun seekTo(progress: Float)
    fun isSeeking(trackInfo: TrackInfo?): Boolean
    fun isInplayingProcess(): Boolean
    fun isRealPlaying(): Boolean
    fun isStopped(): Boolean
    fun isPaused(): Boolean
    fun getPlaybackState(): Int
    fun getCurrentTrack(): TrackInfo?
    fun getCurrentPlayingTrack(): TrackInfo?
    fun getTrackDurationTime(): Int
    fun getTrackPlaybackTime(): Int
    fun getTrackProgress(): Float
    fun setPlayList(trackList: List<TrackInfo>)
    fun getPlayList(): List<TrackInfo>
    fun setCurrentTrackIndex(index: Int)
    fun getCurrentTrackIndex(): Int
    fun changeToNextTrack()
    fun changeToPrevTrack()
    fun isCacheEnough(): Boolean
    fun insertToNextPlay(trackInfo: TrackInfo): Int
    fun insertToTrackList(trackInfo: TrackInfo): Int
    fun addPlayerListener(playerListener: PlayerListener)
    fun removePlayerListener(playerListener: PlayerListener)
    fun changeToNexyLoopMode(): LoopMode
    fun getCurrentLoopMode(): LoopMode
    fun configQuality(quality: QUALITY)
    fun getPreTrack(): TrackInfo?
    fun getNextTrack(): TrackInfo?

}