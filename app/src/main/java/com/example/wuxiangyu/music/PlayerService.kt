package com.example.wuxiangyu.music

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews
import com.example.wuxiangyu.first.R
import com.example.wuxiangyu.music.control.IPlayerController
import com.example.wuxiangyu.music.control.LoopMode
import com.example.wuxiangyu.music.control.PlayerListener
import com.example.wuxiangyu.music.control.QUALITY
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashSet

class PlayerService: Service(), IPlayerController {
    val TAG = "PlayerService"

    private var remoteView: RemoteViews? = null
    private var foldRemoteView: RemoteViews? = null
    private lateinit var notificationManager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder
    private var isForeGroundNow = true
    private lateinit var notification_play_bitmap: Bitmap
    private lateinit var notification_pause_bitmap: Bitmap
    private lateinit var closeIntent: Intent
    private lateinit var preIntent: Intent
    private lateinit var nextIntent: Intent
    private lateinit var playOrPauseIntent: Intent
    private val trackList: MutableList<TrackInfo> = ArrayList<TrackInfo>()
    private val trackIndex: Int = 0
    private var playerListenerList = ArrayList<PlayerListener>()
    private val playedTrackStack = Stack<TrackInfo>()
    private var lastNotificationTime = 0L
    private var isInPlayingProcess = false
    private val shuffledTrackSet = LinkedHashSet<TrackInfo>()
    private val playNextTrackSet = LinkedHashSet<TrackInfo>()
    companion object {
        const val TAG = "PlayerService"
//        const val NOTIFICATION_ID = R.string.playerServiceNotificationId
        const val CHANNEL_NAME = "PLAY_SERVICE_CHANNEL"
        const val CHANNEL_ID = "PLAY_SERVICE"
        const val CHANNEL_DESC = "PLAY_SERVICE"

        const val COMMAND_DO_NOTHING = 0x0000
        const val COMMAND_PREV_TRACK = 0x0001
        const val COMMAND_NEXT_TRACK = 0x0002
        const val COMMAND_PLAY_OR_PAUSE = 0x0003
        const val COMMAND_CLOSE_APP = 0x0004

        const val CACHE_SD_PRESERVE = 256 * 1024 * 1024L
        const val CACHE_LOW_LIMIT = 100 * 1024 * 1024L
        const val CACHE_HIGH_LIMIT = 800 * 1024 * 1024L
        const val CACHE_MAX_SECONDS = 5 * 60

        const val SONG_RECORD_TIME = 5 * 1000
    }

