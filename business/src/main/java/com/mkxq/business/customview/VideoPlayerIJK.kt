package com.mkxq.business.customview

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.FrameLayout
import tv.danmaku.ijk.media.player.IMediaPlayer
import android.view.SurfaceView
import androidx.annotation.AttrRes
import android.view.Gravity
import android.view.SurfaceHolder
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import java.io.IOException

/**
 * @author ziheng
 * @Description:
 * @date 2022/12/8 15:26
 */
class VideoPlayerIJK : FrameLayout {
    /**
     * 由ijkplayer提供，用于播放视频，需要给他传入一个surfaceView
     */
    private var mMediaPlayer: IMediaPlayer? = null

    /**
     * 视频文件地址
     */
    private var mPath = ""
    private var surfaceView: SurfaceView? = null
    private var listener: VideoPlayerListener? = null
    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        initVideoView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initVideoView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initVideoView(context)
    }

    private fun initVideoView(context: Context) {
        mContext = context

        //获取焦点，不知道有没有必要~。~
        isFocusable = true
    }

    /**
     * 设置视频地址。
     * 根据是否第一次播放视频，做不同的操作。
     *
     * @param path the path of the video.
     */
    fun setVideoPath(path: String) {
        if (TextUtils.equals("", mPath)) {
            //如果是第一次播放视频，那就创建一个新的surfaceView
            mPath = path
            createSurfaceView()
        } else {
            //否则就直接load
            mPath = path
            load()
        }
    }

    /**
     * 新建一个surfaceview
     */
    private fun createSurfaceView() {
        //生成一个新的surface view
        surfaceView = SurfaceView(mContext)
        surfaceView!!.holder.addCallback(LmnSurfaceCallback())
        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER
        )
        surfaceView!!.layoutParams = layoutParams
        this.addView(surfaceView)
    }

    /**
     * surfaceView的监听器
     */
    private inner class LmnSurfaceCallback : SurfaceHolder.Callback {
        override fun surfaceCreated(holder: SurfaceHolder) {}
        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            //surfaceview创建成功后，加载视频
            load()
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {}
    }

    /**
     * 加载视频
     */
    private fun load() {
        //每次都要重新创建IMediaPlayer
        createPlayer()
        try {
            mMediaPlayer!!.dataSource = mPath
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //给mediaPlayer设置视图
        mMediaPlayer!!.setDisplay(surfaceView!!.holder)
        mMediaPlayer!!.prepareAsync()
    }

    /**
     * 创建一个新的player
     */
    private fun createPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.setDisplay(null)
            mMediaPlayer!!.release()
        }
        val ijkMediaPlayer = IjkMediaPlayer()
        IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG)

//        //开启硬解码
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1)
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "dns_cache_clear", 1)
        mMediaPlayer = ijkMediaPlayer
        if (listener != null) {
            mMediaPlayer?.setOnPreparedListener(listener)
            mMediaPlayer?.setOnInfoListener(listener)
            mMediaPlayer?.setOnSeekCompleteListener(listener)
            mMediaPlayer?.setOnBufferingUpdateListener(listener)
            mMediaPlayer?.setOnErrorListener(listener)
        }
    }

    fun setListener(listener: VideoPlayerListener?) {
        this.listener = listener
        if (mMediaPlayer != null) {
            mMediaPlayer!!.setOnPreparedListener(listener)
        }
    }

    /**
     * -------======--------- 下面封装了一下控制视频的方法
     */
    fun start() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.start()
        }
    }

    fun release() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.reset()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    fun pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.pause()
        }
    }

    fun stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
        }
    }

    fun reset() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.reset()
        }
    }

    val duration: Long
        get() = if (mMediaPlayer != null) {
            mMediaPlayer!!.duration
        } else {
            0
        }
    val currentPosition: Long
        get() = if (mMediaPlayer != null) {
            mMediaPlayer!!.currentPosition
        } else {
            0
        }

    fun seekTo(l: Long) {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.seekTo(l)
        }
    }
}