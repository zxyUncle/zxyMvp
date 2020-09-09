package com.cloudpick.yunnasdk.entity

/**
 * Created by zxy on 2020/8/3 19:08
 * ******************************************
 * *    2.19、补货：店铺补货货架种类
 * ******************************************
 */
data class ReplenishmentGroupRequest(
    var code: String?=null, // 0000
    var rackGroupObject: RackGroupObject?=null,
    var success: Boolean?=null // true
) {
    data class RackGroupObject(
        var TEST_0001: String?=null, // 冷冻D区
        var TEST_0002: String?=null, // 生活用品A区
        var TEST_0003: String?=null, // 干活B区
        var TEST_0004: String?=null // 冷冻C区
    )
}