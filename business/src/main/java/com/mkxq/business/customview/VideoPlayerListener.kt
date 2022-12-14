package com.mkxq.business.customview

import tv.danmaku.ijk.media.player.IMediaPlayer

/**
 * @author ziheng
 * @Description:
 * @date 2022/12/8 15:29
 */
interface VideoPlayerListener: IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener,
    IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnErrorListener