package com.zxy.zxyhttp.entity

/**
 * Created by zxy on 2019/9/10-11:11
 * Class functions
 * ******************************************
 * * 用户基类
 * ******************************************
 */
data class UserBean(
    val obj: Obj,
    val msg: String,
    val code: Int
)

data class Obj(
    val token: String
)