package com.zxy.zxyhttp.entity

/**
 * Created by zxy on 2020/7/24 13:33
 * ******************************************
 * * 创建订单请求基类
 * ******************************************
 */
data class OrderCreateRequestBean(
    val goodsId: String?, // 089686816068
    val laneId: String?, // SFSHP0010010004
    val eventId: String?, // SFSHP00100100041595563200
    val num: String?, // 1
    val startTimestamp: String?, // 1595563200
    val endTimestamp: String?// 1595563200
)