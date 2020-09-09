package com.cloudpick.yunnasdk.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 17:47
 * ******************************************
 * * 5.1.2、查询我能看到的仓库列表
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class StorageListBean(
    var code: String? = null, // 0000
    var `data`: List<Storage?>? = null,
    var success: Boolean? = null // true
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Storage(
        var channel: String? = null, // yunna
        var id: Int? = null, // 1
        var location: Location? = null,
        var storageAddr: String? = null, // 东川路555号
        var storageId: String? = null, // YNSHP001
        var storageName: String? = null, // 啊法店
        var useStorage: String? = null, // 0
        var valid: Boolean? = null // true
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    class Location(

    ) : Parcelable
}