package com.example.wuxiangyu.music

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.wuxiangyu.music.control.IPlayerController
import com.example.wuxiangyu.music.control.LoopMode
import com.example.wuxiangyu.music.control.PlayerListener
import com.example.wuxiangyu.music.control.QUALITY

class PlayerService: Service(), IPlayerController {
    val TAG = "PlayerService"
    override fun play() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData(needPlay: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadLyric() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun seekTo(progress: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isSeeking(trackInfo: TrackInfo?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInplayingProcess(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isRealPlaying(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isStopped(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isPaused(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlaybackState(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentTrack(): TrackInfo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentPlayingTrack(): TrackInfo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackDurationTime(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackPlaybackTime(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrackProgress(): Float {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPlayList(trackList: List<TrackInfo>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayList(): List<TrackInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCurrentTrackIndex(index: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentTrackIndex(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeToNextTrack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeToPrevTrack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCacheEnough(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertToNextPlay(trackInfo: TrackInfo): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertToTrackList(trackInfo: TrackInfo): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addPlayerListener(playerListener: PlayerListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removePlayerListener(playerListener: PlayerListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeToNexyLoopMode(): LoopMode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentLoopMode(): LoopMode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configQuality(quality: QUALITY) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPreTrack(): TrackInfo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNextTrack(): TrackInfo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG, "onBind")
        return LocalBinder(this)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        Log.e(TAG, "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        super.onDestroy()
    }

}