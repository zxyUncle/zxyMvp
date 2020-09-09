package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/3 11:29
 * ******************************************
 * * 3.3.6、发送优惠券
 * ******************************************
 */
@Parcelize
data class CouponModelsBean (
        var count: String?=null,
        var couponTemplateId: String?=null
):Parcelable