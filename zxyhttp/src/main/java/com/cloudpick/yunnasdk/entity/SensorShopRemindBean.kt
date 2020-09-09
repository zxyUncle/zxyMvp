package com.cloudpick.yunnasdk.entity

/**
 * Created by zxy on 2020/8/3 19:32
 * ******************************************
 * * 2.22、补货：查询栏位商品补货提醒值
 * ******************************************
 */
data class SensorShopRemindBean(
    var code: String?=null, // 0000
    var replenishConfigDto: ReplenishConfigDto?=null,
    var success: Boolean? =null// true
) {
    data class ReplenishConfigDto(
        var adviceNum: Int?=null, // 8
        var alarmNum: Int?=null, // 2
        var goodsId: String?=null, // 20022009
        var goodsName: String?=null, // 麦源匈牙利培根芝士
        var laneId: String?=null, // 001
        var notifyNum: Int?=null, // 4
        var storeId: String? =null// SHP001
    )
}