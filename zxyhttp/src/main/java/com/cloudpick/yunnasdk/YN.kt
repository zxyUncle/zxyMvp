package com.cloudpick.yunnasdk

import android.content.Context

/**
 * Created by zxy on 2020/8/3 10:32
 * ******************************************
 * * 初始化类
 * ******************************************
 */
class YN {
    companion object {
        var mContext: Context? = null
        val isAllow: Boolean by lazy {
            true
        }

        fun init(mContext: Context?, hostUrl: String) {
            this.mContext = mContext
            BuildConfig.BUILD_TYPE
            NetConfigUtils.YN_HOSTURL = hostUrl
        }
    }
}