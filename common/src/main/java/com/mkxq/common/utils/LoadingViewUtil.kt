package com.mkxq.common.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.mkxq.common.R

/**
 * @Description:页面展示loading
 * @author ziheng
 * @date 2022/12/5 16:07
 */
object LoadingViewUtil {
    private var dialog: AlertDialog? = null

    fun showLoadingDialog(context: Context, isCancel: Boolean) {

        if (context is Activity) {
            if (context.isFinishing) {
                return
            }
        }

        if (dialog == null) {
            val b = AlertDialog.Builder(context)
            b.setView(
                LayoutInflater.from(context).inflate(R.layout.common_loading_dialog, null)
            )

            b.setCancelable(isCancel)
            dialog = b.create()

            if (dialog?.window == null) {
                return
            }

            dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        }

        if (dialog != null && !(dialog!!.isShowing)) {
            dialog!!.show()
        }

    }

    fun dismissLoadingDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog?.dismiss()
            dialog = null
        }
    }
}