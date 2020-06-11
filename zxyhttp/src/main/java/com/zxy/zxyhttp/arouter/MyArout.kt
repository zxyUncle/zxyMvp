package com.zxy.zxyhttp.arouter

import android.content.Context
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.zxy.zxyhttp.R

/**
 * Created by zxy on 2020/4/2 0002 19:29
 * ******************************************
 * * Arout跳转
 * ******************************************
 */
/**
 * 普通跳转
 * @param url String
 * @param mContext Context
 * @param bundle Bundle
 */
fun Any.startArout(mContext: Context, url: String, bundle: Bundle? = null) {
    ARouter.getInstance()
        .build(url)
        .withBundle("key", bundle)
        .navigation(mContext)
}


/**
 * 左出又进的动画跳转
 * @param url String
 * @param mContext Context
 */
fun Any.startAroutLeft(mContext: Context, url: String, bundle: Bundle? = null) {
    var makeCustomAnimation = ActivityOptionsCompat
        .makeCustomAnimation(mContext, R.anim.translate_out, R.anim.translate_in)
    ARouter.getInstance()
        .build(url)
        .withBundle("key", bundle)
        .withOptionsCompat(makeCustomAnimation)
        .navigation(mContext)
}

/**
 * 上出下进的动画跳转
 * @param url String
 * @param mContext Context
 */
fun Any.startAroutTop(mContext: Context, url: String, bundle: Bundle? = null) {
    var makeCustomAnimation = ActivityOptionsCompat
        .makeCustomAnimation(mContext, R.anim.translate_top, R.anim.translate_bottom)
    ARouter.getInstance()
        .build(url)
        .withBundle("key", bundle)
        .withOptionsCompat(makeCustomAnimation)
        .navigation(mContext)
}