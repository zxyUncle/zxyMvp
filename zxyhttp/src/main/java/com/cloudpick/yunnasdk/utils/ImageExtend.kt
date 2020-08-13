package com.cloudpick.yunnasdk.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cloudpick.yunnasdk.NetConfigUtils
import com.cloudpick.yunnasdk.R
import com.cloudpick.yunnasdk.YN

/**
 * Created by zxy on 2020/7/22 13:58
 * ******************************************
 * * 图片加载扩展函数
 * ******************************************
 */
fun ImageView.loadUrl(img: String="") {
    if(YN.mContext!=null) {
        Glide.with(YN.mContext!!).load(NetConfigUtils.YN_IMGURL + img).into(this)
    }
}