package com.zxy.zxyhttp


import com.zxy.zxyhttp.entity.PersonTravelNodes
import com.zxy.zxyhttp.entity.UserBean
import retrofit2.http.*
import rx.Observable

/**
 * Created by ZXY on 2019/9/5 11:52.
 * Class functions
 * *********************************************************
 * * 网路接口
 * * 具体Retrfit注解查看：https://blog.csdn.net/fayangzhou/article/details/80859318
 * *********************************************************
 */
interface HttpMode {
    //发送验证码
    @POST("user/sendPhoneCode")
    @FormUrlEncoded
    fun login(@FieldMap map: HashMap<String, String>): Observable<UserBean>

    @GET("tourcloud/tourcloud/oldList")
    fun getPersonTravelNodes(
        @Query("tourTag") tourTag: String,
        @Query("pageNum") pageNum: String,
        @Query("pageSize") pageSize: String
    ): Observable<PersonTravelNodes>

}