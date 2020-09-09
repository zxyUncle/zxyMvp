package com.cloudpick.yunnasdk.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 17:06
 * ******************************************
 * * 3.3.5、优惠券类型查询
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class CouponListBean(
    var code: String? = null, // 0000
    var `data`: List<Data?>? = null,
    var success: Boolean? = null // true
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        var amount: String? = null, // 1.00
        var channel: String? = null, // yunna
        var couponDesc: String? = null, // 123
        var couponName: String? = null, // 123
        var couponTemplateId: String? = null, // 200804067
        var couponType: String? = null, // ADD_PURCHASE_COUPON
        var couponTypeArg: String? = null, // {"typeA":"1004339","typeB":"1004339","condition":"A|2","result":"B|2|0"}
        var createTime: String? = null, // 2020-08-04 14:39:48
        var fromDate: String? = null, // 2020-08-06 00:00:00
        var limitDay: String? = null, // null
        var limitNum: String? = null, // null
        var scence: String? = null, // 4
        var showAmount: String? = null, // 123
        var stores: String? = null, // SHP001
        var toDate: String? = null // 2020-08-08 23:59:59
    ) : Parcelable
}