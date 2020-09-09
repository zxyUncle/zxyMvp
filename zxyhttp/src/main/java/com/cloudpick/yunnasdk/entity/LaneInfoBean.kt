package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/3 18:10
 * ******************************************
 * * 2.14、查询商品所在栏位
 * ******************************************
 */
@Parcelize
data class LaneInfoBean(
        var code: String?=null, // 0000
        var laneInfos: List<LaneInfo?>?=null,
        var success: Boolean?=null // true
) : Parcelable

@Parcelize
data class LaneInfo(
        var laneId: String?=null, // SFSHP0010010101
        var rackId: String?=null, // 001
        var shelfId: String?=null // SFSHP00100101
) : Parcelable