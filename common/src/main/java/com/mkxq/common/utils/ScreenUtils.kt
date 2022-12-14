package com.mkxq.common.utils

import android.content.Context
import android.content.res.Configuration

/**
 * @Description:
 * @author ziheng
 * @date 2022/12/8 11:24
 */
object ScreenUtils {
    /**
     * 是否是竖屏
     *
     * @param context
     * @return true:是竖屏；false:是横屏
     */
    fun isPortrait(context: Context): Boolean {
        val configuration = context.resources.configuration //获取设置的配置信息
        return configuration.orientation != Configuration.ORIENTATION_LANDSCAPE
    }

    /**
     * 竖屏的屏幕宽度
     */
    const val SCREEN_WITH = 1920

    /**
     * 横屏的屏幕宽度
     */
    const val SCREEN_WITH_HORIZONTAL = 1920

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context?, dpValue: Float): Int {
        if (context == null) {
            return dpValue.toInt()
        }
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context?, pxValue: Float): Int {
        if (context == null) {
            return pxValue.toInt()
        }
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}