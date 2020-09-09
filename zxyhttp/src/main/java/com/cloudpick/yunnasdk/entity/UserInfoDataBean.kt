package com.cloudpick.yunnasdk.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 16:48
 * ******************************************
 * *3.3.1、查询顾客信息
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class UserInfoDataBean(
    var code: String? = null, // 0000
    var `data`: UserInfo? = null
):Parcelable
