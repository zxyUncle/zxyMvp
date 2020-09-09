package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/4 15:30
 * ******************************************
 * * 3.2.3、订单支付流水
 * ******************************************
 */
@Parcelize
data class OrderWater(
    var code: String?=null, // 0000
    var `data`: List<Data?>?=null,
    var success: Boolean?=null // true
) :Parcelable{
    @Parcelize
    data class Data(
        var accAmt: Int?=null, // 0
        var payType: String?=null, // cp_pay
        var transAmt: Int?=null, // 799
        var transId: String?=null, // 200804SHP001312T00014131
        var transStatus: String?=null // 05
    ):Parcelable
}