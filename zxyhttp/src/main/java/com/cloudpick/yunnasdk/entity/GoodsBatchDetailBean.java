package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class GoodsBatchDetailBean {

    /**
     * code : 0000
     * data : [{"flowId":"OrderSHP001201805306902890235118","outBizType":"订单","storageId":"YNSHP001","bizDate":"2018-05-29 20:10:00","goodsId":"6902890235118","goodsName":"双汇泡面搭档240g","num":13,"beforeNum":0,"afterNum":-13,"type":"2","area":"1","posit":"1"}]
     * success : true
     */

    private String code;
    private boolean success;
    private List<DataBean> data;
    private String message;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * flowId : OrderSHP001201805306902890235118
         * outBizType : 订单
         * storageId : YNSHP001
         * bizDate : 2018-05-29 20:10:00
         * goodsId : 6902890235118
         * goodsName : 双汇泡面搭档240g
         * num : 13
         * beforeNum : 0
         * afterNum : -13
         * type : 2
         * area : 1
         * posit : 1
         */

        private String flowId;
        private String outBizType;
        private String storageId;
        private String bizDate;
        private String goodsId;
        private String goodsName;
        private int num;
        private int beforeNum;
        private int afterNum;
        private String type;
        private String area;
        private String posit;

        public String getFlowId() {
            return flowId;
        }

        public void setFlowId(String flowId) {
            this.flowId = flowId;
        }

        public String getOutBizType() {
            return outBizType;
        }

        public void setOutBizType(String outBizType) {
            this.outBizType = outBizType;
        }

        public String getStorageId() {
            return storageId;
        }

        public void setStorageId(String storageId) {
            this.storageId = storageId;
        }

        public String getBizDate() {
            return bizDate;
        }

        public void setBizDate(String bizDate) {
            this.bizDate = bizDate;
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

        public int getBeforeNum() {
            return beforeNum;
        }

        public void setBeforeNum(int beforeNum) {
            this.beforeNum = beforeNum;
        }

        public int getAfterNum() {
            return afterNum;
        }

        public void setAfterNum(int afterNum) {
            this.afterNum = afterNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPosit() {
            return posit;
        }

        public void setPosit(String posit) {
            this.posit = posit;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "flowId='" + flowId + '\'' +
                    ", outBizType='" + outBizType + '\'' +
                    ", storageId='" + storageId + '\'' +
                    ", bizDate='" + bizDate + '\'' +
                    ", goodsId='" + goodsId + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", num=" + num +
                    ", beforeNum=" + beforeNum +
                    ", afterNum=" + afterNum +
                    ", type='" + type + '\'' +
                    ", area='" + area + '\'' +
                    ", posit='" + posit + '\'' +
                    '}';
        }
    }
}
