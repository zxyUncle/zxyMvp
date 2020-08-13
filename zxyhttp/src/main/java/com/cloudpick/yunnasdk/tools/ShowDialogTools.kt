package com.cloudpick.yunnasdk.tools

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.cloudpick.yunnasdk.R
import com.cloudpick.yunnasdk.utils.AlertDialogUtils

/**
 * Created by zxy on 2020/8/5 11:00
 * ******************************************
 * * 弹出框Dialog
 * ******************************************
 */
class ShowDialogTools {
    companion object {
        private lateinit var alertDialogUtils: AlertDialogUtils

        fun showSMS(captchaUrl: String, mContext: Context, map: Map<String, Any>) {
            alertDialogUtils = AlertDialogUtils.build(mContext)
                    .setCancelable(true)                //可选 设置是否可以取消，默认true
                    .setTransparency(0.3f)                //可选 设置窗口透明度，默认0.5
                    .setView(R.layout.yn_dialog_sms_veri_code)
                    .setOnClick(R.id.btn_ok)
                    .create(object : AlertDialogUtils.Builder.AlertDialogUtilsListener {
                        override fun onClickDialog(view: View) {
                            when (view.id) {
                                R.id.btn_ok -> {
                                    val layoutView = alertDialogUtils.layoutView
                                    val etCaptcha = layoutView.findViewById<EditText>(R.id.et_captcha)
                                    (map as HashMap)["captchaValue"] = etCaptcha.text.toString()
//                                    YNClient.sendsms(map, mContext, onResponceListener)
                                }
                            }
                            alertDialogUtils.cancel()   //取消Dialog
                        }
                    })
            val imgCaptcha = alertDialogUtils.layoutView.findViewById<ImageView>(R.id.imgCaptcha)
//            imgCaptcha.loadUrl(captchaUrl)
        }
    }
}