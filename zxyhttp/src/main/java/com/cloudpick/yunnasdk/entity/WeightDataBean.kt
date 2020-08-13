package com.zxy.zxyhttp.entity

import java.io.Serializable

/**
 * Created by zxy on 2020/7/22 11:20
 * ******************************************
 * * 层板商品信息
 * ******************************************
 */
data class WeightDataBean(
    val code: String?, // 0000
    var laneWtList: MutableList<LaneWt?>?,
    val success: Boolean? // true
) :Serializable{
    data class LaneWt(
        var isExist: Boolean = true,
        var code: Byte = 0x00,
        val goodsId: String? = null,// shop
        val laneId: String? = null, // SFSHP0010010300
        val maxWeight: Int? = null, // 661
        val minWeight: Int? = null // 631
    ): Serializable
}