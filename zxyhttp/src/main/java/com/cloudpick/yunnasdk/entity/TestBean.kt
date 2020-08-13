package com.zxy.zxyhttp.entity

/**
 * Created by zxy on 2020/7/21 20:28
 * ******************************************
 * *
 * ******************************************
 */
data class TestBean(
    val code: String?,
    val success: Boolean?,
    val userInfo: UserInfo?
) {
    data class UserInfo(
        val country: String?,
        val gender: String?,
        val identityGrade: String?,
        val mobile: String?,
        val payBind: String?,
        val realName: String?,
        val registerChannel: String?,
        val status: String?,
        val statusDesc: String?,
        val type: String?,
        val typeDesc: String?,
        val userId: String?
    )
}