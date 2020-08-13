package com.cloudpick.yunnasdk.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.provider.Settings
import com.cloudpick.yunnasdk.R
import kotlinx.android.synthetic.main.yn_dialog_network.view.*


/**
 * Created by zxy on 2019/9/10-16:32
 * Class functions
 * ******************************************
 * * 监听网路变换的广播
 * ******************************************
 */
class NetworkChangeReceiver : BroadcastReceiver() {
    var popwindowUtils:PopwindowTopUtils?=null
    var mContext: Context? = null
    override fun onReceive(mContext: Context?, intent: Intent?) {
        this.mContext = mContext
        //如果是在开启wifi连接和有网络状态下
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.getAction()) {
            val connectivityManager = mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val networkInfo = connectivityManager!!.activeNetworkInfo
            if (networkInfo != null && networkInfo.isAvailable) {//有网
                if (popwindowUtils!=null) popwindowUtils!!.dismiss()
            } else {
                showPopwindow()
            }
        }
    }

    /**
     * popwindow弹出框
     */
    fun showPopwindow(){
        //弹出popwindow对话框
        var viewLayout = LayoutInflater.from(mContext).inflate(R.layout.yn_dialog_network, null)
        popwindowUtils = PopwindowTopUtils(mContext!!)
        popwindowUtils!!.showPop(viewLayout, viewLayout)
        viewLayout.dialog_network_setting.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                //无线网和网络设置界面
                val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
                mContext!!.startActivity(intent)
            }
        })

    }


}