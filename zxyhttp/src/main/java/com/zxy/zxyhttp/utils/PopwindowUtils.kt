package com.zxy.zxyhttp.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow


/**
 *  界面的下方弹出
 * @property mContext Activity?
 * @constructor
 */
class PopwindowUtils(mContext: Context) {
    var mContext: Context? = null
    var popupWindow: PopupWindow? = null

    init {
        this.mContext = mContext
    }

    /**
     * view:位置
     * v：popwindow的布局
     */
    fun showPop(view: View, v: View) {
        popupWindow = PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow!!.isTouchable = true;
        popupWindow!!.isFocusable = false;
        popupWindow!!.isOutsideTouchable = false;
//        popupWindow!!.showAsDropDown(view, 0, 0, Gravity.TOP)
        popupWindow!!.showAtLocation(view, Gravity.TOP, 0, 300)
    }

    fun dismiss() {
        if (popupWindow != null)
            popupWindow!!.dismiss()
    }
}