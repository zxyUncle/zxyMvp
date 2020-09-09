package com.cloudpick.yunnasdk

import android.content.Context
import com.cloudpick.yunnasdk.tools.FilesUtils
import com.cloudpick.yunnasdk.utils.AppUtils

/**
 * Created by zxy on 2020/8/3 10:32
 * ******************************************
 * * 初始化类
 * ******************************************
 */
class YN {
    companion object {
        var mContext: Context?=null
        val isAllow: Boolean by lazy {
            if (mContext==null){
                false
            }else{
                FilesUtils.initAssets(mContext,AppUtils.getPackageName(mContext))
            }
        }

        fun init(mContext: Context?) {
            this.mContext = mContext
        }
    }
}