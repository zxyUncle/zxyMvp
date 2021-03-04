package com.zxy.zxymvp.presenter

import com.zxy.http.OkHttpService
import com.zxy.zxymvp.activity.MainActivity
import com.zxy.zxymvp.mvp.base.BasePresenter

/**
 * Created by zxy on 2020/6/9 0009 22:14
 * ******************************************
 * * M层
 * ******************************************
 */
class MainPresenter : BasePresenter<MainActivity>() {

    fun loginCode() {
        OkHttpService.INSTANCE.callBack(okHttpApi.getWXArticle(), {
            //todo 成功过-可省略
        }, {
            //todo 失败-可省略
        }, {
            //todo 网路错误-可省略
        })
    }


}