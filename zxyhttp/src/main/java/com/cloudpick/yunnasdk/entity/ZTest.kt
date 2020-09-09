package com.cloudpick.yunnasdk.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 16:49
 * ******************************************
 * *
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ZTest(
    var code: String? = null, // 0000
    var success: Boolean? = null, // true
    var userInfo: UserInfo? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class UserInfo(
        var birthday: String? = null, // 1990-01-01
        var country: String? = null, // +86
        var countryName: String? = null, // +86
        var gender: String? = null, // 00
        var identityGrade: String? = null, // N
        var mobile: String? = null, // 18682026497
        var payBind: String? = null, // N
        var realName: String? = null, // 张行于
        var registerChannel: String? = null, // yunna
        var registerTime: String? = null, // 2020-07-16 17:22:49
        var status: String? = null, // 01
        var statusDesc: String? = null, // 正常
        var type: String? = null, // 02
        var typeDesc: String? = null, // 普通用户
        var userId: String? = null // 200716172249312UA000249
    ) : Parcelable
}