package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/5 15:01
 * ******************************************
 * *    3.1.4、商品面板分类
 * ******************************************
 */
@Parcelize
data class PanelGroupRequest(
        var code: String? = null,
        var String: String? = null,
        var groupModelList: MutableList<PanelLeftBean>? = null
        ) : Parcelable {
    @Parcelize
    data class PanelLeftBean(
            var groupId: String? = null,
            var groupName: String? = null
    ) : Parcelable
}