package com.mkxq.business.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar

/**
 * @Description:
 * @author ziheng
 * @date 2022/12/12 18:40
 */
@SuppressLint("AppCompatCustomView")
class CustomSeekbar: SeekBar {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun setOnTouchListener(l: OnTouchListener?) {
//        l?.onTouch()
        super.setOnTouchListener(l)
    }
}