package com.cloudpick.yunnasdk.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 19:52
 * ******************************************
 * * 5.3.11、查询指定批次的盘点快照
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class InventorySnapshotBean(
    var code: String? = null, // 0000
    var i18nFields: List<String?>? = null,
    var result: Int? = null, // 0
    var success: Boolean? = null // true
) : Parcelable