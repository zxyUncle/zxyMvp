package com.zxy.zxyhttp.entity

/**
 * des:统一的返回体
 * author: DengXiaoLei
 * date: 2018/7/11 18:53
 * e-mail: dengxiaolei@gto365.com
 */

data class BaseResponse<T>(
    var code: String?,
    var success: Boolean?,
    var data: T?
)
