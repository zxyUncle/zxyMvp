package com.cloudpick.yunnasdk.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.cloudpick.yunnasdk.R
import com.cloudpick.yunnasdk.YN


/**
 * Created by zxy on 2019/12/23 15:32
 * ******************************************
 * * 界面的下方弹出
 * ******************************************
 */
class PopwindowUtils {
    var popupWindow: PopupWindow? = null
    val mContext: Context? by lazy {
        YN.mContext
    }

    //zxy 单例模式
    private constructor() {}

    companion object {
        @Volatile
        private var instance: PopwindowUtils? = null

        fun instance(): PopwindowUtils? {
            if (instance == null) {
                synchronized(PopwindowUtils::class.java) {
                    if (instance == null) {
                        instance = PopwindowUtils()
                    }
                }
            }
            return instance
        }
    }

    /**
     * view:位置
     * v：popwindow的布局
     */
    fun showPop(v: View, alpha: Float = 0.8f) {
        if (popupWindow == null) {
            popupWindow =
                    PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow!!.isTouchable = true
            popupWindow!!.isFocusable = true
            popupWindow!!.animationStyle = R.style.YN_popwin_anim_style_bottom;
            popupWindow!!.isOutsideTouchable = true
            popupWindow!!.showAsDropDown(v, 0, 0, Gravity.CENTER)
//            popupWindow!!.showAtLocation(view, Gravity.BOTTOM, 0, 0)
            //设置遮罩层
            backgroundAlpha(alpha)
            popupWindow!!.setOnDismissListener {
                backgroundAlpha(1f)
                popupWindow = null
                //**重点，清理掉浮动的遮罩层，否则使用转场动画的时候会有黑色背景，因为dismiss是隐藏不是销毁
//                mContext?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
        } else {
            dismiss()
        }
    }

    fun dismiss() {
        if (popupWindow == null)
            popupWindow?.dismiss()
    }

    private fun backgroundAlpha(f: Float) {
//        val attributesNew = mContext?.window?.attributes
//        attributesNew?.alpha = f
//        mContext?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//        mContext?.window?.attributes = attributesNew
    }

//    popupWindow?.setTouchInterceptor { v, event ->
//        if (event.y >= 0) {//PopupWindow内部的事件
//            false
//        } else {//PopupWindow外部的事件
//            viewDialog.wheelViewAdult.setItems(voucherTypes, selectPositon)
//        }
//        false
//    }

}