package com.cloudpick.yunnasdk.entity;

import android.text.TextUtils;
import android.util.Log;

import java.io.Serializable;
import java.util.List;


public class PanelDataBean implements Serializable {

    /**
     * code : 0000
     * data : [{"goodsId":"6925303783297","storeId":"SHP001","goodsName":"奶茶小椰","groupId":"160203","groupName":"包子","price":"800.00"}]
     */

    private String code;
    private List<DataBean> data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable,Cloneable{
        /**
         * goodsId : 6925303783297
         * storeId : SHP001
         * goodsName : 奶茶小椰
         * groupId : 160203
         * groupName : 包子
         * price : 800.00
         */

        private String goodsId;
        private String storeId;
        private String goodsName;
        private String groupId;
        private String groupName;
        private String price;
        private int count;
        private boolean isEmpty;
        private boolean editAble;
        private String goodsPrice;

        public String getGoodsPrice() {
            if(TextUtils.isEmpty(goodsPrice)){
                return "0";
            }
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public boolean isEditAble() {
            return editAble;
        }

        public void setEditAble(boolean editAble) {
            this.editAble = editAble;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getPrice() {
            if(TextUtils.isEmpty(price)){
                return "0";
            }
            return price;
        }

        public void priceYuan2Fen() {
            float priceFloat = getPriceFloat();
            setPrice("" + (priceFloat / 100));
        }

        public float getPriceFloat() {
            try {
                return Float.parseFloat(price);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
            Log.e("tag", "" + count);
        }

        public boolean isEmpty() {
            return isEmpty;
        }

        public void setEmpty(boolean empty) {
            isEmpty = empty;
        }

        @Override
        public Object clone(){
            DataBean bean = null;
            try{
                bean = (DataBean) super.clone();
            }catch (CloneNotSupportedException e){

            }
            return bean;
        }

    }
}
