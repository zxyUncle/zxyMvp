package com.cloudpick.yunnasdk.entity

import java.util.ArrayList

/**
 * Created by zxy on 2020/8/3 16:48
 * ******************************************
 * *    2.3、获取货架栏详情
 * ******************************************
 */
data class ShelfBeanListBean(
        var code: String? = null, // 0000
        var message: String? = null,
        var `data`: ArrayList<ShelfBean?>? = null
)