package com.cloudpick.yunnasdk.entity
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/8/4 17:39
 * ******************************************
 * * 4.1、订单报表
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class StatementLIstBean(
    var code: String? = null, // 0000
    var mktBizReportModels: List<MktBizReportModel?>? = null,
    var success: Boolean? = null // true
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class MktBizReportModel(
        var bizDate: String? = null, // 20200213
        var bizTime: String? = null, // 02:10:00
        var channelId: String? = null, // SHP001
        var result: Result? = null,
        var type: String? = null // dailyorder
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Result(
            var couponAmt: String? = null, // 99.36
            var delayPayAmt: String? = null, // 0.00
            var orderAmt: String? = null, // 319.92
            var orderNewCustomerNum: Int? = null, // 0
            var orderOldCustomerNum: Int? = null, // 5
            var orderTakeOutNum: Int? = null, // 0
            var refundAmt: String? = null, // 0.00
            var suppliers: List<Supplier?>? = null,
            var totalNum: Int? = null, // 5
            var totalRepeatCustomersNum: Int? = null, // 2
            var unPaidAmt: String? = null // 0.00
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Supplier(
                var orderAmt: String? = null, // 208.56
                var refundAmt: String? = null, // 0.00
                var supplier: String? = null // SPGMS
            ) : Parcelable
        }
    }
}