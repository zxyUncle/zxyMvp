package com.cloudpick.yunnasdk.tools.rackview;

import com.cloudpick.yunnasdk.R;

import java.util.List;


public class Authority {
    //修改商品  更新 修改商品价格
    public static final String EditGoods = "ui.mgr.action:editGoods";
    //发送优惠券
    public static final String SendCoupon = "ui.mgr.action:sendCoupon";
    //订单退款
    public static final String OrderRefund = "ui.mgr.action:orderRefund";
    //拉黑用户
    public static final String BlackInsert = "ui.mgr.action:blackInsert";
    //手动收银
    public static final String Manualmoney = "ui.mer.menu:manualmoney";

    //订单列表
    public static final String OrderList = "ui.mer.menu:orderList";
    public static final String GoodsList = "ui.mer.menu:goodsList";//	管理-商品列表
    public static final String CustomerInfo = "ui.mer.menu:customerInfo";//管理-顾客信息
    public static final String Statistics = "ui.mer.menu:statistics";//	报表

    public static final String Package = "ui.mer.menu:package";//		管理-背包系统

    public static final String queryAll = "ui.mer.menu:queryAll";//		商户-菜单：综合查询
    public static final String Storage = "ui.mer.menu:goodStorage";//		商户-菜单：仓库进货
    public static final String Putback = "ui.mer.menu:goodPutback";//		商户-菜单：仓库退货
    public static final String stockSearch = "ui.mer.menu:stockSearch";//		商户-菜单：库存查询
    public static final String lossMana = "ui.mer.menu:lossMana";//		商户-菜单：损耗管理
    public static final String goodCheck = "ui.mer.menu:goodCheck";//		商户-菜单：库存盘点
    public static final String Purchase = "ui.mer.menu:purchase";//	采购系统
    public static final String Allocation = "ui.mer.menu:stockTransfer";//	库存调拨
    public static final String Replenishment = "ui.mer.action:editRplItem";//	缺货提醒




    public static final int HAS_NO_AUTHORITY = R.string.has_no_authority;

    public static boolean check(String author) {
        if (author == null)
            return false;
        List<String> authorities = SPUtil.getObject("eco_authority");
        if (authorities == null || authorities.size() == 0)
            return true;
        for (String authority : authorities) {
            if (authority.equals(author)) {
                return true;
            }
        }
        return false;
    }
}
