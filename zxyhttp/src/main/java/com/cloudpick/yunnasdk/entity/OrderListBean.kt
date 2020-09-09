package com.cloudpick.yunnasdk.entity

/**
 * Created by zxy on 2020/8/4 15:23
 * ******************************************
 * *    3.2.1、订单列表
 * ******************************************
 */
data class OrderListBean(
        var code: String?=null, // 0000
        var `data`: List<Data?>?=null,
        var i18nFields: Any?=null, // null
        var message: Any?=null // null
) {
    data class Data(
            var accountAmt: String?=null, // 10.80
            var couponAmt: String?=null, // 2.70
            var ext: String?=null, // {"goodsPhoto":"6935145301047"=null,"count":1}
            var gmtCreate: String?=null, // 2020-08-04 11:02:07
            var orderAmt: String?=null, // 13.50
            var orderDesc: String?=null, // 到店自提订单
            var orderId: String?=null, // 200804SHP001682D00001717
            var orderType: String?=null, // 05
            var originOrderId: Any?=null, // null
            var payDesc: Any?=null, // null
            var payTime: String?=null, // 2020-08-04 11:02:07
            var payTypeViewInfo: Any?=null, // null
            var status: String?=null, // success
            var transId: Any?=null, // null
            var unPaidAmt: String?=null, // 0.00
            var userId: String?=null // 191210****0206
    )
}