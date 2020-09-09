package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/3 17:07
 * ******************************************
 * *    2.7、查询商品
 * ******************************************
 */
@Parcelize
data class GoodsInfoListBean(
        var code: String?=null, // 0000
        var goodsInfoList: List<GoodsInfoBean?>?=null,
        var success: Boolean?=null // true
) : Parcelable