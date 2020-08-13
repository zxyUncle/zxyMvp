package com.cloudpick.yunnasdk


import com.zxy.zxyhttp.entity.BaseBean
import com.zxy.zxyhttp.entity.OrderCreateRequestBean
import com.zxy.zxyhttp.entity.OrderCreateResponceBean
import com.zxy.zxyhttp.entity.WeightDataBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by ZXY on 2019/9/5 11:52.
 * Class functions
 * *********************************************************
 * * 网路接口
 * * 具体Retrfit注解查看：https://blog.csdn.net/fayangzhou/article/details/80859318
 * *********************************************************
 */
interface HttpMode {
    /**
     * 查询层板商品信息
     */
    @GET("sto/rest/device/api/v1/shelf/lane/goods/weight")
    fun getWeightData(
        @Query("shelfId") shelfId: String
    ): Observable<WeightDataBean>

    /**
     * 发送心跳包
     */
    @GET("basedata/rest/device/api/v1/hb/heartBeat/{searchId}")
    fun sendBindContent(
        @Path("searchId") searchId: String
    ): Observable<BaseBean>


    /**
     * 创建订单
     */
    @POST("sto/rest/device/api/v1/event/goods/pay")
    fun createOrder(
        @Body orderCreateRequestBean: OrderCreateRequestBean
    ): Observable<OrderCreateResponceBean>

    /**
     * 查询订单支付结果
     */
    @GET("sto/rest/device/api/v1/order/{ordrId}")
    fun orderResult(
        @Path("ordrId") ordrId: String
    ): Observable<OrderCreateResponceBean>

}