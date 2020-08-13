package com.cloudpick.yunnasdk.enums

/**
 * Created by zxy on 2020/8/10 13:53
 * ******************************************
 * * Release服务器枚举
 * ******************************************
 */
enum class ServerEnum(val KEY: String, val VALUE: String) {
    TEST("http://10.10.10.130","http://img.yunatop.com/"),//测试服
    TEST2("http://10.10.13.96","http://img.yunatop.com/"),//测试服2
    TEST139("http://10.10.10.139","http://img.yunatop.com/"),//测试服139
    TEST_INTERNET("http://117.143.63.234:8081","http://img.yunatop.com/"),//外网测试服务器
    A("http://www.yunatop.com", "http://img.yunatop.com/"),//A服务器
    T("http://140.143.29.38", "http://img.yunatop.com/"),//T服务器
    KOREA("http://119.28.238.114", "https://cloudpick-japan.oss-ap-northeast-1.aliyuncs.com/"),//韩国服务器
    JAPAN("http://104.41.182.195", "http://cloudpick-japan.oss-ap-northeast-1.aliyuncs.com/"),//日本服务器
    AMERICA("http://52.167.168.251", "https://cloudpick-eus.oss-us-east-1.aliyuncs.com/"),//微软云-美国
    SINGAPORE("http://sgapp.cloudpick.store", "https://cloudpick-singapore.oss-ap-southeast-1.aliyuncs.com/"),//新加坡服务器
    BURMA("http://sgapp.cloudpick.store", "http://img.yunatop.com/"),//缅甸服务器

}