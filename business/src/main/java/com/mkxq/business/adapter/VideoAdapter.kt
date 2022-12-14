package com.mkxq.business.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mkxq.business.R

/**
 * @Description:视频列表adapter
 * @author ziheng
 * @date 2022/12/7 16:49
 */
class VideoAdapter: BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_video_layout) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.video_item,item)
    }
}