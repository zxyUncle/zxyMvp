package com.cloudpick.yunnasdk

import android.content.Intent

/**
 * Created by ZXY on 2019/9/10 14:01.
 * Class functions
 * *********************************************************
 * * 网路配置
 * *********************************************************
 */
class NetConfigUtils {
    companion object {
        //网路请求的配置
        var YN_HOSTURL = "http://10.10.10.130/"//服务器地址
        var YN_IMGURL = "http://img.yunatop.com/"//图片地址
        val YN_STATUS = "code"         //成功失败的code或者status，根据服务器不同，变为不同
        val YN_MSG = "message"             //失败提示，根据服务器不同，变为不同
        val YN_TAG = "yunna"         //网路请求数据显示的Logcat 日志过滤TAG
        val YN_SUCC = "0000"        //响应成功
        val YN_Fail = "-255"        //响应token失效回调--如果没有就可以随便填
        var YN_INTENT_LOGIN: Intent? = null  //Token失效自动跳转到指定Activity

    }

}