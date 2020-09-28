package com.zxy.http


import com.zxy.http.entity.*
import io.reactivex.Observable
import okhttp3.RequestBody
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
    //zxy 1、登录模块
    /**
     * @see 1.1、发送短信验证码
     * @param captchaValue 图形验证码
     * @param mobile    手机号
     * @param region    区号：+86
     * @param system   app默认传：mer
     * @param channel   渠道
     */
    @GET("cloudpick/rest/admin/api/v1/logon/sendsms")
    fun sendsms(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    /**
     * @see 1.2、刷新图片验证码
     * @param mobile    手机号
     * @param system    app默认传：mer
     * @param channel   渠道
     */
    @GET("cloudpick/rest/admin/api/v1/logon/captcha")
    fun captcha(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    /**
     * @see 1.3、用户登录
     * @param mobile    手机号
     * @param smsCode   验证码
     * @param system    app默认传：mer
     * @param region    区号：+86
     * @param channel   渠道
     */
    @POST("cloudpick/rest/admin/api/v1/logon/login")
    fun loginCode(@Body body: RequestBody): Observable<LoginInfoBean>

    /**
     * @see 1.4、一个手机号在多个渠道下有账号 登录
     * @param userId    userId
     * @param system    app默认传：mer
     */
    @POST("cloudpick/rest/admin/api/v1/logon/mlogin")
    fun mlogin(@Body body: RequestBody): Observable<LoginInfoBean>

    /**
     * @see 1.5、用户退出
     */
    @GET("cloudpick/rest/admin/api/v1/logon/logout")
    fun logout(): Observable<BaseBean>

    /**
     * @return 弃用
     * @see 1.6、EX：账号登录
     * @param userName    账户
     * @param password   密码
     * @param channel   渠道
     * @param expiry    过期时间：天
     * @return {"code":0}
     */
    @POST("cloudpick/rest/admin/api/v1/logon/pwd/user/login")
    fun loginAccount(@Body body: RequestBody): Observable<LoginInfoBean>


}