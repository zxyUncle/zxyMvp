package com.cloudpick.yunnasdk.tools

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.cloudpick.yunnasdk.YN


/**
 * Created by zxy on 2020/8/3 9:41
 * ******************************************
 * * 本地sp文件存储
 * ******************************************
 */
class SharedPreUtils {
    private var mSharedPreferences: SharedPreferences?=null
    val mContext: Context? by lazy {
        YN.mContext
    }

    //zxy 单例模式
    private constructor() {
        if (mContext != null)
            mSharedPreferences = mContext!!.getSharedPreferences(mContext!!.packageName, Context.MODE_PRIVATE)
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: SharedPreUtils? = null

        fun instance(): SharedPreUtils {
            if (instance == null) {
                synchronized(SharedPreUtils::class.java) {
                    if (instance == null) {
                        instance = SharedPreUtils()
                    }
                }
            }
            return instance!!
        }
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return mSharedPreferences?.getString(key, defaultValue) ?: ""
    }

    fun putString(key: String, value: String) {
        val edit = mSharedPreferences?.edit()
        edit?.putString(key, value)
        edit?.commit()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return mSharedPreferences?.getInt(key, defaultValue)?:-1
    }

    fun putInt(key: String, value: Int) {
        val edit = mSharedPreferences?.edit()
        edit?.putInt(key, value)
        edit?.commit()
    }

    fun getLong(key: String, defaultValue: Long = 0): Long {
        return mSharedPreferences?.getLong(key, 0)?:-1
    }

    fun putLong(key: String, value: Long) {
        val edit = mSharedPreferences?.edit()
        edit?.putLong(key, value)
        edit?.commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean? {
        return mSharedPreferences?.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        val edit = mSharedPreferences?.edit()
        edit?.putBoolean(key, value)
        edit?.commit()
    }

    fun <T> putBean(key: String, bean: T) {
        val toJson = Gson().toJson(bean)
        val edit = mSharedPreferences?.edit()
        edit?.putString(key, toJson)
        edit?.commit()
    }

    fun getParcelable() {

    }

    /**
     * 通过key进行删除
     *
     * @param key
     */
    fun remove(key: String) {
        val edit = mSharedPreferences?.edit()
        edit?.remove(key)
        edit?.commit()
    }

    /*
    * 清楚所有数据*/
    fun clearSp() {
        mSharedPreferences?.edit()?.clear()?.commit()
    }
}