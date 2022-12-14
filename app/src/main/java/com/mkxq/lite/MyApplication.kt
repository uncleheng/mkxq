package com.mkxq.lite

import android.app.Activity
import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.mkxq.common.utils.ScreenUtils
import com.tencent.mmkv.MMKV
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.onAdaptListener
import kotlin.properties.Delegates

/**
 * @Description:
 * @author ziheng
 * @date 2022/12/7 15:23
 */
class MyApplication: Application() {

    companion object{
        private var instance: MyApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initARouter()
        initAutoSize()
        initMMKV()
    }

    private fun initMMKV() {
        MMKV.initialize(this)
    }

    /**
     * 横竖版适配，横版根据设计图使用1920 竖版使用1080
     */
    private fun initAutoSize() {
        AutoSizeConfig.getInstance().onAdaptListener = object : onAdaptListener {
            override fun onAdaptBefore(target: Any, activity: Activity?) {
                if (ScreenUtils.isPortrait(activity!!)) {
                    AutoSizeConfig.getInstance().designWidthInDp = ScreenUtils.SCREEN_WITH
                } else {
                    AutoSizeConfig.getInstance().designWidthInDp = ScreenUtils.SCREEN_WITH_HORIZONTAL
                }
            }

            override fun onAdaptAfter(target: Any?, activity: Activity?) {}
        }
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}