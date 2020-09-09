package com.cloudpick.yunnasdk.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 17:45
 * ******************************************
 * * 5.1.1、供应商列表
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ProviderListBean(
    var code: String? = null, // 0000
    var count: Int? = null, // 21
    var `data`: List<Data?>? = null,
    var success: Boolean? = null // true
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        var areaCode: String? = null,
        var bankAccount: String? = null, // 156
        var bankName: String? = null, // 的的的
        var channel: String? = null, // yunna
        var cityCode: String? = null,
        var contactMail: String? = null, // 成长成才
        var contactPeople: String? = null, // 测试
        var contactPhone: String? = null, // 1111111111111
        var countTaxStyle: String? = null, // 01
        var countUnit: String? = null, // CNY
        var deliveryType: String? = null, // 02
        var goodsTypeNum: Int? = null, // 0
        var invoiceAddress: String? = null, // 开票地址
        var invoiceHead: String? = null, // 22222222222
        var invoiceType: String? = null, // 02
        var minNum: Int? = null, // 10
        var minPrice: String? = null, // 100.00
        var provinceCode: String? = null,
        var runType: String? = null, // 02
        var signTaxRate: String? = null, // 5.00
        var supplierAddr: String? = null, // 1111111111
        var supplierCode: String? = null, // 123
        var supplierId: String? = null, // 113210f2749241e3b5225b3e99e275c0
        var supplierName: String? = null, // 77
        var valid: String? = null // Y
    ) : Parcelable
}