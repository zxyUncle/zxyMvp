package com.cloudpick.yunnasdk.entity;

import android.text.TextUtils;

public class NegativeStorageGoodsBean {
    // 负库存 列表 数据
    private String storageId;
    private String goodsId;
    private String goodsName;
    private int totalNum;

    // 负库存 商品 操作流水
    private String flowId;
    private String bizDate;
    private String num;
    private String sumPrice;
    private int type;// 1-入库 ，2-出库
    private String outBizId;
    private String outBizType;//
    /* "01","进货"
	"02","出货"
            "03","订单"
            "04","溢出耗损"
            "05","调拨"
            "06","调整"
            "07","外卖背包订单"
            "08","强平库存"
            "09", "溢出耗损（盘点）"
            "10", "销售订单"
            "11", "退款订单"
            "12", "关闭订单"*/
    private String gmtCreate;


    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSumPrice() {
        if(TextUtils.isEmpty(sumPrice)){
            return "0";
        }
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOutBizId() {
        return outBizId;
    }

    public void setOutBizId(String outBizId) {
        this.outBizId = outBizId;
    }

    public String getOutBizType() {
        return outBizType;
    }

    public void setOutBizType(String outBizType) {
        this.outBizType = outBizType;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
