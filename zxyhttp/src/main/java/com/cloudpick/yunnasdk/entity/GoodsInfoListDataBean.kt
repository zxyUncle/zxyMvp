package com.cloudpick.yunnasdk.entity

/**
 * Created by zxy on 2020/8/3 17:17
 * ******************************************
 * *2.8、模糊查询商品
 * ******************************************
 */
data class GoodsInfoListDataBean(
    var code: String?=null, // 0000
    var `data`: List<Data?>?=null,
    var success: Boolean?=null // true
) {
    data class Data(
        var brand: String?=null, // 味全
        var diffRisk: Int?=null, // 10
        var expireDay: Int?=null, // 12
        var goodsDesc: String?=null, // 8801048921102
        var goodsId: String?=null, // 8801048921102
        var goodsName: String?=null, // 真露烧酒ELMER'S GLUE ALL MULTI PURPOSE GLUE 118ML
        var goodsNo: String?=null, // 555522
        var goodsPhoto: String?=null, // https://img.yunatop.com/8801048921102.jpg
        var maxWeight: Int?=null, // 660
        var minWeight: Int?=null, // 640
        var normalWeight: Int?=null, // 650
        var place: String?=null, // 见包装
        var price: String?=null, // 5.00
        var rackClass: String?=null, // 01
        var saleStatus: String?=null, // OFF
        var spec: String?=null, // 11
        var subType: String?=null, // 6001
        var taxRate: String?=null, // 0.00
        var type: String?=null,
        var unit: String?=null // 盒
    )
}