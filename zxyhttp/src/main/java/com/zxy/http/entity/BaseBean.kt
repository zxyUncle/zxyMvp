package com.zxy.http.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2019/9/10-11:11
 * Class functions
 * ******************************************
 * * 通用基类
 * ******************************************
 */
@Parcelize
data class BaseBean(
        var code: String? = null,
        var message: String? = null,
        var success: Boolean? = null,
        var token: String? = null,
        var userId: String? = null,
        var captchaUrl: String? = null,//图片
        var count: Int? = 0,
        var batch:String="",//批次号
        var flag: Boolean? = null,//标记
        var perCodes: MutableList<String>? = null,//权限集合
        var data: DataBean? = null//注册极光推送响应值
) : Parcelable {
    @Parcelize
    data class DataBean(
            var code: String? = null,
            var success: String? = null
    ) : Parcelable
}