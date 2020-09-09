package com.cloudpick.yunnasdk.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/7/31 10:14
 * ******************************************
 * *    商品基类
 * ******************************************
 */
@Parcelize
data class GoodsInfoBean(
        var goodsId: String?=null,    //商品id
        var goodsName: String?=null,//商品名称
        var goodsPhoto: String?=null,//商品图片
        var goodsPrice: String?=null,//商品价格
        var goodsNum: Int?=null,//数量
        var goodsTax: String?=null,//税费
        var diffRisk: Int?=null, // 5
        var goodsPhotoUrl: String?=null, // https://img.yunatop.com/1005270.jpg
        var goodsUnit: String?=null,
        var brand: String?=null, // 麦源
        var depth: Int?=null, // 60
        var expireDay: Int?=null, // 1
        var goodsDesc: String?=null, // 麦源匈牙利培根芝士
        var goodsPhdoto: String?=null, // http://img.yunatop.com/20022009.jpg
        var height: Int?=null, // 40
        var maxWeight: Int?=null, // 118
        var minWeight: Int?=null, // 98
        var normalWeight: Int?=null, // 108
        var place: String?=null, // 中国
        var price: Int?=null, // 750
        var rackClass: String?=null, // 01
        var spec: String?=null, // 100g
        var type: String?=null, // ssz
        var unit: String?=null, // 个
        var width: Int?=null // 100
):Parcelable