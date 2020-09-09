package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/7/31 15:22
 * ******************************************
 * * 5.6.3、新增采购/退货申请单接口
 * ******************************************
 */
@Parcelize
data class PurApplyDetailBean (
        var goodsId: String?="",    //商品id
        var goodsName: String?="",//商品名称
        var sku: String?="",//sku
        var num: String?="",//数量
        var basicApplyNum: String?=""//基本订购数

):Parcelable