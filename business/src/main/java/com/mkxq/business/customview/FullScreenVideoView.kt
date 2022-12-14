package com.mkxq.business.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * @Description:
 * @author ziheng
 * @date 2022/12/9 14:36
 */
class FullScreenVideoView: VideoView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec))
    }
}