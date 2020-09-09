package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class GoodsStockBean {

    /**
     * code : 0000
     * data : [{"storageId":"YNSHP001","goodsId":"3080216050027","goodsName":"格林堡啤酒胭脂","num":12,"check":"N","sumPrice":"180.00"},{"storageId":"YNSHP001","goodsId":"6901672500017","goodsName":"1164白啤","num":6,"check":"N","sumPrice":"82.80"},{"storageId":"YNSHP001","goodsId":"6901672500123","goodsName":"1664玫瑰啤","num":6,"check":"N","sumPrice":"82.80"},{"storageId":"YNSHP001","goodsId":"4066600111979","goodsName":"保拉纳小麦啤酒（瓶）500ml","num":5,"check":"N","sumPrice":"94.00"},{"storageId":"YNSHP001","goodsId":"4066600120322","goodsName":"保拉纳小麦啤酒（瓶）500ml","num":4,"check":"N","sumPrice":"75.20"},{"storageId":"YNSHP001","goodsId":"4066600601937","goodsName":"保拉纳小麦啤酒500ml","num":12,"check":"N","sumPrice":"201.60"},{"storageId":"YNSHP001","goodsId":"4066600601111","goodsName":"保拉纳大麦啤酒500ml","num":12,"check":"N","sumPrice":"201.60"},{"storageId":"YNSHP001","goodsId":"4066600497615","goodsName":"保拉纳黑小麦啤酒500ml","num":11,"check":"N","sumPrice":"184.80"},{"storageId":"YNSHP001","goodsId":"6938514100715","goodsName":"江小白小曲白酒","num":6,"check":"N","sumPrice":"100.80"},{"storageId":"YNSHP001","goodsId":"6925303722074","goodsName":"统一slim鲜橙","num":10,"check":"N","sumPrice":"15.00"}]
     * count : 33
     * success : true
     */

    private String code;
    private int count;
    private boolean success;
    private List<DataBean> data;
    private String message;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * storageId : YNSHP001
         * goodsId : 3080216050027
         * goodsName : 格林堡啤酒胭脂
         * num : 12
         * check : N
         * sumPrice : 180.00
         */

        private String storageId;
        private String goodsId;
        private String goodsName;
        private int num;
        private String check;
        private String sumPrice;
        private String goodsPhoto;

        private String totalNum;//总计负库存数量

        private String avgOut;//出库价格(成本)
        private String avgIn;//入库价格(成本)

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getCheck() {
            return check;
        }

        public void setCheck(String check) {
            this.check = check;
        }

        public String getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(String sumPrice) {
            this.sumPrice = sumPrice;
        }

        public String getGoodsPhoto() {
            return goodsPhoto;
        }

        public void setGoodsPhoto(String goodsPhoto) {
            this.goodsPhoto = goodsPhoto;
        }

        public String getAvgOut() {
            return avgOut;
        }

        public void setAvgOut(String avgOut) {
            this.avgOut = avgOut;
        }

        public String getAvgIn() {
            return avgIn;
        }

        public void setAvgIn(String avgIn) {
            this.avgIn = avgIn;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
