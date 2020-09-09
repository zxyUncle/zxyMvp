package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/7/30 11:09
 * ******************************************
 * * 用户信息
 * ******************************************
 */
@Parcelize
data class UserInfoBean(
        var code: String?=null, // 0000
        var coupon: Boolean?=null, // false
        var couponAmt: Int?=null, // 0
        var register: Boolean?=null, // true
        var success: Boolean?=null, // true
        var userInfo: UserInfoBean?=null
) : Parcelable

@Parcelize
data class UserInfo(
        var email: String?=null, // xyzhang@cloudpick.me
        var identityGrade: String?=null, // N
        var payBind: String?=null, // N
        var registerChannel: String?=null, // yunna
        var registerTime: String?=null, // 2020-07-30 11:06:59
        var status: String?=null, // 01
        var statusDesc: String?=null, // 正常
        var birthday: String? = null, // 1990-01-01
        var country: String? = null, // +86
        var countryName: String? = null, // +86
        var gender: String? = null, // 00
        var realName: String? = null, // 张行于
        var type: String?=null, // 02
        var typeDesc: String?=null, // 普通用户
        var mobile: String?=null, // 普通用户
        var userId: String?=null // 200730110659565UA000294
) : Parcelable
