package com.cloudpick.yunnasdk.entity

import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 17:23
 * ******************************************
 * * 3.3.7、查询卡券类型
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class CardReelTypeBean(
        var code: String? = null, // 0000
        var `data`: List<Data?>? = null,
        var success: Boolean? = null // true
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
            var cardDesc: String? = null, // 机场店九折卡
            var cardName: String? = null, // 虹桥机场卡
            var cardTemplateId: String? = null, // 190430001
            var cardType: String? = null, // HQ_AIRPORT_CARD
            var channel: String? = null, // yunna
            var fromDate: String? = null, // 2020-08-04 17:00:00
            var handleArg: String? = null, // {"discount":"9"}
            var level: Int? = null, // 1
            var limitday: Int? = null, // -1
            var receiveArg: String? = null, // {}
            var scene: String? = null, // https://cloupick-source.oss-cn-hangzhou.aliyuncs.com/hongqiaoairportcard.jpg
            var storeId: String? = null, // SHM004
            var toDate: String? = null, // 2020-08-04 23:59:59
            var value: String? = null // -1
    ) : Parcelable
}