package com.zxy.zxyhttp.entity

import java.io.Serializable

/**
 * Created by zxy on 2019/9/5-14:27
 * Class functions
 * ******************************************
 * *    用户个人景区游记列表--我的游记列表
 * ******************************************
 */
data class PersonTravelNodes(
    var code: Int?, // 200
    var count: Int?, // 0
    var `data`: List<Data?>?,
    var msg: String? // 操作成功
) {
    data class Data(
        var browseCountDesc: String?, // 6人路过
        var constellationName: Any?, // null
        var constellationType: Int?, // 0
        var costDescription: Any?, // null
        var costUnInCloud: Any?, // null
        var coverUrl: String?, // https://img.lvyouquan.cn/uploads/media/images/product/20200602/6673507678133735425_648_367.jpg!thumbnail_9
        var createTime: Long?, // 1591286166000
        var createUserId: Int?, // 0
        var defaultClause: Any?, // null
        var detail: Any?, // null
        var endCity: Any?, // null
        var endTime: Any?, // null
        var id: Int?, // 18001
        var incomeAmount: Any?, // null
        var isConstellation: Any?, // null
        var isOutbound: Boolean?, // false
        var isSeckill: Any?, // null
        var lyqProductId: String?, // 4546737721264e589c70e65a2611d8a5
        var minDummyPrice: Any?, // null
        var minPrice: Double?, // 2280
        var name: String?, // 【纯玩0自费，当地参团】喀纳斯+观鱼台+禾木+五彩滩+乌尔禾魔鬼城玩双飞8日（无购物，无自费，5晚当地5星酒店，品质首选，）
        var payType: Any?, // null
        var placement: Int?, // 0
        var playDay: Int?, // 8
        var playNight: Int?, // 0
        var price: Double?, // 2280
        var productCode: String?, // FT10296907
        var productLevel: String?, // 尊享
        var productType: String?, // 跟团游
        var proportion: Double?, // 1
        var reservationNotes: Any?, // null
        var sourceType: Int?, // 1
        var startCity: Any?, // null
        var startTime: Any?, // null
        var state: Int?, // 1
        var strengths: Any?, // null
        var strengthsTitle: Any?, // null
        var subProductType: Int?, // 0
        var title: String?,
        var tourModule: String?,
        var tourTag: String?, // 测试数据,特色出游123
        var tourType: String?,
        var travelType: String?, // 国内游
        var trip: Any?, // null
        var type: Int?, // 0
        var updateTime: Long?, // 1591707845000
        var updateUserId: Int? // 0
    )
}