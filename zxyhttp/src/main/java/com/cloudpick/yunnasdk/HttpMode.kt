package com.cloudpick.yunnasdk


import com.cloudpick.yunnasdk.entity.*
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

    /**
     * @see 1.7、EX：账号注册接口
     * @param userName          账户
     * @param password          密码
     * @param confirmPassword   确认密码
     * @param email             邮箱
     * @param vcCode            验证码
     * @param channel            渠道
     */
    @POST("cloudpick/rest/admin/api/v1/logon/pwd/user/register")
    fun register(@Body body: RequestBody): Observable<UserInfoBean>

    /**
     * @see 1.8、EX：修改密码
     * @param userId          账户ID
     * @param password          旧密码
     * @param newPassword       新密码
     * @param confirmPassword   确认密码
     * @param channel            渠道
     */
    @POST("cloudpick/rest/admin/api/v1/logon/pwd/user/update/password")
    fun updataPassword(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @return 弃用
     * @see 1.9、向邮箱发送验证码
     * @param email             邮箱
     * @param captchaValue      图形验证码
     * @param system            app默认传：mer
     * @param channel            渠道
     */
    @GET("cloudpick/rest/admin/api/v1/logon/sendemail")
    fun sendemail(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    //zxy 2、监控界面

    /**
     * @see 2.1、店铺坐标
     * @param storeId             店铺ID
     */
    @GET("sto/rest/admin/api/v1/store/{storeId}/rack/list")
    fun rackList(@Path("storeId") storeId: String): Observable<RackListBean>

    /**
     * @see 2.2、获取货架的高度
     * @param rackId             架子ID
     * @param storeId            店铺ID
     */
    @GET("sto/rest/admin/api/v1/rack/{rackId}/info")
    fun rackHeight(@Path("rackId") rackId: String, @Query("storeId") storeId: String): Observable<RackInfoResBean>

    /**
     * @see 2.3、获取货架栏详情
     * @param rackId             架子ID
     * @param storeId            店铺ID
     */
    @GET("sto/rest/admin/api/v1/rack/{rackId}/shelf/list")
    fun getRackDetail(@Path("rackId") rackId: String, @Query("storeId") storeId: String): Observable<ShelfBeanListBean>

    /**
     * @see 2.4、闸机坐标
     * @param storeId            店铺ID
     */
    @GET("sto/rest/admin/api/v1/store/{storeId}/gate")
    fun gateCoord(@Path("storeId") storeId: String): Observable<GatesBean>

    /**
     * @see 2.5、查询闸机心跳
     * @param storeId            店铺ID
     */
    @GET("sto/rest/admin/api/v1/hb/{storeId}/01/query")
    fun gateHeartbeat(@Path("storeId") storeId: String): Observable<GateHeartBean>

    /**
     * @see 2.6、打开或者关闭闸机
     * @param storeId            店铺ID
     * @param sourceId           sourceId
     * @param command            open/close 打开关闭闸机
     */
    @POST("sto/rest/admin/api/v1/hb/maintain/{storeId}/01/{sourceId}")
    fun openOrCloseGatet(@Path("storeId") storeId: String,@Path("sourceId") sourceId: String, @Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 2.7、查询商品
     * @param goodsId            商品ID
     */
    @GET("cloudpick/rest/api/v1/goods/query")
    fun queryGoods(@Query("goodsId") goodsId: String): Observable<GoodsInfoListBean>

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
    @GET("cloudpick/rest/operator/api/v1/goods/store/{storeId}/query")
    fun queryDimShop(@Path("storeId") storeId: String, @QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<GoodsInfoListDataBean>

    /**
     * @see 2.9、查询商品活动
     * @param storeId            店铺ID
     * @param goodsIds           goodsIds=123456&goodsIds=789  多个goodsId查询
     */
    @GET("cloudpick/rest/operator/api/v1/market/activity/store/{storeId}/query")
    fun queryShopActivity(@Path("storeId") storeId: String, @Query("goodsIds") goodsIds: String): Observable<ShopActivityBean>

    /**
     * @see 2.10、修改栏位商品
     * @param landID                栏位ID
     * @param defaultGoodsId        栏位默认商品
     * @param goodsList            {"goodsId":goodsId,"goodsNum":1]}需要修改为商品的数组
     */
    @POST("sto/rest/admin/api/v1/lane/{landID}")
    fun updataLandGoods(@Path("landID") landID: String, @Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 2.11、查询最大进店人数
     * @param storeId                店铺ID
     */
    @GET("sto/rest/admin/api/v1/store/{storeId}/limit")
    fun queyStoreMaxNumber(@Path("storeId") storeId: String): Observable<BaseBean>

    /**
     * @see 2.11、设置最大进店人数
     * @param storeId                店铺ID
     * @param limitCustomerNumber    设置进店人数
     */
    @POST("sto/rest/admin/api/v1/store/{storeId}/limit")
    fun setStoreMaxNumber(@Path("storeId") storeId: String, @Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 2.12、屏蔽商店事件
     * @param storeId                店铺ID
     * @param type                  默认传“store”字符串，"type":"store"
     * @param deviceId              传storeId
     * @param times                 时间戳
     */
    @POST("sto/rest/operator/api/v1/event/wt/ignore")
    fun shieldShopEvent(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 2.13、查询脏栏
     * @param storeId                店铺ID
     */
    @GET("sto/rest/admin/api/v1/lane/{storeID}/rack/misplace")
    fun queryMisplaceRack(@Path("storeID") storeID: String): Observable<LandBeanRequest>

    /**
     * @see 2.14、查询商品所在栏位
     * @param storeId                店铺ID
     * @param goodsId                商品ID
     */
    @GET("sto/rest/admin/api/v1/lane/store/{storeId}/defaultGoods/{goodsId}")
    fun queryShopRack(@Path("storeId") storeId: String, @Path("goodsId") goodsId: String): Observable<LaneInfoBean>

    /**
     * @see 2.15、重新识别栏位商品数量
     * @param storeId                店铺ID
     * @param rackId                 栏位ID
     */
    @GET("sto/rest/operator/api/v1/store/{storeID}/rack/{rackId}/goodsNum")
    fun recognitionRackShopNum(@Path("storeID") storeId: String, @Path("rackId") goodsId: String): Observable<BaseBean>

    /**
     * @see 2.16、罗列货架传感器配置
     * @param storeId                店铺ID
     * @param rackId                 栏位ID
     */
    @GET("sto/rest/admin/api/v1/rack/sensor/list")
    fun queryStoreSensorLoca(@Query("storeId") storeId: String, @Query("rackId") rackId: String): Observable<SensorListResultBean>

    /**
     * @see 2.17、获取货架传感器记录（三小时以内）
     * @param storeId                店铺ID
     * @param rackId                 栏位ID
     */
    @GET("sto/rest/admin/api/v1/rack/sensor/{rackId}/records")
    fun queryStoreSensorRecordList(@Path("rackId") rackId: String, @Query("storeId") storeId: String): Observable<SensorRecordBean>


    /**
     * @see 2.18、按时间查询单个传感器记录
     * @param storeId                店铺ID
     * @param sensorId               栏位ID
     * @param fromDate               yyyy-MM-dd HH:mm:ss
     * @param toDate                yyyy-MM-dd HH:mm:ss
     */
    @GET("sto/rest/admin/api/v1/rack/sensor/{sensorId}/list")
    fun queryTimeSensorRecord(@Query("storeId") storeId: String,
                              @Path("sensorId") sensorId: String,
                              @Query("fromDate") fromDate: String,
                              @Query("toDate") toDate: String
    ): Observable<SingleSensorRecordBean>

    /**
     * @see 2.19、补货：店铺补货货架种类
     * @param storeId                店铺ID
     */
    @GET("erp/rest/api/v1/replenish/rack/group/list")
    fun queryReplenishmentType(@Query("storeId") sensorId: String): Observable<ReplenishmentGroupRequest>

    /**
     * @see 2.20、补货：根据货架组ID查询货架列表
     * @param rackGroupId            货架组ID
     * @param storeId                店铺ID
     */
    @GET("erp/rest/api/v1/replenish/rack/{rackGroupId}")
    fun queryStoreList(@Path("rackGroupId") rackGroupId: String, @Query("storeId") sensorId: String): Observable<ReplenishmentGroupofRackIdsRequest>

    /**
     * @see 2.21、补货：根据货架ID列表查询补货商品信息列表接口
     * @param storeId                店铺ID
     * @param rackIds                rackIds=001&rackIds=002&rackIds=003
     */
    @GET("erp/rest/api/v1/replenish/bill")
    fun queryReplenishmentList(@Query("storeId") sensorId: String, @Query("rackIds") rackIds: String): Observable<ReplenishmentGoodsRequest>

    /**
     * @see 2.22、补货：查询栏位商品补货提醒值
     * @param goodsId                商品ID
     * @param storeId                商店ID
     * @param laneId                 栏位ID
     * @param goodsName              商品名称
     */
    @GET("erp/rest/api/v1/replenish/remind/goods/info")
    fun querySensorShopRemind(@Query("goodsId") goodsId: String,
                                   @Query("storeId") storeId: String,
                                   @Query("laneId") laneId: String,
                                   @Query("goodsName") goodsName: String): Observable<SensorShopRemindBean>

    /**
     * @see 2.23、补货：设置货架提醒数量
     * @param goodsId                商品ID
     * @param storeId                商店ID
     * @param laneId                 栏位ID
     * @param goodsName              商品名称
     * @param adviceNum              摆放数量（建议）
     * @param remindNum              缺货提醒数量
     */
    @POST("erp/rest/api/v1/replenish/remind/goods/update")
    fun setRackRemindNum(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 2.24、补货：根据店铺ID查询店铺是否有补货提醒的角标显示
     * @param storeId                商店ID
     */
    @GET("erp/rest/api/v1/replenish/remind/{storeId}/corner")
    fun quneryMarkRemind(@Path("storeId") sensorId: String): Observable<BaseBean>

    //zxy 3、管理

    /**
     * @see 3.1.1获取顾客未结算购物车
     * @param qrCode            用户进门二维码
     * @param userId            用户userId
     * @param storeId           storeId
     * @param mobile            用户手机号
     * @param flag                "flag":"normal" 默认写normal
     */
    @GET("cloudpick/rest/operator/api/v1/cart/goods")
    fun unPayCard(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<CartBean>

    /**
     * @see 3.1.2、查询库存商品
     * @param storageId         storageId
     * @param goodsId           goodsId
     * @param pageSize          pageSize默认10
     * @param pageNum           pageNum默认从1开始
     */
    @GET("cloudpick/rest/operator/api/v1/ware/{storageId}/storage")
    fun queryStorageShop(@Path("storageId") storageId: String,
                         @QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<GoodsStockBean>

    /**
     * @see 3.1.3、生成订单
     * @param userId          账户ID
     * @param cartId          购物车id
     * @param flag            "flag":"normal" 默认写normal
     * @param storeId           storeId
     * @param goodsInfo            数组
     *--------------------------------------------
     * @param 数组goodsInfo
     * @param goodsId            goodsId
     * @param goodsPhoto        goodsPhoto
     * @param goodsPrice        商品价格
     * @param goodsNum            数量
     * @param goodsTax            税费
     *
     */
    @POST("cloudpick/rest/operator/api/v1/cart/pay")
    fun createOrder(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 3.1.4、商品面板分类
     * @param storeID
     */
    @GET("cloudpick/rest/operator/api/v1/goods/{storeID}/panelGoodsGroup")
    fun panelGoodsGroup(@Path("storeID") storeID: String): Observable<PanelGroupRequest>

    /**
     * @see 3.1.5、查询面板商品
     * @param storeId           storeId
     * @param groupId           组id
     * @param pageSize          pageSize默认10
     * @param pageNum           pageNum默认从1开始
     */
    @GET("cloudpick/rest/operator/api/v1/goods/{storeID}/panelGoods")
    fun panelGoods(@Path("storeID") storeID: String,
                   @Query("storeId") storeId: String,
                   @Query("groupId") groupId: MutableList<String>,
                   @Query("pageSize") pageSize: String,
                   @Query("pageNum") pageNum: String): Observable<PanelDataBean>

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
    @GET("cloudpick/rest/operator/api/v1/mer/order/{storeID}/list")
    fun orderList(@Path("storeID") storeID: String, @QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<OrderListBean>

    /**
     * @see 3.2.2、订单详情
     * @param orderId 订单ID
     */
    @GET("cloudpick/rest/operator/api/v1/{orderId}/info")
    fun orderDetail(@Path("orderId") orderId: String): Observable<OrderDetailBean>

    /**
     * @see 3.2.3、订单支付流水
     * @param orderId 订单ID
     */
    @GET("cloudpick/rest/api/v1/order/{orderId}/transInfo")
    fun orderPayWater(@Path("orderId") orderId: String): Observable<OrderTransInfoBean>


    /**
     * @see 3.2.4、订单退款
     * @param userId 退款顾客userId
     * @param orderId 订单ID
     * @param refundDesc 退款原因描述
     * @param storeId 商店id
     * @param superfluousGoods 退款商品数组
     * ----------------------------------
     * @param goodsId goodsId
     * @param goodsPrice 商品价格
     * @param goodsNum 退款商品数量
     */
    @POST("cloudpick/rest/operator/api/v1/refund")
    fun orderRefund(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 3.2.5、修改商品价格
     * @param storeId 店铺id
     * @param price 修改到商品的价格
     * @param goodsId 商品id
     */
    @POST("cloudpick/rest/operator/api/v1/goods/price/change")
    fun updataShopPrice(@Body body: RequestBody): Observable<BaseBean>


    /**
     * @see 3.2.6、查询商品的库存流水
     * @param goodsId 店铺id
     * @param storageId 仓库ID
     * @param startTime 时间戳：毫秒
     * @param endTime 时间戳：毫秒
     */
    @GET("cloudpick/rest/operator/api/v1/ware/{storageId}/flow")
    fun storageWater(@Path("storageId") storageId: String, @QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<GoodsBatchDetailBean>

    /**
     * @see 3.2.7、查询商品负库存流水
     * @param storageId 仓库ID
     * @param goodsId 店铺id
     */
    @GET("cloudpick/rest/operator/api/v1/ware/storage/{storageId}/{goodsId}/negative")
    fun queyMinusStorageWater(@Path("storageId") storageId: String, @Path("goodsId") goodsId: String): Observable<NegativeStorageGoodsRequest>


    //zxy 3.3、查询顾客信息

    /**
     * @see 3.3.1、查询顾客信息
     * @param channel 渠道
     * @param storeId 商品id
     * @param qrCode 非必传：用户QRCode
     * @param mobile 非必传：用户手机号
     * @param userId 非必传：用户userId
     */
    @GET("cloudpick/rest/operator/api/v1/user/query")
    fun queryClient(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<UserInfoDataBean>

    /**
     * @see 3.3.2、查询用户信息
     * @param userId
     */
    @GET("cloudpick/rest/api/v1/user/{userId}/get")
    fun queryUser(@Path("userId") userId: String): Observable<UserInfoBean>

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
    @POST("cloudpick/rest/api/v1/user/{userId}/userInfo")
    fun updataUser(@Path("userId") userId: String, @Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 3.3.4、查询顾客优惠券
     * @param userId
     * @param channel   渠道
     */
    @GET("cloudpick/rest/operator/api/v1/coupon/{userId}")
    fun queryUserCoupon(@Path("userId") userId: String, @Query("channel") channel: String): Observable<UserCouponsBean>

    /**
     * @see 3.3.5、优惠券类型查询
     * @param channel   渠道
     * @param mode      默认传：APPLY
     * @param couponType SUPPORT_CASH
     */
    @GET("cloudpick/rest/api/v1/coupon/template")
    fun queryCouponType(@Query("channel") channel: String,
                        @Query("mode") mode: String,
                        @Query("couponType") couponType: String): Observable<CouponListBean>

    /**
     * @see 3.3.6、发送优惠券
     * @param channel   渠道
     * @param mode      默认传：APPLY
     * @param couponType SUPPORT_CASH
     * -----------------------------------
     * @param count 优惠券数量
     * @param couponTemplateId 优惠券类型
     */
    @POST("cloudpick/rest/operator/api/v1/coupon/send")
    fun sendCoupon(@Body body: RequestBody): Observable<BaseBean>


    /**
     * @see 3.3.7、查询卡券类型
     * @param channel   渠道
     * @param mode      默认传：APPLY
     */
    @GET("cloudpick/rest/operator/api/v1/card/template")
    fun queryCouponCardType(@Query("channel") channel: String,
                            @Query("mode") mode: String): Observable<CardReelTypeBean>

    /**
     * @see 3.3.8、发送卡券
     * @param templateId   卡券id
     * @param userId      userId
     */
    @POST("cloudpick/rest/operator/api/v1/card/send")
    fun sendCouponCard(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 3.3.9、查询用户卡券
     * @param userId   userId
     * @param channel   渠道
     */
    @GET("cloudpick/rest/api/v1/card/{userId}")
    fun queryUserCouponCard(@Path("userId") userId: String,
                            @Query("channel") channel: String): Observable<UserCardsBean>

    //zxy 3.4、外卖

    /**
     * @see 3.4.1、添加外卖
     * @param userId        用户userId
     * @param takeoutId     外卖id
     * @param takeoutType   外卖类型美团，饿了么
     * @param storeId       商店id
     * @param remark        备注
     * @param goodsList     数组
     * --------------------------------------------
     * @param goodsId      goodsId
     * @param goodsNum     数量
     */
    @POST("cloudpick/rest/admin/api/v1/scs/takeout/package/genorder")
    fun addTakeOut(@Body body: RequestBody): Observable<BaseBean>

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
    @GET("cloudpick/rest/admin/api/v1/scs/takeout/package")
    fun queryTakeOut(@QueryMap map: Map<String,@JvmSuppressWildcards Any>, @Query("packageId") packageId: MutableList<String>): Observable<BaseBean>

    /**
     * @see 3.4.3、更新外卖订单
     * @param packageId        外卖单id
     * @param type           remove 移除商品add添加商品
     * @param remark        备注
     * @param storeId       商店id
     * @param packageGoods
     * --------------------------------------------
     * @param goodsId      goodsId
     * @param goodsNum     数量
     */
    @POST("cloudpick/rest/admin/api/v1/scs/takeout/package/genorder")
    fun updataTakeOut(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 3.4.4、一键补满背包
     * @param storeId       商店id
     * @param packageId     背包id
     */
    @POST("cloudpick/rest/admin/api/v1/scs/takeout/package/{storeId}/{packageId}/fill")
    fun oneReplenishTakeOut(@Path("storeId") storeId: String, @Path("packageId") packageId: String): Observable<BaseBean>


    //zxy 4、报表

    /**
     * @see 4.1、订单报表
     * @param storeId       商店id
     * @param beginDate     背包id
     * @param endDate     背包id
     */
    @GET("cloudpick/rest/acc/api/v1/market/report/order")
    fun orderForm(@Query("storeId") storeId: String,
                  @Query("beginDate") beginDate: String,
                  @Query("endDate") endDate: String): Observable<StatementLIstBean>

    /**
     * @see 4.2、查询当日订单详情
     * @param orderType     订单类型，默认传：00
     * @param beginDate     yyyy-MM-dd HH:mm:ss
     * @param endDate       yyyy-MM-dd HH:mm:ss
     */
    @GET("cloudpick/rest/operator/api/v1/order/{storeID}/list")
    fun queryOrderDetail(@Path("orderType") storeId: String,
                         @Query("beginDate") beginDate: String,
                         @Query("endDate") endDate: String): Observable<BaseBean>


    //zxy 5、进销存

    //zxy 5.1、仓库进货&退货

    /**
     * @see 5.1.1、供应商列表
     * @param channel       渠道
     * @param valid         默认传Y:valid=Y
     */
    @GET("cloudpick/rest/operator/api/v1/ware/supplier/list")
    fun providerList(@Query("channel") channel: String,
                     @Query("valid") valid: String): Observable<ProviderListBean>

    /**
     * @see 5.1.2、查询我能看到的仓库列表
     */
    @GET("cloudpick/rest/operator/api/v1/ware/warehouse/list")
    fun queryStorageList(): Observable<StorageListBean>

    /**
     * @see 5.1.3、查询所有的仓库列表
     */
    @GET("cloudpick/rest/operator/api/v1/ware/warehouse/all")
    fun queryAllStorageList(): Observable<StorageListBean>

    /**
     * @see 5.1.4、查询仓库录入和仓库退货列表
     * @param dateStart        yyyy-MM-dd HH:mm:ss
     * @param dateEnd          yyyy-MM-dd HH:mm:ss
     * @param flag             进货：storage-退货：putout
     */
    @GET("cloudpick/rest/operator/api/v1/ware/{storageId}/batch/list")
    fun queryStorageGoodsList(@Query("dateStart") dateStart: String,
                              @Query("dateEnd") dateEnd: String,
                              @Query("flag") flag: String
    ): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/ware/{storageId}/batch/{batchNo}/clone")
    fun cloneStorage(@Path("storageId") dateStart: String,
                     @Path("batchNo") dateEnd: String,
                     @Body body: RequestBody
    ): Observable<BaseBean>

    /**
     * @see 5.1.6、获取批次号
     * @param type        0=出库、1=入库、4=录入损耗批次号、5=盘点
     */
    @GET("cloudpick/rest/operator/api/v1/ware/batch/gen")
    fun getBatchNum(@Query("type") type: String
    ): Observable<BaseBean>

    /**
     * @see 5.1.7、获取批次详情
     * @param batchNo        批次号
     */
    @GET("cloudpick/rest/operator/api/v1/ware/batch/{batchNo}")
    fun getBatchNumDetail(@Path("batchNo") batchNo: String
    ): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/ware/{storageId}/signin")
    fun inStorage(@Body body: RequestBody): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/ware/{storageId}/signout")
    fun outStorage(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.1.10、出入库批次确认
     * @param batchNo       批次号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/batch/{batchNo}/confirm")
    fun storageBatchNumConfig(@Path("batchNo") batchNo: String): Observable<BaseBean>

    /**
     * @see 5.1.11、删除批次商品
     * @param batchNo       批次号
     * @param recordId      记录id
     * @param storageId     仓库
     * @param goodsId       商品的id
     */
    @POST("cloudpick/rest/operator/api/v1/ware/{storageId}/batch/{batchNo}/goods/{recordId}/delete")
    fun delBatchNumGood(@Path("storageId") storageId: String,
                        @Path("batchNo") batchNo: String,
                        @Path("recordId") recordId: String,
                        @Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.1.12、查询供应商商品列表
     * @param goodsId       商品id
     * @param supplierId    供应商id
     * @param channel       channel
     */
    @GET("erp/rest/operator/api/v1/ware/supplier/goods/{goodsId}")
    fun queryProGoodList(@Path("goodsId") goodsId: String,
                         @Query("supplierId") supplierId: String,
                         @Query("channel") channel: String): Observable<GoodsInfoListDataBean>

    /**
     * @see 5.1.13、根据供应商查询是否税率使用情况
     * @param channel       channel
     * @param supplierId    供应商id
     * @param vaild         默认：Y
     */
    @GET("erp/rest/operator/api/v1/ware/supplier")
    fun queryTaxRate(@Query("channel") channel: String,
                     @Query("supplierId") supplierId: String,
                     @Query("vaild") vaild: String): Observable<BaseBean>

    //zxy 5.2、库存

    /**
     * @see 5.2.1、查询商品库存流水
     * @param storageId     仓库id
     * @param pageSize      默认：10
     * @param pageNum       默认：1
     * @param goodsId       非必传：商品的id
     */
    @GET("cloudpick/rest/operator/api/v1/ware/{storageId}/storage")
    fun queryGoodStorageWater(@Path("storageId") storageId: String,
                              @Query("pageSize") pageSize: String,
                              @Query("pageNum") pageNum: String,
                              @Query("goodsId") goodsId: String): Observable<BaseBean>


    /**
     * @see 5.2.2、查询负库存商品接口
     * @param storageId     仓库id
     * @param pageSize      默认：10
     * @param pageNum       默认：1
     * @param goodsId       非必传：商品的id
     */
    @GET("cloudpick/rest/operator/api/v1/ware/storage/{storageId}/goods/negative")
    fun queryStorageGood(@Path("storageId") storageId: String,
                         @Query("pageSize") pageSize: String,
                         @Query("pageNum") pageNum: String,
                         @Query("goodsId") goodsId: String): Observable<BaseBean>

    /**
     * @see 5.2.3、强制调整商品库存
     * @param storageId      库ID
     * @param sumPrice      单价
     * @param num           数量
     * @param goodsId       非必传：商品的id
     */
    @POST("cloudpick/rest/operator/api/v1/ware/storage/{storageId}/adjust")
    fun updateStorageNum(@Path("storageId") storageId: String,
                         @Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.2.4、强平负库存
     * @param storageId      库ID
     * @param goodsId       商品id
     */
    @POST("cloudpick/rest/operator/api/v1/ware/storage/negative/right")
    fun storageNegative(@Body body: RequestBody): Observable<BaseBean>


    //zxy 5.3、盘点


    /**
     * @see 5.3.1、盘点列表
     * @param storageId      库ID
     * @param pageSize       默认：10
     * @param pageNum        默认：1
     */
    @GET("cloudpick/rest/operator/api/v1/ware/inventory/batchno/list")
    fun checkList(@Query("storageId") storageId: String,
                  @Query("pageSize") pageSize: String,
                  @Query("pageNum") pageNum: String): Observable<BaseBean>

    /**
     * @see 5.3.2、查询盘点结果
     * @param batchNo      批次号
     */
    @GET("cloudpick/rest/operator/api/v1/ware/inventory/summary")
    fun queryCheckReslt(@Query("batchNo") batchNo: String): Observable<BaseBean>

    /**
     * @see 5.3.3、废除盘点号
     * @param batchNo      批次号
     * @param storageId    仓库号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/discard")
    fun delCheckCall(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.4、结束盘点
     * @param batchNo      批次号
     * @param storageId    仓库号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/complete")
    fun overCheck(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.5、获取盘点批次号
     * @param storageId    仓库号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/batchno/get")
    fun getCheckCall(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.6、执行并且校验负库存
     * @param storageId    仓库号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/storage/negative/{storageId}/rac")
        fun execuStorage(@Body body: RequestBody): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/goods/{flowId}/add")
    fun addInventoryGood(@Path("flowId") flowId:String,@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.8、更新盘点商品
     * @param flowId        流水id
     * @param num           数量
     * @param remark        非必填：备注
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/goods/{flowId}/update")
    fun updataInventoryGood(@Path("flowId") flowId:String,@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.9、查询录入的盘点数据
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param operator      操作人
     * @param pageNum       默认：1
     * @param pageSize      默认：10
     */
    @GET("cloudpick/rest/operator/api/v1/ware/inventory/goods/list")
    fun selectInventoryList(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    /**
     * @see 5.3.10、用户确认盘点数据
     * @param batchNo       批次号
     * @param storageId     仓库号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/goods/confirm")
    fun usreConfigInventory(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.11、查询指定批次的盘点快照
     * @param batchNo       批次号
     * @param storageId     非必填：仓库号
     * @param goodsId       非必填：商品id
     * @param goodsName     非必填：商品名称
     */
    @GET("cloudpick/rest/operator/api/v1/ware/inventory/snapshot/search")
    fun queryInventorySnapshot(@Query("batchNo") batchNo: String,
                               @Query("storageId") storageId: String,
                               @Query("goodsId") goodsId: String,
                               @Query("goodsName") goodsName: String): Observable<InventorySnapshotBean>

    /**
     * @see 5.3.12、插入盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId     商品id
     * @param goodsName     商品名称
     * @param inventoryNum     盘点数量
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/snapshot/create")
    fun insertInventorySnapshot(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.13、删除盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param gmtModified   yyyy-MM-dd HH:mm:ss
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/snapshot/delete")
    fun delInventorySnapshot(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.14、修改盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       商品id
     * @param gmtModified   yyyy-MM-dd HH:mm:ss
     * @param inventoryNum  盘点数量
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/snapshot/modify")
    fun updataInventorySnapshot(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.15、复核指定批次的盘点快照（修改库存）
     * @param batchNo       批次号
     * @param storageId     仓库号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/snapshot/review")
    fun reviewInventorySnapshot(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.3.16、刷新盘点商品的快照
     * @param batchNo       批次号
     * @param storageId     仓库号
     * @param goodsId       非必填：商品id
     */
    @POST("cloudpick/rest/operator/api/v1/ware/inventory/snapshot/refresh")
    fun refreshInventorySnapshot(@Body body: RequestBody): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/ware/spillLossFlow/add")
    fun addLoss(@Body body: RequestBody): Observable<BaseBean>

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
    @GET("cloudpick/rest/operator/api/v1/ware/spillLossFlow/query/list")
    fun queryLoss(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

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
    @GET("cloudpick/rest/operator/api/v1/ware/storageTransfer/query")
    fun queryStorageTransfer(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    /**
     * @see 5.5.2、调拨成本价格查询
     * @param fromStorageId     调出商品仓库
     * @param toStorageId       调入商品仓库
     * @param goodsId           非必填：商品id
     */
    @GET("cloudpick/rest/operator/api/v1/ware/storageTransfer/cost/query")
    fun queryCostStorageTransfer(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/ware/storageTransfer/cost/add")
    fun addCostStorageTransfer(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.5.4、调拨确认
     * @param batchNo           批次号
     */
    @POST("cloudpick/rest/operator/api/v1/ware/storageTransfer/confirm/{batchNo}")
    fun confirmStorageTransfer(@Body body: RequestBody): Observable<BaseBean>

    //zxy 5.6、采购系统


    /**
     * @see 5.6.1、查询面板商品分组
     */
    @GET("cloudpick/rest/operator/api/v1/ware/panel/goods/group")
    fun queryPanelGoodGroup(): Observable<PanelGoodGroupBean>

    /**
     * @see 5.6.2、查询面板候选商品
     * @param   groupId     分组id
     * @param   pageNum     非必填：默认：1
     * @param   pageSize    非必填：默认：10
     * @param   status      默认传1
     */
    @GET("cloudpick/rest/operator/api/v1/ware/panel/goods")
    fun queryPanelGood(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/ware/panel/goods")
    fun addProcurement(@Body body: RequestBody): Observable<BaseBean>

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
    @POST("cloudpick/rest/operator/api/v1/pur/apply/detail/{storageId}/{applyId}")
    fun updataProcurement(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.6.5、提交采购/退货申请
     * @param   storageId       库ID
     * @param   applyId         采购：0,退货：1
     * @param   operator        操作人
     */
    @POST("cloudpick/rest/operator/api/v1/pur/apply/{storageId}/{applyId}/submit")
    fun subProcurement(@Path("storageId") storageId:String,
                             @Path("applyId") applyId:String,
                             @Body body: RequestBody): Observable<BaseBean>

    /**
     * @see 5.6.6、查询采购/退货申请
     * @param   applyType       采购：0,退货：1
     * @param   storageId       仓库id
     * @param   pageNum         非必传：默认：1
     * @param   pageSize        非必传：默认：10
     * @param   beginDate       非必传：时间戳：毫秒
     * @param   endDate         非必传：时间戳：毫秒
     */
    @GET("cloudpick/rest/operator/api/v1/pur/apply")
    fun queryProcurement(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    /**
     * @see 5.6.7、查询采购/退货申请单详情
     * @param   storageId       仓库id
     * @param   applyId         采购：0,退货：1
     */
    @GET("cloudpick/rest/operator/api/v1/pur/apply/detail/{storageId}/{applyId}")
    fun queryProcurementDetail(@Path("storageId") storageId:String,@Path("applyId") applyId:String): Observable<BaseBean>

    /**
     * @see 5.6.8、查询配送单列表
     * @param   storageId       仓库id
     * @param   pageNum         非必传：默认：1
     * @param   pageSize        非必传：默认：10
     * @param   startDate       非必传：时间戳：毫秒
     * @param   endDate         非必传：时间戳：毫秒
     */
    @GET("cloudpick/rest/operator/api/v1/pur/delivery")
    fun queryDistributionList(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    /**
     * @see 5.6.9、查询配送单详情列表
     * @param   deliveryId       配送单ID
     */
    @GET("cloudpick/rest/operator/api/v1/pur/delivery/{deliveryId}/detail")
    fun queryDistributionDeatil(@Path("deliveryId") deliveryId:String): Observable<BaseBean>

    /**
     * @see 5.6.10、查询库存变动信息汇总变动详情
     * @param   storageId       库Id
     * @param   goodsId         商品id
     * @param   startTime       非必传：时间戳：毫秒
     * @param   entTime         非必传：时间戳：毫秒
     * @param   pageSize        非必传：默认：10
     * @param   pageNum         非必传：默认：1
     */
    @GET("cloudpick/rest/operator/api/v1/ware/daily/{storageId}/query/detail")
    fun queryStorageChangeDetail(@Path("storageId") storageId:String,
                                 @QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    //zxy 6、我的

    /**
     * @see  6.1、获取我能看到的商店列表
     */
    @GET("sto/rest/admin/api/v1/store/list")
    fun getMyShopList(): Observable<BaseBean>

    /**
     * @see  6.2、查询版本号
     * @param   bizType         (00用户，01商户)',
     * @param   osType          APP终端类型：(android，ios)',
     * @param   channel         渠道
     * @param   version        版本号
     */
    @GET("cloudpick/rest/api/v1/cms/app/version")
    fun queryVersion(@QueryMap map: Map<String,@JvmSuppressWildcards Any>): Observable<BaseBean>

    /**
     * @see  6.3、拉黑操作
     * @param   userId          用户userid
     * @param   desc            描述
     * @param   day             拉黑时间
     */
    @POST("cloudpick/rest/operator/api/v1/blacklist/insert")
    fun blacklist(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see  6.4、二维码
     * @param   userId          用户userid
     * @param   token           用户token
     */
    @POST("cloudpick/rest/admin/api/v1/logon/qrcode")
    fun qrCode(@Body body: RequestBody): Observable<QrCodeBean>

    /**
     * @see  6.5、用户权限集合
     * @param   type            ui
     * @param   owners           mgr，mer，jxc,acc
     */
    @GET("cloudpick/rest/admin/api/v1/logon/query/userpmt?owners=mgr&owners=mer&owners=jxc&owners=acc&type=ui")
    fun userJurisdiction(): Observable<BaseBean>

    /**
     * @see  6.6、意见反馈
     * @param   userId            用户userid
     * @param   feedbackType      问题：problem,意见：advice
     * @param   desc              描述
     */
    @POST("cloudpick/rest/api/v1/feedback/insert")
    fun feedback(@Body body: RequestBody): Observable<BaseBean>

    /**
     * @see  6.7、向后台注册极光推送
     * @param   userId              用户userid
     * @param   receiverId          极光的registrationID
     * @param   type                JPUSH
     * @param   appkey              极光appkey
     */
    @POST("cloudpick/rest/api/v1/logon/sdkInfo")
    fun sdkInfo(@Body body: RequestBody): Observable<BaseBean>


}