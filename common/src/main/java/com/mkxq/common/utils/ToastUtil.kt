package com.mkxq.common.utils

import android.app.Application
import android.widget.Toast

/**
 * @Description:
 * @author ziheng
 * @date 2022/12/5 17:47
 */
object ToastUtil {
    lateinit var mContext: Application
    fun init(context: Application){
        mContext= context
    }

    fun showMsg(str:String){
        Toast.makeText(mContext,str, Toast.LENGTH_SHORT).show()
    }
}