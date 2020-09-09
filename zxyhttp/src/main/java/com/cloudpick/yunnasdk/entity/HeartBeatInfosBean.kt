package com.cloudpick.yunnasdk.entity

/**
 * Created by zxy on 2020/8/3 16:57
 * ******************************************
 * *    2.5、查询闸机心跳
 * ******************************************
 */
data class HeartBeatInfosBean(
        var code: String?=null, // 0000
        var heartBeatInfos: List<HeartBeatInfo?>?=null,
        var success: Boolean?=null // true
) {
    data class HeartBeatInfo(
            var intervarTime: Int?=null, // 90
            var lastBeatTime: String?=null, // 2020-06-09 19:50:30
            var sourceId: String?=null, // 00E04C6803E7
            var status: String?=null, // 00
            var storeId: String?=null, // SHP001
            var type: String?=null, // 01
            var varid: String?=null // Y
    )
}