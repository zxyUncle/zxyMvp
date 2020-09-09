package com.cloudpick.yunnasdk.entity;

public class ReplenishmentGoodsBean {

    private String goodsId;
    private String goodsName;
    private int surplusNum;//剩余数量
    private int adviceNum;// 建议数量

    private int isReplenishment = 0;//是否缺货
    private int isIgnore = 0;//是否忽略；

    private boolean isShowName= true;

    public boolean isShowName() {
        return isShowName;
    }

    public void setShowName(boolean showName) {
        isShowName = showName;
    }

    public ReplenishmentGoodsBean() {

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

    public int getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(int surplusNum) {
        this.surplusNum = surplusNum;
    }

    public int getAdviceNum() {
        return adviceNum;
    }

    public void setAdviceNum(int adviceNum) {
        this.adviceNum = adviceNum;
    }

    public int getIsReplenishment() {
        return isReplenishment;
    }

    public void setIsReplenishment(int isReplenishment) {
        this.isReplenishment = isReplenishment;
    }

    public int getIsIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(int isIgnore) {
        this.isIgnore = isIgnore;
    }


}
