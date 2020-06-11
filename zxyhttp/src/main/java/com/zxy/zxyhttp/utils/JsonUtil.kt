package com.zxy.zxyhttp.utils


import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody


/**
 * Created by ZXY on 2019/9/5 11:52.
 * Class functions
 * *********************************************************
 * * 将map转换为Json 示例： 当后台需要传递@body参数的时候，可以用这个工具了转换
 * * val body:RequestBody = JsonUtil.mapToJson("userPhone", "18682026497", "usrePassword","123456")
 * *********************************************************
 */
object JsonUtil {
    //将map转换为Json
    fun mapToJson(vararg args: String): RequestBody {
        val map = HashMap<String, String>()
        for (i in args.indices step 2) {
            map[args[i]] = args[i + 1]
        }
        val json = Gson().toJson(map)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
    }
}