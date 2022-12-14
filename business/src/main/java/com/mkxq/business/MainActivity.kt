package com.mkxq.business

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import cn.jzvd.Jzvd
import com.alibaba.android.arouter.facade.annotation.Route
import com.mkxq.business.adapter.VideoAdapter
import com.mkxq.business.databinding.ActivityMainBinding
import com.mkxq.business.layoutmanager.OnViewPagerListener
import com.mkxq.business.layoutmanager.ViewPagerLayoutManager
import com.mkxq.business.customview.MkxqVideoListener
import com.mkxq.business.customview.MkxqVideoView
import com.mkxq.common.base.BaseActivity
import com.mkxq.common.utils.ARoutePath


/**
 * @Description:首页
 * @author ziheng
 * @date 2022/12/7 14:39
 */
@Route(path = ARoutePath.PATH_MAIN)
class MainActivity: BaseActivity<ActivityMainBinding>(),MkxqVideoListener{

    override fun getLayoutID(): Int = R.layout.activity_main

    private var viewPagerLayoutManager: ViewPagerLayoutManager? = null
    private var videoView: MkxqVideoView? = null
    private var ivCurCover: ImageView? = null

    private var initPos = 0
    /** 当前播放视频位置  */
    private var curPlayPos = -1
    private var videoList = ArrayList<String>()

    override fun init() {
        val videoAdapter = VideoAdapter()
        videoList.add("http://v-cdn.zjol.com.cn/280443.mp4")
        videoList.add("https://imaker-video.oss-cn-beijing.aliyuncs.com/prod/video/%E9%9B%B7%E7%9F%B3/%E6%92%AD%E6%94%BE/480P/7510072.ts")
        videoList.add("https://imaker-video.oss-cn-beijing.aliyuncs.com/prod/video/%E9%9B%B7%E7%9F%B3/%E6%92%AD%E6%94%BE/480P/7351600.ts")
        videoList.add("https://imaker-video.oss-cn-beijing.aliyuncs.com/prod/video/%E8%B4%9D%E7%93%A6/%E6%92%AD%E6%94%BE/1080P/30%20%E7%9B%B4%E5%8D%87%E6%9C%BA-%20%E4%B8%AD%E6%96%87.mp4")
        videoList.add("https://imaker-video.oss-cn-beijing.aliyuncs.com/prod/video/%E8%B4%9D%E7%93%A6/%E6%92%AD%E6%94%BE/1080P/MV25.Salmon%27s%20Return.mp4")
        videoList.add("https://imaker-video.oss-cn-beijing.aliyuncs.com/prod/video/%E9%9B%B7%E7%9F%B3/%E6%92%AD%E6%94%BE/480P/7508826.ts")
        videoAdapter.setNewInstance(videoList)
        videoView = MkxqVideoView(this)
        videoView?.setOnStateListener(this)
        setViewPagerLayoutManager()
        mBind.mainRecycle.adapter = videoAdapter

    }

    private fun setViewPagerLayoutManager() {
        viewPagerLayoutManager = ViewPagerLayoutManager(this)
        mBind.mainRecycle.layoutManager = viewPagerLayoutManager
        mBind.mainRecycle.scrollToPosition(initPos)
        viewPagerLayoutManager!!.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {
                playCurVideo(initPos)
                Log.i(TAG, "onInitComplete: ")
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
//                if (ivCurCover != null) {
//                    ivCurCover?.visibility = View.VISIBLE
//                }
//                if (curPlayPos == position) {
//                    Jzvd.releaseAllVideos()
//                }
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                if (curPlayPos == position) {
                    return
                }
                playCurVideo(position)
                curPlayPos = position
                Log.i(TAG, "onInitComplete: ")
            }
        })
    }

    private fun playCurVideo(position: Int) {
        if (mBind.mainRecycle.getChildAt(0) == null || position == curPlayPos) {
            return
        }
        val itemView = viewPagerLayoutManager!!.findViewByPosition(position) ?: return
        val rootView = itemView.findViewById<ViewGroup>(R.id.item_frame)
        val ivCover = rootView.findViewById<ImageView>(R.id.item_cover)
        curPlayPos = position

        //切换播放器位置
        dettachParentView(rootView)
        autoPlayVideo(curPlayPos, ivCover)
    }

    /**
     * 移除videoview父view
     */
    private fun dettachParentView(rootView: ViewGroup) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        videoView?.parent?.let {
            (it as ViewGroup).removeView(videoView)
        }
        videoView?.progressBar?.progress = 0
        videoView?.currentTimeTextView?.text = "00:00"
        rootView.addView(videoView, 0)
    }

    /**
     * 自动播放视频
     */
    private fun autoPlayVideo(position: Int, ivCover: ImageView) {
        Log.i(TAG, "autoPlayVideo: $position")
        videoView?.setGoneLeftButton(position == 0)
        videoView?.setUp(videoList[position],"视频标题")
        videoView?.startVideo()
        videoView?.posterImageView?.setImageDrawable(resources.getDrawable(R.drawable.ic_launcher_foreground))
    }

    override fun onBackPressed() {
        if(Jzvd.backPress()) return
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.goOnPlayOnPause()
    }

    override fun onResume() {
        Jzvd.goOnPlayOnResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        Jzvd.releaseAllVideos()
    }

    /**
     * 下一曲
     */
    fun gotoNextVideo() {
        if(curPlayPos < videoList.size){
            val i = curPlayPos + 1
            mBind.mainRecycle.smoothScrollToPosition(i)
        }
    }

    /**
     * 上一曲
     */
     fun gotoLastVideo() {
        if(curPlayPos > 0){
            val i = curPlayPos - 1
            viewPagerLayoutManager?.smoothScrollToPosition(mBind.mainRecycle,null,i)
            mBind.mainRecycle.smoothScrollToPosition(i)
        }
    }

    override fun onStatePreparing() {
        mBind.mainRecycle.smoothScrollBy(500,0)
    }

    /**
     * 下一曲
     */
    override fun onNextVideo() {
        gotoNextVideo()
    }

    /**
     * 上一曲
     */
    override fun onLastVideo() {
        gotoLastVideo()
    }

}