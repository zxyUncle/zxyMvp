package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/3 14:27
 * ******************************************
 * * 5.6.1、查询面板商品分组
 * ******************************************
 */
@Parcelize
data class PanelGoodGroupBean(
    var code: String?=null, // 0000
    var stores: List<Store?>?=null,
    var success: Boolean?=null // true
):Parcelable {
    @Parcelize
    data class Store(
        var address: String?=null, // 上海市闵行区东川路555号4号楼102d
        var area: String?=null, // 40
        var channel: String?=null, // yunna
        var cityCode: String?=null, // CN021
        var desc: String?=null, // 紫竹1号店
        var drawLocation: DrawLocation?=null,
        var gmtCreate: String?=null, // 2017-08-29 13:24:22
        var id: String?=null, // SHP001
        var maxLength: Int?=null, // 6000
        var maxWidth: Int?=null, // 6000
        var name: String?=null, // 阿法店
        var storeIp: String?=null, // 10.10.10.155
        var storeLocation: StoreLocation?=null,
        var storeStatus: StoreStatus?=null
    ):Parcelable {
        @Parcelize
        data class DrawLocation(
            var x: Double?=null, // -2000.0
            var y: Double? =null// 3000.0
        ):Parcelable
        @Parcelize
        data class StoreLocation(
            var latitude: String?=null, // 31.176954
            var longitude: String?=null // 121.488518
        ):Parcelable
        @Parcelize
        data class StoreStatus(
            var sleepStatus: Boolean? =null// false
        ):Parcelable
    }
}