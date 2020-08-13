package com.zxy.zxyhttp.entity

/**
 * Created by zxy on 2020/7/22 16:33
 * ******************************************
 * *
 * ******************************************
 */
data class BaseBean(
    val code: String?, // 0000
    val message: String?, // 调用成功
    val success: Boolean? // true
)