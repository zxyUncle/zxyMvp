package com.cloudpick.yunnasdk.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.cloudpick.yunnasdk.R

/**
 * @Author EDZ
 * @Date 2019/8/24-10:13
 * @Email chrisSpringSmell@gmail.com
 */
/**
 * Created by zxy on 2019/8/24-10:13
 * Class functions
 * ******************************************
 * * 自定义万能布局Dialog
 * ******************************************
 */
class DialogMsgUtils(mContext: Context) {
    var mContext: Context? = null
    var dialog: AlertDialog? = null

    init {
        this.mContext = mContext
    }

    /**
     *
     * @param viewLayout View 自定义的布局
     */
    @SuppressLint("ResourceAsColor")
    fun showDilaog(viewLayout: View) {
        val value: Activity = mContext as Activity
        var builder = AlertDialog.Builder(mContext, R.style.MyDilog)
        builder!!.setView(viewLayout)
        dialog = builder!!.create()
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    /**
     *
     * @param viewLayout View
     * @param Gravity Int   -Gravity.TOP
     */
    @SuppressLint("ResourceAsColor")
    fun showDilaog(viewLayout: View,Gravity:Int) {
        val value: Activity = mContext as Activity
        var builder = AlertDialog.Builder(mContext, R.style.MyDilog)
        builder!!.setView(viewLayout)
        dialog = builder!!.create()
        dialog!!.getWindow()!!.setGravity(Gravity)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    /**
     * 设置点击窗口外是否可以消失
     * @param flog Boolean false 不可以消失
     */
    fun setCancel(flog: Boolean) {
        if (dialog != null)
            dialog!!.setCancelable(flog)
    }

    fun destroy() {
        if (dialog != null)
            dialog!!.dismiss()
    }

}