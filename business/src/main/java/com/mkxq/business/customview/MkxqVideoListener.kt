package com.mkxq.business.customview

/**
 * @Description:
 * @author ziheng
 * @date 2022/12/9 17:58
 */
interface MkxqVideoListener {
    /**
     * 准备就绪状态
     */
    fun onStatePreparing()

    /**
     * 切换下一首
     */
    fun onNextVideo()

    /**
     * 切换上一首
     */
    fun onLastVideo()
}