package com.cloudpick.yunnasdk

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.cloudpick.yunnasdk.entity.*
import com.cloudpick.yunnasdk.tools.JsonUtil
import com.cloudpick.yunnasdk.tools.LoadTools
import com.cloudpick.yunnasdk.tools.ShowDialogTools
import com.cloudpick.yunnasdk.tools.rackview.RacksUtils

/**
 * Created by zxy on 2020/7/29 16:24
 * ******************************************
 * * 网路方法类
 * ******************************************
 */
class YNClient {
    //zxy 单例模式
    private constructor() {}

    companion object {
        @Volatile
        private var instance: YNClient? = null

        fun instance(): YNClient {
            if (instance == null) {
                synchronized(YNClient::class.java) {
                    if (instance == null) {
                        instance = YNClient()
                    }
                }
            }
            return instance!!
        }
    }

    //给不许要分页的接口
    interface OnResponceDoubleListener<X, Y> {
        fun onSucc(obj1: X, obj2: Y)

        fun onFail(obj:BaseBean)
    }

    //给不许要分页的接口
    interface OnResponceListener<T> {
        fun onSucc(obj: T)

        fun onFail(obj: T)
    }

    //给分页的接口
    interface OnResponceNetListener<T> {
        fun onSucc(obj: T)

        fun onFail(obj: T)

        fun onNoNetwork()
    }

    val mContext: Context? by lazy {
        YN.mContext
    }

    //zxy 1、登录模块

    /**
     * @see 1.1、发送短信验证码
     * @param captchaValue 图形验证码
     * @param mobile    手机号
     * @param region    区号：+86
     * @param channel   渠道
     * @return {"code":0}
     */
    fun sendsms(mContext: Context, map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .sendsms(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onNoNetwork() {}

            override fun onSucc(obj: BaseBean) {
                if (!obj.captchaUrl.isNullOrEmpty()) {//验证码
                    ShowDialogTools.showSMS(obj.captchaUrl
                            ?: "", mContext, map, onResponceListener)
                } else {
                    onResponceListener.onSucc(obj)
                }
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

        })
    }