    private val pendingCloseIntent: PendingIntent = PendingIntent.getService(this, COMMAND_CLOSE_APP, closeIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    private val pendingPrevTrackIntent = PendingIntent.getService(this, COMMAND_PREV_TRACK, preIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    private val pendingNextTrackIntent = PendingIntent.getService(this, COMMAND_NEXT_TRACK, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    private val peindingPlayOrPauseIntent = PendingIntent.getService(this, COMMAND_PLAY_OR_PAUSE, playOrPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT)


    private var previousTrack: TrackInfo? = null
    private var nextTrack: TrackInfo? = null

    val currentPlaybackState = 1//media回调回来之后的值
    //判断当前是否前台：ActivityLifeHandler来判断，这里加了一个回调。
    val onAppVisbileStateChangeListener = object : ActivityLifecycleHandler.OnVisibleStateChangeListener {
        override fun onVisibleStateStated(visible: Boolean) {
            isForeGroundNow = visible

            if (visible) {//切换到前台
                //一些日志
            } else {//切换到后台

            }
            if (isRealPlaying()) {

            }
        }

    }

    private var loopMode = LoopMode.LOOP_MODE_LIST
    private val musicPlayer: MediaPlayer by lazy { builderMusicPlayer() }

    private fun builderMusicPlayer(): MediaPlayer {
        //创建歌曲播放器
        val musicPlayer = MediaPlayer()
        return musicPlayer
    }

    fun addRecentlyPlayedList() {
        val trackInfo = getCurrentTrack()
        trackInfo?.let {
            val uid = ""
            if (true) {
                //有歌词
                playerListenerList.forEach {
                    it.onLoadLyricSuccess("")
                }
            }

        }
    }


    override fun loadLyric() {
        val currentTrack = getCurrentTrack()?: return
        val trackId = currentTrack.id
//        val fileLyric

        getLyricFromServer(currentTrack)
    }

    private fun getLyricFromServer(currentTrack: TrackInfo) {
        //去网络加载歌词信息

    }


    private var trackDetailBeginTime = 0L
    override fun loadData(needPlay: Boolean) {//加载歌曲的相关信息PlayInfo
        val currenTrack = getCurrentTrack()?: return

        if (needPlay) {
            playerListenerList.forEach {
                it.onLoadPlayInfoStart(currenTrack)
            }
        }
        trackDetailBeginTime = System.currentTimeMillis()
        loadLyric()
        //去网络获取信息，
        if (true) {
            //PlayerInfo信息获取成功，里面包含了视频播放信息。
            playerListenerList.forEach {
                it.onLoadPlayInfoSuccess(currenTrack)
            }
            //如果needplay，则playTrack。
        } else {
            playerListenerList.forEach {
                it.onLoadPlayInfoFailed(currenTrack)
            }
        }


    }
    private fun playTrack(data: PlayerInfo) {
        realPlay()
    }
    private fun realPlay() {
//        musicPlayer.play()

//        isInplayingProcess = true
    }

    private val musicListener = object: VideoEngineListener {
        override fun onComplete() {
            playNext()
        }

        override fun onRenderStart() {
            playerListenerList.forEach {
                val currentTrack = getCurrentTrack()?: return
                it.onRenderStart(currentTrack)
            }
        }

        override fun onPrepare() {
            //准备好了
        }

        override fun onPrepared() {
            //准备好了
        }

        override fun onBufferingUpdate(percent: Int) {
            //缓存百分比
        }

        override fun onPlaybackStateChange() {
            //播放状态改变
            //1.播放，修改notification
            //2.暂停，修改notification
            //2.stop，修改notification

        }

        override fun onLoadStateChanged() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onError() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
    private fun playNext() {
        privateChangeToNextTrack()
        play()
    }

    private fun privateChangeToNextTrack() {
        realChangeToNextTrack(true)
    }
    override fun play() {
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
        return false
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
        realChangeToNextTrack(false)
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

    override fun onRebind(intent: Intent?) {
        Log.e(TAG, "onRebind")
        super.onRebind(intent)
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

    private fun generatePrevAndNextTrack() {
        //第一次初始化歌曲的时候，需要预加载
        if (playedTrackStack.isNotEmpty()) {
            previousTrack = playedTrackStack.peek()
        } else {
            val preIndex = getPrevTrackIndex(trackIndex, false)
            previousTrack = trackList[preIndex]
        }

        if (playNextTrackSet.isNotEmpty()) {
            nextTrack = playNextTrackSet.elementAt(playNextTrackSet.size - 1)

        } else {
            val nextIndex = getNextTrackIndex(trackIndex, false)
            nextTrack = trackList[nextIndex]

        }
    }
    private fun getNextTrackIndex(currentIndex: Int, auto: Boolean): Int {
        var nextIndex = currentIndex
        do {
            nextIndex = if (auto) {
                when (loopMode) {
                    LoopMode.LOOP_MODE_SINGLE -> nextIndex
                    LoopMode.LOOP_MODE_LIST -> nextIndex + 1
                    LoopMode.LOOP_MODE_SHUFFLE -> getShuffleNextTrackIndex()
                }
            } else {
                when (loopMode) {
                    LoopMode.LOOP_MODE_SINGLE, LoopMode.LOOP_MODE_LIST -> nextIndex + 1
                    LoopMode.LOOP_MODE_SHUFFLE -> getShuffleNextTrackIndex()
                }
            }
            while (nextIndex < 0) nextIndex += trackList.size
            nextIndex = nextIndex % trackList.size

        } while (!trackList[nextIndex].isPlayable)
        return nextIndex
    }

    private fun getPrevTrackIndex(currentIndex: Int, auto: Boolean): Int {
        var preIndex = currentIndex
        do {
            preIndex = if (auto) {
                when (loopMode) {
                    LoopMode.LOOP_MODE_SINGLE -> preIndex
                    LoopMode.LOOP_MODE_LIST -> preIndex - 1
                    LoopMode.LOOP_MODE_SHUFFLE -> getShufflePrevTrackIndex()
                }
            } else {
                when (loopMode) {
                    LoopMode.LOOP_MODE_SINGLE, LoopMode.LOOP_MODE_LIST -> preIndex - 1
                    LoopMode.LOOP_MODE_SHUFFLE -> getShufflePrevTrackIndex()
                }
            }

            while (preIndex < 0) preIndex += trackList.size
            preIndex = preIndex % trackList.size

        } while (!trackList[preIndex].isPlayable)
        return preIndex
    }

    private var actionNextTrack = false
    private fun getShuffleNextTrackIndex(isActionNext: Boolean = true): Int {
        actionNextTrack = isActionNext
        if (playNextTrackSet.isNotEmpty()) {//下一首的stack中有值，则在stack中获取
            val track = playNextTrackSet.elementAt(playNextTrackSet.size - 1)
            playNextTrackSet.remove(track)
            val index = trackList.indexOf(track)
            if (index >= 0) {
                return index
            } else {
                //no
            }
        }

        val remainedTrackList = ArrayList<TrackInfo>()
        remainedTrackList.addAll(trackList)
        remainedTrackList.removeAll(shuffledTrackSet)
        val iterator = remainedTrackList.iterator()
        while (iterator.hasNext()) {
            val track = iterator.next()
            if (!track.isPlayable) {
                iterator.remove()
            }
        }
        //如果所有歌曲都shuffle过了，那么就重新开始来过。
        if (remainedTrackList.isEmpty()) {
            shuffledTrackSet.clear()
            remainedTrackList.addAll(trackList)
        }
        val randomNum = Random().nextInt(remainedTrackList.size)
        val track = remainedTrackList[randomNum]
        return trackList.indexOf(track)

    }

    private fun getShufflePrevTrackIndex(): Int {//寻找之前这一首歌
        if (playedTrackStack.isEmpty()) {
            //之前播放过的为空的话，
            return getShuffleNextTrackIndex(false)
        } else {
            val track = playedTrackStack.pop()
            val index = trackList.indexOf(track)
            if (index >= 0) {
                return index
            } else {
                return getShuffleNextTrackIndex(false)
            }
        }
    }

    private fun getNotification(track: TrackInfo): Notification {
        remoteView = RemoteViews(packageName, R.layout.playing_service_notification)
        foldRemoteView = RemoteViews(packageName, R.layout.playing_service_notification_foldview)
        return builder.build()
    }

    fun setCurrentTrack(track: TrackInfo) {
    }
    private fun realChangeToNextTrack(auto: Boolean) {
        val currentTrack = getCurrentTrack()?: return
        if (loopMode == LoopMode.LOOP_MODE_SHUFFLE) {
            if (playNextTrackSet.isNotEmpty()) {
                val track = playNextTrackSet.elementAt(playNextTrackSet.size - 1)
                setCurrentTrack(track)
                playNextTrackSet.remove(track)
                if (playNextTrackSet.isNotEmpty()) {
                    nextTrack = playedTrackStack.elementAt(playNextTrackSet.size - 1)

                } else {
                    nextTrack = trackList[getNextTrackIndex(trackIndex, false)]
                }
                previousTrack = currentTrack
            } else {
                setCurrentTrack(nextTrack!!)
                previousTrack = currentTrack
                nextTrack = trackList[getNextTrackIndex(trackIndex, false)]

            }
            actionNextTrack = true
        } else if (loopMode == LoopMode.LOOP_MODE_LIST) {

        }
    }
}