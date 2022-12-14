package com.mkxq.business.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mkxq.business.R

/**
 * @Description:开通会员item
 * @author ziheng
 * @date 2022/12/14 20:58
 */
class OpenVipAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_openvip) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.item_openvip_textview,"hahah")
    }
}