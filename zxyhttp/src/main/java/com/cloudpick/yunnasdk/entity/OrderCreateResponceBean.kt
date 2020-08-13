package com.zxy.zxyhttp.entity

/**
 * Created by zxy on 2020/7/24 13:33
 * ******************************************
 * * 创建订单请求基类
 * ******************************************
 */
data class OrderCreateResponceBean(
    val code: String?, // 0000
    val message:String?,
    val success: Boolean?,
    val `data`: Data?
) {
    data class Data(
        val orderId: String?, // success
        val orderStatus: String?
    )
}