    /**
     * @see 1.2、刷新图片验证码
     * @param mobile    手机号
     * @param system    app默认传：mer
     * @param channel   渠道
     * @return  {"code":0}
     */
    fun captcha(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .captcha(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 1.3、用户登录
     * @param mobile    手机号
     * @param smsCode   验证码
     * @param system    app默认传：mer
     * @param region    区号：+86
     * @param channel   渠道
     * @return {"code":0}
     */
    fun loginCode(map: Map<String, Any>, onResponceListener: OnResponceListener<LoginInfoBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .loginCode(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<LoginInfoBean> {
            override fun onSucc(obj: LoginInfoBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: LoginInfoBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 1.4、一个手机号在多个渠道下有账号 登录
     * @param userId    手机号
     * @param system    app默认传：mer
     * @return {"code":0}
     */
    fun mlogin(map: Map<String, Any>, onResponceListener: OnResponceListener<LoginInfoBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .mlogin(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<LoginInfoBean> {
            override fun onSucc(obj: LoginInfoBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: LoginInfoBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 1.5、用户退出
     * @return {"code":0}
     */
    fun logout(onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .logout()
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 1.6、EX：账号登录
     * @param userName    账户
     * @param password   密码
     * @param channel   渠道
     * @param expiry    过期时间：天
     * @return {"code":0}
     */
    fun loginAccount(map: Map<String, Any>, onResponceListener: OnResponceListener<LoginInfoBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .loginAccount(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<LoginInfoBean> {
            override fun onSucc(obj: LoginInfoBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: LoginInfoBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 1.7、EX：账号注册接口
     * @param userName          账户
     * @param password          密码
     * @param confirmPassword   确认密码
     * @param email             邮箱
     * @param vcCode            验证码
     * @param channel            渠道
     * @return {"code":0}
     */
    fun register(map: Map<String, Any>, onResponceListener: OnResponceListener<UserInfoBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .register(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<UserInfoBean> {
            override fun onSucc(obj: UserInfoBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: UserInfoBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 1.8、EX：修改密码
     * @param userId          账户ID
     * @param password          旧密码
     * @param newPassword       新密码
     * @param confirmPassword   确认密码
     * @param channel            渠道
     * @return {"code":0}
     */
    fun updataPassword(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataPassword(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 1.9、向邮箱发送验证码
     * @param email             邮箱
     * @param captchaValue      图形验证码
     * @param system            app默认传：mer
     * @param channel            渠道
     * @return {"code":0}
     */
    fun sendemail(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .sendemail(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    //zxy 2、监控界面

    /**
     * @see 2.1、店铺坐标
     * @param storeId             店铺ID
     */
    fun rackList(mContext: Context, storeId: String, onResponceListener: OnResponceListener<RackListBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .rackList(storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<RackListBean> {
            override fun onSucc(obj: RackListBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: RackListBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {

            }

        })
    }

    /**
     * @see 2.2、获取货架的高度
     * @param rackId             架子ID
     * @param storeId            店铺ID
     */
    fun rackHeight(mContext: Context, storeId: String, rackId: String, onResponceListener: OnResponceListener<RackInfoResBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .rackHeight(rackId, storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<RackInfoResBean> {
            override fun onSucc(obj: RackInfoResBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: RackInfoResBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.3、获取货架栏详情
     * @param rackId             架子ID
     * @param storeId            店铺ID
     */
    fun getRackDetail(mContext: Context, storeId: String, rackId: String, onResponceListener: OnResponceListener<ShelfBeanListBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .getRackDetail(rackId, storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<ShelfBeanListBean> {
            override fun onSucc(obj: ShelfBeanListBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: ShelfBeanListBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.4、闸机坐标
     * @param storeId            店铺ID
     */
    fun gateCoord(mContext: Context, storeId: String, onResponceListener: OnResponceListener<GatesBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .gateCoord(storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<GatesBean> {
            override fun onSucc(obj: GatesBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: GatesBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.5、查询闸机心跳
     * @param storeId            店铺ID
     */
    fun gateHeartbeat(storeId: String, onResponceListener: OnResponceListener<GateHeartBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .gateHeartbeat(storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<GateHeartBean> {
            override fun onSucc(obj: GateHeartBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: GateHeartBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.6、打开或者关闭闸机
     * @param storeId            店铺ID
     * @param sourceId           sourceId
     * @param command            open/close 打开关闭闸机
     */
    fun openOrCloseGatet(mContext: Context, storeId: String, sourceId: String, command: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .openOrCloseGatet(storeId, sourceId, JsonUtil.bodyVararg("command", command))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }


    /**
     * @see 2.7、查询商品
     * @param goodsId            店铺ID
     */
    fun queryGoods(mContext: Context, goodsId: String, onResponceListener: OnResponceListener<GoodsInfoListBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryGoods(goodsId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<GoodsInfoListBean> {
            override fun onSucc(obj: GoodsInfoListBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: GoodsInfoListBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.8、模糊查询商品
     * @param storeId            店铺ID
     * @param paging             切换分页，默认false查全部
     * @param goodsId            商品id
     * @param goodsName          商品名称 （支持模糊查询）
     * @param catalog            类别 六位一下数字
     * @param needCount          查询满足条件的商品总数 默认需要
     * @param queryPages         第一页查询页数 默认1
     * @param pageSize           每页查询数量 默认10
     * @param pageNum            页码 从1开始 默认1
     */
    fun queryDimShop(map: Map<String, Any>, onResponceListener: OnResponceListener<GoodsInfoListDataBean>) {
        val storeId = map["storeId"] ?: ""
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryDimShop(storeId as String, map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<GoodsInfoListDataBean> {
            override fun onSucc(obj: GoodsInfoListDataBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: GoodsInfoListDataBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.9、查询商品活动
     * @param storeId            店铺ID
     * @param goodsIds           goodsIds=123456&goodsIds=789  多个goodsId查询
     */
    fun queryShopActivity(storeId: String, goodsIds: String, onResponceListener: OnResponceListener<ShopActivityBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryShopActivity(storeId, goodsIds)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<ShopActivityBean> {
            override fun onSucc(obj: ShopActivityBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: ShopActivityBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.10、修改栏位商品
     * @param landID                栏位ID
     * @param defaultGoodsId        栏位默认商品
     * @param goodsList             {"goodsId":goodsId,"goodsNum":1]}需要修改为商品的数组 -GoodsInfoBean
     */
    fun updataLandGoods(mContext: Context, map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val landID = map["landID"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataLandGoods(landID as String? ?: "", JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.11、查询最大进店人数
     * @param storeId                店铺ID
     */
    fun queyStoreMaxNumber(mContext: Context, storeId: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queyStoreMaxNumber(storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.11、设置最大进店人数
     * @param storeId                店铺ID
     * @param limitCustomerNumber    设置进店人数
     */
    fun setStoreMaxNumber(mContext: Context, storeId: String, limitCustomerNumber: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .setStoreMaxNumber(storeId, JsonUtil.bodyVararg("limitCustomerNumber", limitCustomerNumber))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.12、屏蔽商店事件
     * @param storeId                店铺ID
     * @param type                  默认传“store”字符串，"type":"store"
     * @param deviceId              传storeId
     * @param times                 时间戳
     */
    fun shieldShopEvent(mContext: Context, map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .shieldShopEvent(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.13、查询脏栏
     * @param storeId                店铺ID
     */
    fun queryMisplaceRack(mContext: Context, storeId: String, onResponceListener: OnResponceListener<LandBeanRequest>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryMisplaceRack(storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<LandBeanRequest> {
            override fun onSucc(obj: LandBeanRequest) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: LandBeanRequest) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 2.14、查询商品所在栏位
     * @param storeId                店铺ID
     * @param goodsId                商品ID
     */
    fun queryShopRack(mContext: Context, storeId: String, goodsId: String, onResponceListener: OnResponceListener<LaneInfoBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryShopRack(storeId, goodsId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<LaneInfoBean> {
            override fun onSucc(obj: LaneInfoBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: LaneInfoBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.15、重新识别栏位商品数量
     * @param storeId                店铺ID
     * @param rackId                 栏位ID
     */
    fun recognitionRackShopNum(storeId: String, rackId: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .recognitionRackShopNum(storeId, rackId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.16、罗列货架传感器配置
     * @param storeId                店铺ID
     * @param rackId                 栏位ID
     */
    fun queryStoreSensorLoca(storeId: String, rackId: String, onResponceListener: OnResponceListener<SensorListResultBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStoreSensorLoca(storeId, rackId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<SensorListResultBean> {
            override fun onSucc(obj: SensorListResultBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: SensorListResultBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.17、获取货架传感器记录（三小时以内）
     * @param storeId                店铺ID
     * @param rackId                 栏位ID
     */
    fun queryStoreSensorRecordList(storeId: String, rackId: String, onResponceListener: OnResponceListener<SensorRecordBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStoreSensorRecordList(storeId, rackId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<SensorRecordBean> {
            override fun onSucc(obj: SensorRecordBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: SensorRecordBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.18、按时间查询单个传感器记录
     * @param storeId                店铺ID
     * @param sensorId               栏位ID
     * @param fromDate               yyyy-MM-dd HH:mm:ss
     * @param toDate                yyyy-MM-dd HH:mm:ss
     */
    fun queryTimeSensorRecord(storeId: String, sensorId: String,
                              fromDate: String, toDate: String, onResponceListener: OnResponceListener<SingleSensorRecordBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryTimeSensorRecord(storeId, sensorId, fromDate, toDate)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<SingleSensorRecordBean> {
            override fun onSucc(obj: SingleSensorRecordBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: SingleSensorRecordBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.19、补货：店铺补货货架种类
     * @param storeId                店铺ID
     */
    fun queryReplenishmentType(storeId: String, onResponceListener: OnResponceListener<ReplenishmentGroupRequest>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryReplenishmentType(storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<ReplenishmentGroupRequest> {
            override fun onSucc(obj: ReplenishmentGroupRequest) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: ReplenishmentGroupRequest) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.20、补货：根据货架组ID查询货架列表
     * @param rackGroupId            货架组ID
     * @param storeId                店铺ID
     */
    fun queryStoreList(storeId: String, rackGroupId: String, onResponceListener: OnResponceListener<ReplenishmentGroupofRackIdsRequest>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStoreList(storeId, rackGroupId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<ReplenishmentGroupofRackIdsRequest> {
            override fun onSucc(obj: ReplenishmentGroupofRackIdsRequest) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: ReplenishmentGroupofRackIdsRequest) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.21、补货：根据货架ID列表查询补货商品信息列表接口
     * @param storeId                店铺ID
     * @param rackIds                rackIds=001&rackIds=002&rackIds=003
     */
    fun queryReplenishmentList(storeId: String, rackIds: String, onResponceListener: OnResponceListener<ReplenishmentGoodsRequest>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryReplenishmentList(storeId, rackIds)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<ReplenishmentGoodsRequest> {
            override fun onSucc(obj: ReplenishmentGoodsRequest) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: ReplenishmentGoodsRequest) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.22、补货：查询栏位商品补货提醒值
     * @param goodsId                商品ID
     * @param storeId                商店ID
     * @param laneId                 栏位ID
     * @param goodsName              商品名称
     */
    fun querySensorShopRemind(goodsId: String, storeId: String, laneId: String, goodsName: String, onResponceListener: OnResponceListener<SensorShopRemindBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .querySensorShopRemind(goodsId, storeId, laneId, goodsName)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<SensorShopRemindBean> {
            override fun onSucc(obj: SensorShopRemindBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: SensorShopRemindBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.23、补货：设置货架提醒数量
     * @param goodsId                商品ID
     * @param storeId                商店ID
     * @param laneId                 栏位ID
     * @param goodsName              商品名称
     * @param adviceNum              摆放数量（建议）
     * @param remindNum              缺货提醒数量
     */
    fun setRackRemindNum(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .setRackRemindNum(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 2.24、补货：根据店铺ID查询店铺是否有补货提醒的角标显示
     * @param storeId                商店ID
     */
    fun quneryMarkRemind(storeId: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .quneryMarkRemind(storeId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    //zxy 3、管理

    /**
     * @see 3.1.1、获取顾客未结算购物车
     * @param qrCode            用户进门二维码
     * @param userId            用户userId
     * @param storeId           storeId
     * @param mobile            用户手机号
     * @param flag                "flag":"normal" 默认写normal
     */
    fun unPayCard(map: Map<String, Any>, onResponceListener: OnResponceListener<CartBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .unPayCard(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<CartBean> {
            override fun onSucc(obj: CartBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: CartBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 3.1.2、查询库存商品
     * @param storageId         storageId
     * @param goodsId           goodsId
     * @param pageSize          pageSize默认10
     * @param pageNum           pageNum默认从1开始
     */
    fun queryStorageShopStorageShop(map: Map<String, Any>, onResponceNetListener: OnResponceNetListener<GoodsStockBean>) {
        var storageId = map["storageId"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStorageShop(storageId as String? ?: "", map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<GoodsStockBean> {
            override fun onSucc(obj: GoodsStockBean) {
                onResponceNetListener.onSucc(obj)
            }

            override fun onFail(obj: GoodsStockBean) {
                onResponceNetListener.onFail(obj)
            }

            override fun onNoNetwork() {
                onResponceNetListener.onNoNetwork()
            }

        })
    }

    /**
     * @see 3.1.3、生成订单
     * @param userId          账户ID
     * @param cartId          购物车id
     * @param flag            "flag":"normal" 默认写normal
     * @param storeId           storeId
     * @param goodsInfo            数组 GoodsInfoBean
     *--------------------------------------------
     * @param 数组goodsInfo
     * @param goodsId            goodsId
     * @param goodsPhoto        goodsPhoto
     * @param goodsPrice        商品价格
     * @param goodsNum            数量
     * @param goodsTax            税费
     *
     */
    fun createOrder(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .createOrder(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {}

        })
    }

    /**
     * @see 3.1.4、商品面板分类
     * @param storeID
     */
    fun panelGoodsGroup(storeID: String, onResponceListener: OnResponceListener<PanelGroupRequest>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .panelGoodsGroup(storeID)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<PanelGroupRequest> {
            override fun onSucc(obj: PanelGroupRequest) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: PanelGroupRequest) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.1.5、查询面板商品
     * @param storeId           storeId
     * @param groupId           组id
     * @param pageSize          pageSize默认10
     * @param pageNum           pageNum默认从1开始
     */
    fun panelGoods(storeID: String, storeId: String, groupId: MutableList<String>, pageSize: String, pageNum: String,
                   onResponceNetListener: OnResponceNetListener<PanelDataBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .panelGoods(storeID, storeId, groupId, pageSize, pageNum)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<PanelDataBean> {
            override fun onSucc(obj: PanelDataBean) {
                onResponceNetListener.onSucc(obj)
            }

            override fun onFail(obj: PanelDataBean) {
                onResponceNetListener.onFail(obj)
            }

            override fun onNoNetwork() {
                onResponceNetListener.onNoNetwork()
            }

        })
    }

    //zxy 3.2、订单信息

    /**
     * @see 3.2.1、订单列表
     * @param storeId          商店id
     * @param qrCode           非必传：用户二维码
     * @param mobile          非必传：用户手机号
     * @param startDate       非必传：yyyy-MM-dd HH:mm:ss
     * @param endDate         非必传：yyyy-MM-dd HH:mm:ss
     * @param pageNum         pageSize默认10
     * @param pageSize        pageNum默认从0开始
     */
    fun orderList(map: Map<String, Any>, onResponceNetListener: OnResponceNetListener<OrderListBean>) {
        val storeID = map["storeID"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .orderList(storeID as String? ?: "", map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<OrderListBean> {
            override fun onSucc(obj: OrderListBean) {
                onResponceNetListener.onSucc(obj)
            }

            override fun onFail(obj: OrderListBean) {
                onResponceNetListener.onFail(obj)
            }

            override fun onNoNetwork() {
                onResponceNetListener.onNoNetwork()
            }

        })
    }

    /**
     * @see 3.2.2、订单详情
     * @param orderId 订单ID
     */
    fun orderDetail(orderId: String, onResponceListener: OnResponceListener<OrderDetailBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .orderDetail(orderId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<OrderDetailBean> {
            override fun onSucc(obj: OrderDetailBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: OrderDetailBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.2.3、订单支付流水
     * @param orderId 订单ID
     */
    fun orderPayWater(orderId: String, onResponceListener: OnResponceListener<OrderTransInfoBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .orderPayWater(orderId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<OrderTransInfoBean> {
            override fun onSucc(obj: OrderTransInfoBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: OrderTransInfoBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.2.4、订单退款
     * @param userId 退款顾客userId
     * @param orderId 订单ID
     * @param refundDesc 退款原因描述
     * @param storeId 商店id
     * @param superfluousGoods 退款商品数组 GoodsInfoBean
     * ----------------------------------
     * @param goodsId goodsId
     * @param goodsPrice 商品价格
     * @param goodsNum 退款商品数量
     */
    fun orderRefund(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .orderRefund(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.2.5、修改商品价格
     * @param storeId 店铺id
     * @param price 修改到商品的价格
     * @param goodsId 商品id
     */
    fun updataShopPrice(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataShopPrice(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.2.6、查询商品的库存流水
     * @param goodsId 店铺id
     * @param storageId 仓库ID
     * @param startTime 时间戳：毫秒
     * @param endTime 时间戳：毫秒
     */
    fun storageWater(map: Map<String, Any>, onResponceListener: OnResponceListener<GoodsBatchDetailBean>) {
        val storageId = map["storageId"] ?: ""
        val observable = OkHttpManager.instance.apiService(mContext)
                .storageWater(storageId as String, map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<GoodsBatchDetailBean> {
            override fun onSucc(obj: GoodsBatchDetailBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: GoodsBatchDetailBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.2.7、查询商品负库存流水
     * @param storageId 仓库ID
     * @param goodsId 店铺id
     */
    fun queyMinusStorageWater(storageId: String, goodsId: String, onResponceListener: OnResponceListener<NegativeStorageGoodsRequest>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queyMinusStorageWater(storageId, goodsId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<NegativeStorageGoodsRequest> {
            override fun onSucc(obj: NegativeStorageGoodsRequest) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: NegativeStorageGoodsRequest) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 3.3、查询顾客信息

    /**
     * @see 3.3.1、查询顾客信息
     * @param channel 渠道
     * @param storeId 商品id
     * @param qrCode 非必传：用户QRCode
     * @param mobile 非必传：用户手机号
     * @param userId 非必传：用户userId
     */
    fun queryClient(map: Map<String, Any>, onResponceListener: OnResponceListener<UserInfoDataBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryClient(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<UserInfoDataBean> {
            override fun onSucc(obj: UserInfoDataBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: UserInfoDataBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.3.2、查询用户信息
     * @param userId
     */
    fun queryUser(userId: String, onResponceListener: OnResponceListener<UserInfoBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryUser(userId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<UserInfoBean> {
            override fun onSucc(obj: UserInfoBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: UserInfoBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.3.3、修改用户信息
     * @param realName      真实姓名
     * @param nickName      昵称
     * @param gender        性别：男：00-女：01-未知：02
     * @param city          城市
     * @param province      省
     * @param country       国家
     * @param birthday      生日
     * @param email         邮箱
     */
    fun updataUser(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val userId = map["userId"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataUser(userId as String? ?: "", JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.3.4、查询顾客优惠券
     * @param userId
     * @param channel   渠道
     */
    fun queryUserCoupon(userId: String, channel: String, onResponceListener: OnResponceListener<UserCouponsBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryUserCoupon(userId, channel)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<UserCouponsBean> {
            override fun onSucc(obj: UserCouponsBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: UserCouponsBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.3.5、优惠券类型查询
     * @param channel   渠道
     * @param mode      默认传：APPLY
     * @param couponType SUPPORT_CASH
     */
    fun queryCouponType(channel: String, mode: String, couponType: String, onResponceListener: OnResponceListener<CouponListBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryCouponType(channel, mode, couponType)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<CouponListBean> {
            override fun onSucc(obj: CouponListBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: CouponListBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.3.6、发送优惠券
     * @param channel   渠道
     * @param mode      默认传：APPLY
     * @param couponType SUPPORT_CASH
     * -----------------------------------
     * @param count 优惠券数量
     * @param couponTemplateId 优惠券类型
     */
    fun sendCoupon(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .sendCoupon(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.3.7、查询卡券类型
     * @param channel   渠道
     * @param mode      默认传：APPLY
     */
    fun queryCouponCardType(channel: String, mode: String, onResponceListener: OnResponceListener<CardReelTypeBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryCouponCardType(channel, mode)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<CardReelTypeBean> {
            override fun onSucc(obj: CardReelTypeBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: CardReelTypeBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }


    /**
     * @see 3.3.8、发送卡券
     * @param templateId   卡券id
     * @param userId      userId
     */
    fun sendCouponCard(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .sendCouponCard(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.3.9、查询用户卡券
     * @param userId   userId
     * @param channel   渠道
     */
    fun queryUserCouponCard(userId: String, channel: String, onResponceListener: OnResponceListener<UserCardsBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryUserCouponCard(userId, channel)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<UserCardsBean> {
            override fun onSucc(obj: UserCardsBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: UserCardsBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 3.4、外卖

    /**
     * @see 3.4.1、添加外卖
     * @param userId        用户userId
     * @param takeoutId     外卖id
     * @param takeoutType   外卖类型美团，饿了么
     * @param storeId       商店id
     * @param remark        备注
     * @param goodsList     数组 GoodsInfoBean
     * --------------------------------------------
     * @param goodsId      goodsId
     * @param goodsNum     数量
     */
    fun addTakeOut(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .addTakeOut(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.4.2、查询外卖
     * @param userId            userId
     * @param takeoutId         外卖单id
     * @param takeoutType        外卖平台类型美团，饿了么
     * @param storeId           商店id
     * @param packageId         数组-外卖商品ID
     * @param status   背包状态I("I", "待接包"),II("II", "待取货"),P("P", "取货中"),S("S", "完成");直接多个状态查询，用逗号隔开
     * @param isNeedGoods         是否需要背包商品，默認false
     */
    fun queryTakeOut(map: Map<String, Any>, packageIds: MutableList<String>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryTakeOut(map, packageIds)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.4.3、更新外卖订单
     * @param packageId        外卖单id
     * @param type           remove 移除商品add添加商品
     * @param remark        备注
     * @param storeId       商店id
     * @param packageGoods  数组GoodsInfoBean
     * --------------------------------------------
     * @param goodsId      goodsId
     * @param goodsNum     数量
     */
    fun updataTakeOut(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataTakeOut(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 3.4.4、一键补满背包
     * @param storeId       商店id
     * @param packageId     背包id
     */
    fun oneReplenishTakeOut(storeId: String, packageId: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .oneReplenishTakeOut(storeId, packageId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 4、报表

    /**
     * @see 4.1、订单报表
     * @param storeId       商店id
     * @param beginDate     背包id
     * @param endDate     背包id
     */
    fun orderForm(storeId: String, beginDate: String, endDate: String, onResponceListener: OnResponceListener<StatementLIstBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .orderForm(storeId, beginDate, endDate)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<StatementLIstBean> {
            override fun onSucc(obj: StatementLIstBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: StatementLIstBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 4.2、查询当日订单详情
     * @param orderType     订单类型，默认传：00
     * @param beginDate     yyyy-MM-dd HH:mm:ss
     * @param endDate       yyyy-MM-dd HH:mm:ss
     */
    fun queryOrderDetail(orderType: String, beginDate: String, endDate: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryOrderDetail(orderType, beginDate, endDate)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }
    //zxy 5、进销存

    //zxy 5.1、仓库进货&退货

    /**
     * @see 5.1.1、供应商列表
     * @param channel       渠道
     * @param valid         默认传Y:valid=Y
     */
    fun providerList(channel: String, valid: String, onResponceListener: OnResponceListener<ProviderListBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .providerList(channel, valid)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<ProviderListBean> {
            override fun onSucc(obj: ProviderListBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: ProviderListBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.2、查询我能看到的仓库列表
     */
    fun queryStorageList(onResponceListener: OnResponceListener<StorageListBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStorageList()
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<StorageListBean> {
            override fun onSucc(obj: StorageListBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: StorageListBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.3、查询所有的仓库列表
     */
    fun queryAllStorageList(onResponceListener: OnResponceListener<StorageListBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryAllStorageList()
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<StorageListBean> {
            override fun onSucc(obj: StorageListBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: StorageListBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.4、查询仓库录入和仓库退货列表
     * @param dateStart        yyyy-MM-dd HH:mm:ss
     * @param dateEnd          yyyy-MM-dd HH:mm:ss
     * @param flag             进货：storage-退货：putout
     */
    fun queryStorageGoodsList(dateStart: String, dateEnd: String, flag: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStorageGoodsList(dateStart, dateEnd, flag)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.5、克隆入库
     * @param batchNo           批次号
     * @param bizDate           毫秒时间戳
     * @param signer            签收人
     * @param storageId         仓库id
     * @param supplier          供应商id
     * @param desc              描述
     * @param type              默认传1
     */
    fun cloneStorage(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val storageId = map["storageId"]
        val batchNo = map["batchNo"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .cloneStorage(storageId as String? ?: "", batchNo as String?
                        ?: "", JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.6、获取批次号
     * @param type        0=出库、1=入库、4=录入损耗批次号、5=盘点
     */
    fun getBatchNum(type: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .getBatchNum(type)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.7、获取批次详情
     * @param batchNo        批次号
     */
    fun getBatchNumDetail(batchNo: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .getBatchNumDetail(batchNo)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.8、预入库
     * @param batchNo       批次号
     * @param bizDate       进货日期
     * @param goodsId       商品id
     * @param num           数量
     * @param pd            非必填：生产日期
     * @param recordId      记录号
     * @param signer        操作员
     * @param storageId     仓库号
     * @param supplier      供应商
     * @param desc          描述
     * @param sumPrice      总成本费
     * @param extraFee      非必填：额外费用
     * @param discountAmt   非必填：折扣费用
     * @param taxFee        非必填：税费
     */
    fun inStorage(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .inStorage(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.9、预出库
     * @param batchNo       批次号
     * @param bizDate       进货日期
     * @param goodsId       商品id
     * @param num           数量
     * @param pd            非必填：生产日期
     * @param recordId      记录号
     * @param signer        操作员
     * @param storageId     仓库号
     * @param supplier      供应商
     * @param sumPrice      总成本费
     * @param reason        退货理由
     */
    fun outStorage(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .outStorage(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.10、出入库批次确认
     * @param batchNo       批次号
     */
    fun storageBatchNumConfig(batchNo: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .storageBatchNumConfig(batchNo)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.11、删除批次商品
     * @param batchNo       批次号
     * @param recordId      记录id
     * @param storageId     仓库
     * @param goodsId       商品的id
     */
    fun delBatchNumGood(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val batchNo: String = map["batchNo"] as String
        val recordId = map["recordId"] as String
        val storageId = map["storageId"] as String
        val goodsId = map["goodsId"] as String
        val observable = OkHttpManager.instance.apiService(mContext)
                .delBatchNumGood(batchNo, recordId, storageId, JsonUtil.bodyVararg("goodsId", goodsId))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.12、查询供应商商品列表
     * @param goodsId       商品id
     * @param supplierId    供应商id
     * @param channel       channel
     */
    fun queryProGoodList(goodsId: String, supplierId: String, channel: String, onResponceListener: OnResponceListener<GoodsInfoListDataBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryProGoodList(goodsId, supplierId, channel)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<GoodsInfoListDataBean> {
            override fun onSucc(obj: GoodsInfoListDataBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: GoodsInfoListDataBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.1.13、根据供应商查询是否税率使用情况
     * @param channel       channel
     * @param supplierId    供应商id
     * @param vaild         默认：Y
     */
    fun queryTaxRate(channel: String, supplierId: String, vaild: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryTaxRate(channel, supplierId, vaild)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 5.2、库存

    /**
     * @see 5.2.1、查询商品库存流水
     * @param storageId     仓库id
     * @param pageSize      默认：10
     * @param pageNum       默认：1
     * @param goodsId       非必传：商品的id
     */
    fun queryGoodStorageWater(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val storageId = map["storageId"] as String
        val pageSize = map["pageSize"] as String
        val pageNum = map["pageNum"] as String
        val goodsId = map["goodsId"] as String
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryGoodStorageWater(storageId, pageSize, pageNum, goodsId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.2.2、查询负库存商品接口
     * @param storageId     仓库id
     * @param pageSize      默认：10
     * @param pageNum       默认：1
     * @param goodsId       非必传：商品的id
     */
    fun queryStorageGood(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val storageId = map["storageId"] as String
        val pageSize = map["pageSize"] as String
        val pageNum = map["pageNum"] as String
        val goodsId = map["goodsId"] as String
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStorageGood(storageId, pageSize, pageNum, goodsId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.2.3、强制调整商品库存
     * @param storageId      库ID
     * @param sumPrice      单价
     * @param num           数量
     * @param goodsId       非必传：商品的id
     */
    fun updateStorageNum(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val storageId = map["storageId"] as String
        val observable = OkHttpManager.instance.apiService(mContext)
                .updateStorageNum(storageId, JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.2.4、强平负库存
     * @param storageId      库ID
     * @param goodsId       商品id
     */
    fun storageNegative(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .storageNegative(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 5.3、盘点

    /**
     * @see 5.3.1、盘点列表
     * @param storageId      库ID
     * @param pageSize       默认：10
     * @param pageNum        默认：1
     */
    fun checkList(storageId: String, pageSize: String, pageNum: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .checkList(storageId, pageSize, pageNum)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.2、查询盘点结果
     * @param batchNo      批次号
     */
    fun queryCheckReslt(batchNo: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryCheckReslt(batchNo)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.3、废除盘点号
     * @param batchNo      批次号
     * @param storageId    仓库号
     */
    fun delCheckCall(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .delCheckCall(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.4、结束盘点
     * @param batchNo      批次号
     * @param storageId    仓库号
     */
    fun overCheck(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .overCheck(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.5、获取盘点批次号
     * @param storageId    仓库号
     */
    fun getCheckCall(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .getCheckCall(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.6、执行并且校验负库存
     * @param storageId    仓库号
     */
    fun execuStorage(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .execuStorage(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.7、添加新的盘点商品
     * @param storageId     仓库号
     * @param flowId        流水id
     * @param batchNo       批次号
     * @param goodsId       商品id
     * @param goodsName    商品名称
     * @param num           数量
     * @param remark        非必填：备注
     */
    fun addInventoryGood(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val flowId = map["flowId"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .addInventoryGood(flowId as String? ?: "", JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.8、更新盘点商品
     * @param flowId        流水id
     * @param num           数量
     * @param remark        非必填：备注
     */
    fun updataInventoryGood(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val flowId = map["flowId"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataInventoryGood(flowId as String? ?: "", JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.9、查询录入的盘点数据
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param operator      操作人
     * @param pageNum       默认：1
     * @param pageSize      默认：10
     */
    fun selectInventoryList(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .selectInventoryList(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.10、用户确认盘点数据
     * @param batchNo       批次号
     * @param storageId     仓库号
     */
    fun usreConfigInventory(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .usreConfigInventory(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.11、查询指定批次的盘点快照
     * @param batchNo       批次号
     * @param storageId     非必填：仓库号
     * @param goodsId       非必填：商品id
     * @param goodsName     非必填：商品名称
     */
    fun queryInventorySnapshot(batchNo: String, storageId: String, goodsId: String, goodsName: String, onResponceListener: OnResponceListener<InventorySnapshotBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryInventorySnapshot(batchNo, storageId, goodsId, goodsName)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<InventorySnapshotBean> {
            override fun onSucc(obj: InventorySnapshotBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: InventorySnapshotBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.12、插入盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId     商品id
     * @param goodsName     商品名称
     * @param inventoryNum     盘点数量
     */
    fun insertInventorySnapshot(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .insertInventorySnapshot(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.13、删除盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param gmtModified   yyyy-MM-dd HH:mm:ss
     */
    fun delInventorySnapshot(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .delInventorySnapshot(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.14、修改盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param gmtModified   yyyy-MM-dd HH:mm:ss
     * @param inventoryNum  盘点数量
     */
    fun updataInventorySnapshot(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataInventorySnapshot(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.15、复核指定批次的盘点快照（修改库存）
     * @param batchNo       批次号
     * @param storageId     仓库号
     */
    fun reviewInventorySnapshot(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .reviewInventorySnapshot(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.3.16、刷新盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       非必填：商品id
     */
    fun refreshInventorySnapshot(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .refreshInventorySnapshot(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 5.4、损耗

    /**
     * @see 5.4.1、录入损耗
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param goodsName     商品名称
     * @param type          1溢出/2损耗
     * @param num           数量
     * @param flowId        流水id
     * @param des           描述
     * @param bizDate       时间戳，毫秒
     */
    fun addLoss(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .addLoss(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.4.2、查询溢出损耗（多条件）
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param beginDate     时间戳，毫秒
     * @param endDate       时间戳，毫秒
     * @param type          1溢出/2损耗
     * @param pageNum       默认：1
     * @param pageSize      默认：10
     */
    fun queryLoss(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryLoss(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 5.5、调拨


    /**
     * @see 5.5.1、调拨批次查询
     * @param batchNo           批次号
     * @param fromStorageId     调出商品仓库
     * @param toStorageId       调入商品仓库
     * @param goodsId           非必填：商品id
     * @param beginDate         非必填：时间戳，毫秒
     * @param endDate           非必填：时间戳，毫秒
     * @param pageNum           非必填：默认：1
     * @param pageSize          非必填：默认：10
     */
    fun queryStorageTransfer(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStorageTransfer(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.5.2、调拨成本价格查询
     * @param fromStorageId     调出商品仓库
     * @param toStorageId       调入商品仓库
     * @param goodsId           非必填：商品id
     */
    fun queryCostStorageTransfer(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryCostStorageTransfer(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.5.3、新增调拨商品 //todo 有异议的接口
     * @param batchNo           批次号
     * @param fromStorageId     调出商品仓库
     * @param toStorageId       调入商品仓库
     * @param goodsId           非必填：商品id
     * @param transferPrice     调拨的价格(默认出库价格)
     * @param num               商品的数量(0:删除,>0:新增或者修改)
     * @param transferDate      非必填：时间戳，毫秒
     * @param signer            调拨人
     */
    fun addCostStorageTransfer(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .addCostStorageTransfer(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.5.4、调拨确认
     * @param batchNo           批次号
     */
    fun confirmStorageTransfer(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .confirmStorageTransfer(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 5.6、采购系统

    /**
     * @see 5.6.1、查询面板商品分组
     */
    fun queryPanelGoodGroup(onResponceListener: OnResponceListener<PanelGoodGroupBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryPanelGoodGroup()
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<PanelGoodGroupBean> {
            override fun onSucc(obj: PanelGoodGroupBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: PanelGoodGroupBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.2、查询面板候选商品
     * @param   groupId     分组id
     * @param   pageNum     非必填：默认：1
     * @param   pageSize    非必填：默认：10
     * @param   status      默认传1
     */
    fun queryPanelGood(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryPanelGood(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.3、新增采购/退货申请单接口
     * @param   storageId       仓库id
     * @param   storageName     仓库名
     * @param   operator        操作人
     * @param   applyType      采购：0,退货：1
     * @param   remark          备注
     * @param   purApplyDetailList      数组 -PurApplyDetailBean
     * ---------------------------------------
     * @param   goodsId          商品id
     * @param   goodsName        商品名称
     * @param   sku             sku
     * @param   num             数量
     * @param   basicApplyNum   基本订购数
     */
    fun addProcurement(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .addProcurement(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.4、修改采购/退货申请
     * @param   purApplyDetailList      数组 -PurApplyDetailBean
     * ---------------------------------------
     * @param   goodsId          商品id
     * @param   goodsName        商品名称
     * @param   sku             sku
     * @param   num             数量
     * @param   basicApplyNum   基本订购数
     */
    fun updataProcurement(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .updataProcurement(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.5、提交采购/退货申请
     * @param   storageId       库ID
     * @param   applyId         采购：0,退货：1
     * @param   operator        操作人
     */
    fun subProcurement(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val storageId = map["storageId"]
        val applyId = map["applyId"]
        val observable = OkHttpManager.instance.apiService(mContext)
                .subProcurement(storageId as String? ?: "", applyId as String?
                        ?: "", JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.6、查询采购/退货申请
     * @param   applyType       采购：0,退货：1
     * @param   storageId       仓库id
     * @param   pageNum         非必传：默认：1
     * @param   pageSize        非必传：默认：10
     * @param   beginDate       非必传：时间戳：毫秒
     * @param   endDate         非必传：时间戳：毫秒
     */
    fun queryProcurement(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryProcurement(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.7、查询采购/退货申请单详情
     * @param   storageId       仓库id
     * @param   applyId         采购：0,退货：1
     */
    fun queryProcurementDetail(storageId: String, applyId: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryProcurementDetail(storageId, applyId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.8、查询配送单列表
     * @param   storageId       仓库id
     * @param   pageNum         非必传：默认：1
     * @param   pageSize        非必传：默认：10
     * @param   startDate       非必传：时间戳：毫秒
     * @param   endDate         非必传：时间戳：毫秒
     */
    fun queryDistributionList(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryDistributionList(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.9、查询配送单详情列表
     * @param   deliveryId       配送单ID
     */
    fun queryDistributionDeatil(deliveryId: String, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryDistributionDeatil(deliveryId)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see 5.6.10、查询库存变动信息汇总变动详情
     * @param   storageId       库Id
     * @param   goodsId         商品id
     * @param   startTime       非必传：时间戳：毫秒
     * @param   entTime         非必传：时间戳：毫秒
     * @param   pageSize        非必传：默认：10
     * @param   pageNum         非必传：默认：1
     */
    fun queryStorageChangeDetail(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val storageId = map["storageId"] as String? ?: ""
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryStorageChangeDetail(storageId, map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 6、我的

    /**
     * @see  6.1、获取我能看到的商店列表
     */
    fun getMyShopList(onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .getMyShopList()
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see  6.2、查询版本号
     * @param   bizType         (00用户，01商户)',
     * @param   osType          APP终端类型：(android，ios)',
     * @param   channel         渠道
     * @param   version        版本号
     */
    fun queryVersion(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .queryVersion(map)
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see  6.3、拉黑操作
     * @param   userId          用户userid
     * @param   desc            描述
     * @param   day             拉黑时间
     */
    fun blacklist(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .blacklist(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see  6.4、二维码
     * @param   userId          用户userid
     * @param   token           用户token
     */
    fun qrCode(map: Map<String, Any>, onResponceListener: OnResponceListener<QrCodeBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .qrCode(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<QrCodeBean> {
            override fun onSucc(obj: QrCodeBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: QrCodeBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see  6.5、用户权限集合
     * @param   type            ui
     * @param   owners           mgr，mer，jxc,acc
     */
    fun userJurisdiction(onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .userJurisdiction()
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    /**
     * @see  6.6、意见反馈
     * @param   userId            用户userid
     * @param   feedbackType      问题：problem,意见：advice
     * @param   desc              描述
     */
    fun feedback(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .feedback(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }


    /**
     * @see  6.7、向后台注册极光推送
     * @param   userId              用户userid
     * @param   receiverId          极光的registrationID
     * @param   type                JPUSH
     * @param   appkey              极光appkey
     */
    fun sdkInfo(map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        val observable = OkHttpManager.instance.apiService(mContext)
                .sdkInfo(JsonUtil.bodyMap(map))
        OkHttpManager.instance.CallObserDialog(observable, object : OkHttpManager.HttpClickLenerlist<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                onResponceListener.onSucc(obj)
            }

            override fun onFail(obj: BaseBean) {
                onResponceListener.onFail(obj)
            }

            override fun onNoNetwork() {
            }

        })
    }

    //zxy 组合接口

    /**
     *  设置token
     */
    fun setToken(token: String) {
        OkHttpManager.instance.token = token
    }

    /**
     *  清空token
     */
    fun clearToken() {
        OkHttpManager.instance.token = ""
    }


    /**
     * @see  zxy 开店
     * @param   1)取消屏蔽商店事件  2)设置最大进店人数为XX
     * @param storeId                店铺ID
     * @param type                  默认传“store”字符串，"type":"store"
     * @param deviceId              传storeId
     * @param limitCustomerNumber   开店人数
     */
    fun openStore(mContext: Context, map: Map<String, Any>, onResponceListener: OnResponceListener<BaseBean>) {
        (map as HashMap)["times"] = "1"//取消屏蔽事件
        LoadTools.instance().showMultistage(mContext)
        shieldShopEvent(mContext, map, object : OnResponceListener<BaseBean> {
            override fun onSucc(obj: BaseBean) {
                setStoreMaxNumber(mContext, map["storeId"] as String?
                        ?: "", map["limitCustomerNumber"] as String?
                        ?: "", object : OnResponceListener<BaseBean> {
                    override fun onSucc(obj: BaseBean) {
                        LoadTools.instance().hideMultistage()
                        onResponceListener.onSucc(obj)
                    }

                    override fun onFail(obj: BaseBean) {
                        LoadTools.instance().hideMultistage()
                        onResponceListener.onFail(obj)
                    }

                })
            }

            override fun onFail(obj: BaseBean) {
                LoadTools.instance().hideMultistage()
            }

        })
    }

    /**
     * @see  zxy 关店
     * @param   1)设置最大进店人数为0 2)屏蔽商店事件
     * @param storeId                店铺ID
     * @param type                  默认传“store”字符串，"type":"store"
     * @param deviceId              传storeId
     * @param times                 时间戳
     */
    fun closeStore(mContext: Context, map: Map<String, Any>, onResponceListener: YNClient.OnResponceListener<BaseBean>) {
        LoadTools.instance().showMultistage(mContext)
        //2.12、屏蔽商店事件
        shieldShopEvent(mContext, map, object : YNClient.OnResponceListener<BaseBean> {
            override fun onFail(obj: BaseBean) {
                LoadTools.instance().hideMultistage()
                onResponceListener.onFail(obj)
            }

            override fun onSucc(obj: BaseBean) {
                //2.11、设置最大进店人数
                setStoreMaxNumber(mContext, map["storeId"] as String?
                        ?: "", "0", object : YNClient.OnResponceListener<BaseBean> {
                    override fun onSucc(obj: BaseBean) {
                        LoadTools.instance().hideMultistage()
                        onResponceListener.onSucc(obj)
                    }

                    override fun onFail(obj: BaseBean) {
                        LoadTools.instance().hideMultistage()
                        onResponceListener.onFail(obj)
                    }

                })
            }

        })
    }

    /**
     * @see  zxy 查询商店货架信息
     * @param   1)设置最大进店人数为0 2)屏蔽商店事件
     * @param storeId                店铺ID
     */
    fun queryStoreRackMsg(mContext: Context, storeId: String, onResponceDoubleListener: OnResponceDoubleListener<RacksBean, GatesBean>) {
        LoadTools.instance().showMultistage(mContext)
        //2.1、店铺坐标
        rackList(mContext, storeId, object : OnResponceListener<RackListBean> {
            override fun onFail(rackListBean: RackListBean) {
                LoadTools.instance().hideMultistage()
                onResponceDoubleListener.onFail(BaseBean(rackListBean.code,rackListBean.message))
            }

            override fun onSucc(rackListBean: RackListBean) {
                val toJson = Gson().toJson(rackListBean)
                val racksBean = RacksUtils().convert(toJson)
                //2.4、闸机坐标
                gateCoord(mContext, storeId, object : YNClient.OnResponceListener<GatesBean> {
                    override fun onSucc(gatesBean: GatesBean) {
                        LoadTools.instance().hideMultistage()
                        var originX = racksBean.originX
                        var originY = racksBean.originY
                        var maxX = racksBean.maxX
                        var maxY = racksBean.maxY
                        for (gate in gatesBean.gates) {
                            gate.regionA.x = (gate.regionA.x - originX) / maxX
                            gate.regionA.y = -(gate.regionA.y - originY) / maxY
                            gate.regionB.x = (gate.regionB.x - originX) / maxX
                            gate.regionB.y = -(gate.regionB.y - originY) / maxY
                            gate.regionC.x = (gate.regionC.x - originX) / maxX
                            gate.regionC.y = -(gate.regionC.y - originY) / maxY
                            gate.regionD.x = (gate.regionD.x - originX) / maxX
                            gate.regionD.y = -(gate.regionD.y - originY) / maxY
                        }
                        onResponceDoubleListener.onSucc(racksBean, gatesBean)
                    }

                    override fun onFail(obj: GatesBean) {
                        LoadTools.instance().hideMultistage()
                        onResponceDoubleListener.onFail(BaseBean(obj.code,obj.message))
                    }

                })
            }

        })
    }

    /**
     * @see  zxy 查询货架商品信息
     * @param   1)设置最大进店人数为0 2)屏蔽商店事件
     * @param storeId                店铺ID
     * @param rackId                 货架ID
     */
    fun queryRackGoodsMsg(mContext: Context, storeId: String, rackId: String, onResponceDoubleListener: OnResponceDoubleListener<RackInfoBean, ShelfBeanListBean>) {
        LoadTools.instance().showMultistage(mContext)
        //2.2、获取货架的高度
        rackHeight(mContext, storeId, rackId, object : YNClient.OnResponceListener<RackInfoResBean> {
            override fun onFail(obj: RackInfoResBean) {
                LoadTools.instance().hideMultistage()
                onResponceDoubleListener.onFail(BaseBean(obj.code,obj.message))
            }

            override fun onSucc(rackInfoResBean: RackInfoResBean) {
                //2.3、获取货架栏详情
                getRackDetail(mContext, storeId, rackId, object : YNClient.OnResponceListener<ShelfBeanListBean> {
                    override fun onSucc(shelfBeanListBean: ShelfBeanListBean) {
                        LoadTools.instance().hideMultistage()
                        onResponceDoubleListener.onSucc(rackInfoResBean.data, shelfBeanListBean)
                        //2.14、查询商品所在栏位-GoodsPosBean
                    }

                    override fun onFail(obj: ShelfBeanListBean) {
                        LoadTools.instance().hideMultistage()
                        onResponceDoubleListener.onFail(BaseBean(obj.code,obj.message))
                    }

                })
            }

        })
    }


}