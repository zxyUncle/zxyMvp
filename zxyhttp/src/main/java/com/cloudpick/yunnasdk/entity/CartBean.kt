package com.cloudpick.yunnasdk.entity

import android.annotation.SuppressLint

/**
 * Created by zxy on 2020/8/4 14:42
 * ******************************************
 * *    3.1.1、获取顾客未结算购物车
 * ******************************************
 */
@SuppressLint("ParcelCreator")
data class CartBean(
        var cartGoodsInfos: MutableList<GoodsInfoBean>? = null, // null
        var cartId: Any? = null, // null
        var code: String? = null, // 0000
        var i18nFields: Any? = null, // null
        var i18nTemplateFields: Any? = null, // null
        var message: Any? = null, // null
        var mobile: String? = null, // 18682026497
        var success: Boolean? = null, // true
        var userId: String? = null // 200716172249312UA000249
)
