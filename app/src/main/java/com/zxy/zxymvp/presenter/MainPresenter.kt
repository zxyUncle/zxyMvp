package com.zxy.zxymvp.presenter

import com.zxy.http.OkHttpApi
import com.zxy.http.OkHttpService
import com.zxy.http.entity.LoginInfoBean
import com.zxy.http.tools.JsonUtil
import com.zxy.zxymvp.activity.MainActivity
import com.zxy.zxymvp.mvp.base.BasePresenter

/**
 * Created by zxy on 2020/6/9 0009 22:14
 * ******************************************
 * * M层
 * ******************************************
 */
class MainPresenter : BasePresenter<MainActivity> {
    constructor() {
        okHttpApi = OkHttpService.INSTANCE.apiService(view)
    }

    fun loginCode() {
        OkHttpService.INSTANCE.callBack(okHttpApi.getWXArticle(), {

        }, {

        }, {

        })
    }


}