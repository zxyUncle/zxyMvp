package com.zxy.zxymvp

import android.app.Application
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
    }

}