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

    /**
     * @see 1.3、用户登录
     * @param mobile    手机号
     * @param smsCode   验证码
     * @param system    app默认传：mer
     * @param region    区号：+86
     * @param channel   渠道
     * @return {"code":0}
     */
    fun loginCode(map: Map<String, Any>) {
        OkHttpService.INSTANCE.callBack(okHttpApi.getWXArticle(), {

        }, {

        }, {

        })
    }


}