package com.mkxq.business.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.mkxq.business.R
import com.mkxq.common.utils.Constants
import com.mkxq.common.utils.KVUtil


/**
 * @Description:自定义播放器
 * @author ziheng
 * @date 2022/12/9 16:37
 */
class MkxqVideoView: JzvdStd{

    private val TAG = "MkxqVideoView"
    private var listener: MkxqVideoListener? = null
    private var lastVideoBut: ImageButton? = null
    private var nextVideoBut: ImageButton? = null
    private var menuView: ConstraintLayout? = null
    //是否展示菜单
    private var isShowMenu: Boolean = false

    constructor(context: Context) : super(context) {
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    override fun getLayoutId(): Int {
        return R.layout.jz_video_layout
    }

    override fun init(context: Context?) {
        super.init(context)
        lastVideoBut = findViewById(R.id.last_video)
        nextVideoBut = findViewById(R.id.next_video)
        menuView = findViewById(R.id.menu_con)
        lastVideoBut?.setOnClickListener(this)
        nextVideoBut?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        val id = v?.id
        if(id == R.id.last_video){
            listener?.onLastVideo()
            menuView?.visibility = View.GONE
        }else if(id == R.id.next_video){
            listener?.onNextVideo()
            menuView?.visibility = View.GONE
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val id = v?.id
        if (id == R.id.surface_container) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {}
                MotionEvent.ACTION_MOVE -> {}
                MotionEvent.ACTION_UP -> {
                    if (state == STATE_PLAYING) {
                        Log.d(Jzvd.TAG, "pauseVideo [" + this.hashCode() + "] ")
                        menuView?.visibility = View.VISIBLE
//                        mediaInterface.pause()
//                        onStatePause()
                    } else if (state == STATE_PAUSE) {
                        menuView?.visibility = View.GONE
//                        mediaInterface.start()
//                        onStatePlaying()
                    }
                }
            }
        }
        return false
    }

    override fun touchActionUp() {
        super.touchActionUp()

    }


    fun setOnStateListener(listener: MkxqVideoListener){
        this.listener = listener
    }

    override fun onStatePreparing() {
        super.onStatePreparing()
        val isFirstOpenApp = KVUtil.getBoolean(Constants.FIRST_OPEN, true)
        //第一次打开引导用户滑动切换视频
        if(isFirstOpenApp){
            listener?.onStatePreparing()
            KVUtil.put(Constants.FIRST_OPEN,false)
        }
        Log.i(TAG, "onStatePreparing: ")
    }

    override fun onStatePlaying() {
        super.onStatePlaying()
        Log.i(TAG, "onStatePlaying: ")
    }

    override fun onStatePreparingPlaying() {
        super.onStatePreparingPlaying()
        Log.i(TAG, "onStatePreparingPlaying: ")
    }

    /**
     * 是否隐藏上一首
     */
    fun setGoneLeftButton(isGone: Boolean){
        lastVideoBut?.isGone = isGone
    }
    fun setGoneRightButton(isGone: Boolean){
        nextVideoBut?.isGone = isGone
    }

    override fun setScreenNormal() {
        super.setScreenNormal()
        Log.i(TAG, "setScreenNormal: ")
    }

    override fun setScreenFullscreen() {
        super.setScreenFullscreen()
        Log.i(TAG, "setScreenFullscreen: ")
    }

}