package com.example.wuxiangyu.music

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.wuxiangyu.MyApp
import com.example.wuxiangyu.music.control.IPlayerController
import com.example.wuxiangyu.music.control.LoopMode
import com.example.wuxiangyu.music.control.PlayerListener
import com.example.wuxiangyu.music.control.QUALITY

class LocalBinder(val pService: PlayerService): Binder() {
    fun getService(): PlayerService {
        return pService
    }
}
object PlayerController: IPlayerController {
    @Volatile
    private var playService: IPlayerController? = null
    private const val TAG = "PlayerController"

    private var hasHintCache = false//todo
    private var localBinder: LocalBinder? = null
    private var bufferPercent: Int = 0
    private var shouldUnbind = false
    var shouldShowVideo = false
    private var skipNetworkCheckThisTime = false

    private var deathRecepient = object: IBinder.DeathRecipient {
        override fun binderDied() {
            //死亡回调,
            localBinder?.unlinkToDeath(this, 0)
            localBinder = null
            playService = null
            bindPlayerService(MyApp.instance)
        }

    }
    val conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

            //todo
             removePlayerListener(playerListener)
            localBinder?.unlinkToDeath(deathRecepient, 0)
            localBinder = null
            playService = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //todo
            localBinder = service as LocalBinder
            playService = localBinder?.getService()
            addPlayerListener(playerListener)
            localBinder?.linkToDeath(deathRecepient, 0)
//            playService?.getCurrentTrack()?.


        }

    }
    enum class LoadingSate {
        LOAD_STATE_PLAYSABLE,  LOAD_STATE_STALLED, LOAD_STATE_ERROR, LOAD_STATE_NET_START, LOAD_STATE_NET_SUCCESS, LOAD_STATE_NET_FAIL
    }


    enum class PlaybackState {
        PLAYBACK_STATE_START, PLAYBACK_STATE_PLAYING, PLAYBACK_STATE_PAUSED, PLAYBACK_STATE_STOPPED, PLAYBACK_STATE_ERROR;
    }
    fun bindPlayerService(context: Context) {
        if (context.bindService(Intent(context, PlayerService::class.java), conn, Context.BIND_AUTO_CREATE)) {
            shouldUnbind = true
        }
    }
    private fun startPlayerService(context: Context) {
        context.startService(Intent(context, PlayerService::class.java))
    }
    var playerListener = object : PlayerListener {
        override fun onPlayStart(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onRenderStart(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPrepared(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCompletion(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPrepare(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBufferingUpdate(track: TrackInfo, percent: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onVidewStatusException(status: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onError(error: Exception) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLoadStateChanged(track: TrackInfo, loadState: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLoadPlayInfoStart(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLoadPlayInfoSuccess(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLoadPlayInfoFailed(track: TrackInfo) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLoadLyricSuccess(lyric: String) {
            Log.e("xxxx", "歌词加载成功")
        }

    }

    fun checkNetworkBefor(context: Context, nextStep: () -> Unit) {

    }
    private fun checkServiceAlive() {
        val serviceAlive = playService != null && localBinder != null && localBinder?.isBinderAlive ?: false
        if (serviceAlive) {

        }
        if (!serviceAlive) {
            startPlayerService(MyApp.instance)
            bindPlayerService(MyApp.instance)
        }
    }


    override fun play() {
        checkServiceAlive()
        playService?.play()
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
}