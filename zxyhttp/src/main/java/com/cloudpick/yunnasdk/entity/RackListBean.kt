package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/3 15:37
 * ******************************************
 * *    2.1、店铺坐标
 * ******************************************
 */
@Parcelize
data class RackListBean(
        var code: String? = null, // 0000
        var `data`: Data? = null,
        var message: String? = null,
        var i18nFields: List<String?>? = null
) : Parcelable {
    @Parcelize
    data class Data(
            var location: Location? = null,
            var maxX: Int? = null, // 6000
            var maxY: Int? = null, // 6000
            var originX: Int? = null, // -2000
            var originY: Int? = null, // 3000
            var rackList: List<Rack?>? = null
    ) : Parcelable {
        @Parcelize
        data class Location(
                var latitude: String? = null, // 31.176954
                var longitude: String? = null // 121.488518
        ) : Parcelable
    }

    @Parcelize
    data class Rack(
            var depth: Int? = null, // 340
            var height: Int? = null, // 2225
            var rackId: String? = null, // 001
            var typeName: String? = null, // 普通货架
            var width: Int? = null, // 900
            var firstHoleHeight: Int? = null, //0
            var holeGap: Int? = null, // 0
            var points: MutableList<PointF>? = null,
            var x0: Int? = null, // 818
            var x1: Int? = null, // 1640
            var x2: Int? = null, // 1590
            var x3: Int? = null, // 768
            var y0: Int? = null, // 1422
            var y1: Int? = null, // 1558
            var y2: Int? = null, // 1930
            var y3: Int? = null // 1793
    ) : Parcelable {
        @Parcelize
        data class PointF(
                var x: Float? = null,
                var y: Float? = null
        ) : Parcelable
    }
}