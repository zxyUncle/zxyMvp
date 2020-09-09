package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/3 14:32
 * ******************************************
 * *    6.4、二维码
 * ******************************************
 */
@Parcelize
data class QrCodeBean(
        var code: String?=null, // 0000
        var `data`: Data?=null
) : Parcelable {
    @Parcelize
    data class Data(
            var code: String?=null, // 0000
            var entryUrl: String?=null, // yunna/M-83460834374390b5e98cc0a728bb7a
            var success: Boolean?=null // true
    ) : Parcelable
}