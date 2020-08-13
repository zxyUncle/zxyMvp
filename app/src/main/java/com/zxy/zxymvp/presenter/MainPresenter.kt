package com.zxy.zxymvp.presenter

import com.cloudpick.yunnasdk.OkHttpManager
import com.zxy.zxyhttp.entity.BaseBean
import com.zxy.zxymvp.activity.MainActivity
import com.zxy.zxymvp.mvp.base.BasePresenter

/**
 * Created by zxy on 2020/6/9 0009 22:14
 * ******************************************
 * * M层
 * ******************************************
 */
class MainPresenter : BasePresenter<MainActivity>() {
    fun login() {
        val login = OkHttpManager.instance.apiService(view).sendBindContent("123")
        OkHttpManager.instance.CallObserDialog(login,
            object : OkHttpManager.HttpClickLenerlist<BaseBean> {
                override fun onNoNetwork() {

                }

                override fun onFail(obj: BaseBean) {

                }

                override fun onSucc(obj: BaseBean) { // 成功
                    view.bindContent(obj)
                }

            })

    }
}