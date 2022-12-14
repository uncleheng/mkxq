package com.mkxq.business.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * @Description:
 * @author ziheng
 * @date 2022/12/12 18:33
 */
class CustomRecycle: RecyclerView {

    private var mLastX = 0
    private  var mLastY:Int = 0


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    // 外部拦截法：父容器处理冲突
    // 我想要把事件分发给谁就分发给谁
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastX = event.x.toInt()
                mLastY = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX: Int = x - mLastX
                val deltaY: Int = y - mLastY
                if (Math.abs(deltaX) > Math.abs(deltaY)) {    //	横向滑动时拦截
                    return false
                }
            }
            MotionEvent.ACTION_UP -> {}
            else -> {}
        }
        return super.onInterceptTouchEvent(event)
    }

}