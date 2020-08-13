package com.zxy.zxymvp

import android.app.Application
import com.cloudpick.yunnasdk.YN
import com.zxy.zxymultilingual.MultiLanguageUtil


/**
 * Created by zxy on 2020/7/22 9:57
 * ******************************************
 * *
 * ******************************************
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化多语言
        MultiLanguageUtil.init(this)
        //初始化网路框架
        //根据变种版本，更换服务器地址，在build中
        //也可以直接写String服务器地址，不过很OUT
        YN.init(this, BuildConfig.hostUrl)
    }

}