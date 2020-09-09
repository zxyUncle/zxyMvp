package com.cloudpick.yunnasdk

import android.app.Activity
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.widget.Toast
import com.google.gson.Gson
import com.cloudpick.httpnet.utils.NetWorkUtils
import com.cloudpick.yunnasdk.tools.LoadTools
import com.cloudpick.yunnasdk.tools.LogcatUitls
import com.cloudpick.yunnasdk.utils.*
import com.trello.rxlifecycle2.components.support.RxFragmentActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.*
import okio.Buffer
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.nio.charset.Charset


/**
 * Created by ZXY on 2019/9/10 12:29.
 * Class functions
 * *********************************************************
 * * 网路请求封装
 * *********************************************************
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
open class OkHttpManager {
    var dialog: LoadingDialog? = null
    var token: String = ""

    private constructor() {

    }

    interface HttpClickLenerlist<T> {
        fun onSucc(obj: T) //成功
        fun onFail(obj: T)//失败
        fun onNoNetwork()//失败

    }

    /**
     * 单例模式
     */
    companion object {
        val instance: OkHttpManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpManager()
        }
    }

    private class HttpLoggerInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            if (request.url().toString().isNotEmpty())
                if ("POST" == request.method()) {
                    var requestBody = request.body()
                    var body: String = ""
                    val buffer = Buffer()
                    requestBody?.writeTo(buffer)
                    var charset: Charset? = Charset.forName("UTF-8")
                    val contentType = requestBody?.contentType()
                    contentType?.let {
                        charset = contentType.charset(Charset.forName("UTF-8"))
                    }
                    body = buffer.readString(charset!!)
                    LogcatUitls.printPost(NetConfigUtils.YN_TAG, request.url().toString(), body)
//                Log.d(TAG,"发送请求: method：" + request.method()+ "\nurl：" + request.url()+ "\n请求头：" + request.headers()+ "\n请求参数: " + body)
                } else {
                    LogcatUitls.printStirng(NetConfigUtils.YN_TAG, request.url().toString())
                }
            return chain.proceed(request)
        }

    }

    private lateinit var mContext: Activity
    private fun <A> getService(baseUrl: String, service: Class<A>): A {
        var httpLoggerInterceptor = HttpLoggerInterceptor()

//        val fileDir = File(Environment.getExternalStorageDirectory(), "cache")
        val fileSize = (10 * 1024 * 1024).toLong()
        val clien = OkHttpClient.Builder()
                //自定义拦截器用于日志输出
                .addNetworkInterceptor { chain ->
                    val request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("token", token)
                            .build()
                    chain.proceed(request)
                }
                .addInterceptor(httpLoggerInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
//                .addInterceptor(initLogInterceptor())
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//            .cache(Cache(fileDir, fileSize))
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build()

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                //格式转换
                .addConverterFactory(GsonConverterFactory.create())
                //正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或其他类型
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clien)
                .build()

        return retrofit.create(service)
    }

    //可用于多种不同种类的请求
    fun apiService(mContext: Context?): HttpMode {
        this.mContext = (mContext as Activity)!!
//        networkChangeReceiver()//网路广播监听,弹出Dilaog去设置网路
        return getService(NetConfigUtils.YN_HOSTURL, HttpMode::class.java)
    }

    /**
     * 网路广播监听,弹出Dilaog去设置网路
     */
    private fun networkChangeReceiver() {
        val receiver = NetworkChangeReceiver()
        val filter = IntentFilter()
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        mContext.registerReceiver(receiver, filter)
    }


    /**
     * 显示Dialog 加载中动画的网路
     * @param dialogCancelable Boolean 是否可以取消Dialog false 不能取消
     * @param observable Observable<T>
     * @param httpClickLenerlist HttpClickLenerlist<T>
     */
    fun <T : Any> CallObserDialog(observable: Observable<T>, httpClickLenerlist: HttpClickLenerlist<T>, isShowLoad: Boolean = true) {
        if (!NetWorkUtils.isNetAvailable(mContext)) {
            Toast.makeText(mContext, mContext.resources.getString(R.string.include_network_no), Toast.LENGTH_SHORT).show()
            LoadTools.instance().hideMultistage()
            httpClickLenerlist.onNoNetwork()
        } else if (!YN.isAllow) {
            LogcatUitls.printStirng(NetConfigUtils.YN_TAG,mContext.resources.getString(R.string.yn_init_error))
              LoadTools.instance().hideMultistage()
            httpClickLenerlist.onNoNetwork()
        } else {
            if (mContext !is RxFragmentActivity) {
                LogcatUitls.printStirng(NetConfigUtils.YN_TAG,mContext.resources.getString(R.string.yn_extends))
            } else {
                if (isShowLoad) LoadTools.instance().show(mContext)
                observable.subscribeOn(Schedulers.io())
                        .compose((mContext as RxFragmentActivity).bindToLifecycle<T>())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            LogcatUitls.printJson(NetConfigUtils.YN_TAG, Gson().toJson(it))
                            val obj = it!!::class.java
                            val fieldCode = obj.getDeclaredField(NetConfigUtils.YN_STATUS)
                            fieldCode.isAccessible = true
                            val code = fieldCode.get(it)
                            when (code) {
                                NetConfigUtils.YN_SUCC ->//成功
                                    httpClickLenerlist.onSucc(it)
                                NetConfigUtils.YN_Fail ->//token失效
                                    mContext.startActivity(NetConfigUtils.YN_INTENT_LOGIN)
                                else -> {// 失败
                                    httpClickLenerlist.onFail(it)
                                }
                            }
                        }, {
                            LogcatUitls.printStirng(NetConfigUtils.YN_TAG, it.toString())
                        }, {
                            if (isShowLoad) LoadTools.instance().hide()
                        })
            }
        }
    }

}