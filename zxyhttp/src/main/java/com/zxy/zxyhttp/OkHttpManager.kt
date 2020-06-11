package com.zxy.httpnet.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.zxy.zxyhttp.utils.LoadingDialog
import com.zxy.zxyhttp.utils.SSLSocketClient
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.util.concurrent.TimeUnit
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.content.IntentFilter
import com.kennyc.view.MultiStateView
import com.zxy.zxyhttp.*
import com.zxy.zxyhttp.arouter.startAroutLeft
import com.zxy.zxyhttp.tools.content
import com.zxy.zxyhttp.tools.error
import com.zxy.zxyhttp.tools.load
import com.zxy.zxyhttp.utils.NetworkChangeReceiver
import com.zxy.zxyhttp.utils.log.okHttpLog.HttpLoggingInterceptorM
import com.zxy.zxyhttp.utils.log.okHttpLog.LogInterceptor


/**
 * Created by ZXY on 2019/9/10 12:29.
 * Class functions
 * *********************************************************
 * * 网路请求封装
 * *********************************************************
 */
open class OkHttpManager private constructor() {
    var dialog: LoadingDialog? = null

    interface HttpClickLenerlist<T> {
        fun onSucc(obj: T) //成功
        fun onFail(obj: T)//失败

    }

    /**
     * 单例模式
     */
    companion object {
        val instance: OkHttpManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpManager()
        }
    }

    private lateinit var mContext: Context
    private fun <A> getService(baseUrl: String, service: Class<A>): A {
        val interceptor = HttpLoggingInterceptorM(LogInterceptor("http"))
        interceptor.level = HttpLoggingInterceptorM.Level.BODY

//        val fileDir = File(Environment.getExternalStorageDirectory(), "cache")
        val fileSize = (10 * 1024 * 1024).toLong()
        val clien = OkHttpClient.Builder()
                //自定义拦截器用于日志输出
                .addNetworkInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Authorization", "Bearer BB67vr43aIUvcUIyKcER6iS9bcZd8EU9uqt46dLNWqyh4/A=")
                                .build()
                        Log.i("msg", chain.request().tag().toString())
                        return chain.proceed(request)
                    }
                })
                .addInterceptor(interceptor)
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(clien)
                .build()

        return retrofit.create(service)
    }

    //可用于多种不同种类的请求
    fun apiService(mContext: Context): HttpMode {
        this.mContext = mContext
        dialog = LoadingDialog(mContext)

        networkChangeReceiver()//网路广播监听,弹出Dilaog去设置网路

        return getService(NetConfigUtils.instance.hostUrl, HttpMode::class.java)
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
    fun <T : Any> CallObserDialog(observable: Observable<T>, httpClickLenerlist: HttpClickLenerlist<T>, dialogCancelable: Boolean = true) {
        if (!NetWorkUtils.isNetAvailable(mContext)) {
            Toast.makeText(mContext, mContext.resources.getString(R.string.include_network_no), Toast.LENGTH_SHORT).show()
        } else {

//            //延迟1000毫秒显示加载动画，如果1000以内访问成功就不会显示加载对话框
//            Handler().postDelayed({
//                show(dialogCancelable)
//            }, 1000)
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<T>() {
                        override fun onNext(t: T?) {
                            dismiss()
                            if (dialog != null) dialog!!.dismiss()
                            Log.e(NetConfigUtils.instance.TAG, t.toString())
                            val obj = t!!::class.java
                            val fieldCode = obj.getDeclaredField(NetConfigUtils.instance.status)
                            val fieldMsg = obj.getDeclaredField(NetConfigUtils.instance.msg)
                            fieldCode.isAccessible = true
                            fieldMsg.isAccessible = true
                            val code = fieldCode.get(t)
                            val msg = fieldMsg.get(t)
                            when (code) {
                                NetConfigUtils.instance.responseSucc ->//成功
                                    httpClickLenerlist.onSucc(t)
                                NetConfigUtils.instance.responseTokenFail ->//token失效
                                    startAroutLeft(mContext, NetConfigUtils.instance.loginActivity)
                                else -> {// 失败
                                    Toast.makeText(mContext, "$msg", Toast.LENGTH_SHORT).show()
                                    httpClickLenerlist.onFail(t)
                                }
                            }
                        }

                        override fun onCompleted() {
                            if (dialog != null) dialog!!.dismiss()
                        }

                        override fun onError(e: Throwable?) {
                            if (dialog != null) dialog!!.dismiss()
                            Log.e(NetConfigUtils.instance.TAG, "onError: ${e.toString()}")
                        }
                    })
        }
    }

    /**
     * 销毁加载动画
     */
    fun dismiss() {
        if (dialog !== null)
            dialog!!.dismiss()
        dialog = null
    }

    /**
     * 显示加载动画
     */
    fun show(dialogCancelable: Boolean) {
        dialog?.setCancelable(dialogCancelable)
        if (dialog != null)
            dialog!!.show()
    }
}