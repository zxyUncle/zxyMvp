package com.zxy.httpnet.utils

import com.zxy.zxyhttp.arouter.ArouterConfig

/**
 * Created by ZXY on 2019/9/10 14:01.
 * Class functions
 * *********************************************************
 * * 网路配置
 * *********************************************************
 */
open class NetConfigUtils {
    /**
     * 单例模式
     */
    companion object {
        val instance: NetConfigUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetConfigUtils()
        }
    }

    var hostUrl = "https://pre.cuttlefish.vip/" //服务器地址
    var status = "code"         //成功失败的code或者status，根据服务器不同，变为不同
    var msg = "msg"             //失败提示，根据服务器不同，变为不同
    var TAG = "zxyhttp"         //网路请求数据显示的Logcat 日志过滤TAG
    var responseSucc = 200        //响应成功
    var responseTokenFail = 205        //响应token失效回调--如果没有就可以随便填
    var loginActivity:String = ArouterConfig.LOGIN
//    var loginActivity = LoginActivity::class.java      //Token失效自动登录的类
}