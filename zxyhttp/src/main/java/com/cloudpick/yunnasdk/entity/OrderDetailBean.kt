package com.cloudpick.yunnasdk.entity

/**
 * Created by zxy on 2020/8/4 15:28
 * ******************************************
 * *    3.2.2、订单详情
 * ******************************************
 */
data class OrderDetailBean(
    var code: String?=null, // 0000
    var `data`: Data?=null,
    var i18nFields: Any?=null, // null
    var message: Any?=null // null
) {
    data class Data(
        var count: Int?=null, // 0
        var couponAmt: Any?=null, // null
        var desc: Any?=null, // null
        var ext: String?=null, // {"goodsPhoto":"20022009"=null,"count":1}
        var gmtCreate: String?=null, // 2020-08-04 15:27:11
        var goodsIdList: List<Any?>?=null,
        var goodsList: List<Goods?>?=null,
        var noTaxGoodsAmt: Int?=null, // 799
        var orderAmt: String?=null, // 7.99
        var orderDesc: String?=null, // 便利店线下消费
        var orderId: String?=null, // 200804SHP001312D00001729
        var orderTaxAmt: Int?=null, // 0
        var orderType: String?=null, // 00
        var payTime: String?=null, // 2020-08-04 15:27:11
        var payTypeList: List<Any?>?=null,
        var status: String?=null, // init
        var stayingTime: Int?=null, // 0
        var storeId: String?=null, // SHP001
        var storeName: Any?=null, // null
        var tipTimes: Any?=null, // null
        var unPaidAmt: String?=null, // 7.99
        var userId: String?=null // 200716172249312UA000249}
    ) {
        data class Goods(
            var discountHis: Any?=null, // null
            var displayRealAmt: Any?=null, // null
            var ext: Any?=null, // null
            var goodsId: String?=null, // 20022009
            var goodsName: String?=null, // 麦源匈牙利培根芝士
            var goodsNum: Int?=null, // 1
            var goodsPhotoUrl: String?=null, // https://img.yunatop.com/20022009.jpg
            var goodsPrice: String?=null, // 7.99
            var goodsRealPrice: String?=null, // 7.99
            var goodsTax: Int?=null, // 0
            var goodsUnit: String?=null, // 个
            var taxRate: Int?=null // 0
        )
    }
}