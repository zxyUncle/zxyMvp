package com.zxy.zxymvp.presenter

import com.kennyc.view.MultiStateView
import com.zxy.httpnet.utils.OkHttpManager
import com.zxy.zxyhttp.entity.PersonTravelNodes
import com.zxy.zxyhttp.entity.UserBean
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
        val login = OkHttpManager.instance.apiService(view).getPersonTravelNodes("特色出游", "1", "20")
        OkHttpManager.instance.CallObserDialog(login,
                object : OkHttpManager.HttpClickLenerlist<PersonTravelNodes> {
                    override fun onFail(obj: PersonTravelNodes) {

                    }

                    override fun onSucc(obj: PersonTravelNodes) { // 成功
                        view.bindContent(obj)
                    }

                })

    }
}