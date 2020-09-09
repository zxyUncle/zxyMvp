package com.cloudpick.yunnasdk.entity

/**
 * Created by zxy on 2020/8/3 17:31
 * ******************************************
 * *    2.9、查询商品活动
 * ******************************************
 */
data class ShopActivityBean(
    var code: String?=null, // 0000
    var goodsMap: GoodsMap?=null,
    var success: Boolean? =null// true
) {
    data class GoodsMap(
        var `898999000022`: List<X898999000022?>?
    ) {
        data class X898999000022(
            var activityId: String?=null, // 2007200080
            var activityName: String?=null, // 买一送一test
            var activityTypeId: String?=null, // buysend
            var timeSlot: String?=null
        )
    }
}