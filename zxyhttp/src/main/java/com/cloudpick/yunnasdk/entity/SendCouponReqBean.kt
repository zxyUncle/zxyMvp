package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/7/30 17:36
 * ******************************************
 * *    3.3.6、发送优惠券
 * ******************************************
 */
@Parcelize
data class SendCouponReqBean(
        var userId: String?=null,
        var sendCouponModels: List<SendCouponModels>
) :Parcelable{
    @Parcelize
    data class SendCouponModels(
            var count: String?=null,
            var couponTemplateId: String
    ):Parcelable
}