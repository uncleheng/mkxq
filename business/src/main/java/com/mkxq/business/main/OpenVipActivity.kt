package com.mkxq.business.main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.mkxq.business.R
import com.mkxq.business.databinding.ActivityOpenvipBinding
import com.mkxq.common.base.BaseActivity

/**
 * @Description:开通会员
 * @author ziheng
 * @date 2022/12/14 20:49
 */
class OpenVipActivity : BaseActivity<ActivityOpenvipBinding>() {
    override fun getLayoutID(): Int = R.layout.activity_openvip

    override fun init() {
        mBind.openvipRecycle.layoutManager = LinearLayoutManager(this)

    }


}