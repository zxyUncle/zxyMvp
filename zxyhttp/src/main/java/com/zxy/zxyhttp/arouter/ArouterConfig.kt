package com.zxy.zxyhttp.arouter

import android.content.Context
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.zxy.zxyhttp.R

/**
 * Created by zxy on 2019/9/26-13:50
 * Class functions
 * ******************************************
 * * 路由配置
 * ******************************************
 */
class ArouterConfig {
    companion object {
        //ZXY 路径
        //app模块主路由
        const val appModule: String = "/app/"
        //uikit模块主路由 -网易云信界面
        const val uikitModule: String = "/wy/"




        //登录
        const val LOGIN: String = appModule + "login"
        //主页
        const val HOME: String = appModule + "home"




    }

}