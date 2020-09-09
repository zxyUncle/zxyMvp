package com.cloudpick.yunnasdk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class ShelfBean {

    /**
     * shelfId : SHELF171016000051
     * smpMac : b8:27:eb:75:f8:1a
     * shelfType : PIANOV1_6
     * laneAmount : 6
     * shelfWidth : 890
     * shelfDepth : 460
     * status : using
     * storeId :
     * rackId : 1001
     * startHeight : 800
     * height : 341
     * laneModelList : [{"laneId":"SHELF17101600005100","shelfId":"SHELF171016000051","rackId":"1001","storeId":"SHP001","type":"01","k":"0.002339","b":"-16973.934054","width":148,"height":341,"depth":460,"defaultGoodsId":"613008715267","status":0,"goodsList":[],"points":[{"x":-820,"y":470,"z":800},{"x":-820,"y":621,"z":800},{"x":-820,"y":621,"z":1141},{"x":-820,"y":470,"z":1141}]},{"laneId":"SHELF17101600005101","shelfId":"SHELF171016000051","rackId":"1001","storeId":"SHP001","type":"01","k":"0.002336","b":"-19840.744388","width":148,"height":341,"depth":460,"defaultGoodsId":"613008720209","status":0,"goodsList":[{"goodsId":"613008720209","goodsName":"亚利桑那冰茶西瓜味饮料","goodsPhotoUrl":"http://img.yunatop.com/613008720209.jpg","goodsNum":3,"goodsUnit":"","goodsPrice":"0.00","diffRisk":6}],"points":[{"x":-820,"y":621,"z":800},{"x":-820,"y":773,"z":800},{"x":-820,"y":773,"z":1141},{"x":-820,"y":621,"z":1141}]},{"laneId":"SHELF17101600005102","shelfId":"SHELF171016000051","rackId":"1001","storeId":"SHP001","type":"01","k":"0.002299","b":"-20740.958126","width":148,"height":341,"depth":460,"defaultGoodsId":"613008719302","status":0,"goodsList":[],"points":[{"x":-820,"y":773,"z":800},{"x":-820,"y":925,"z":800},{"x":-820,"y":925,"z":1141},{"x":-820,"y":773,"z":1141}]},{"laneId":"SHELF17101600005103","shelfId":"SHELF171016000051","rackId":"1001","storeId":"SHP001","type":"01","k":"0.002340","b":"-4534.321392","width":148,"height":341,"depth":460,"defaultGoodsId":"4054500525811","status":0,"goodsList":[],"points":[{"x":-820,"y":925,"z":800},{"x":-820,"y":1076,"z":800},{"x":-820,"y":1076,"z":1141},{"x":-820,"y":925,"z":1141}]},{"laneId":"SHELF17101600005104","shelfId":"SHELF171016000051","rackId":"1001","storeId":"SHP001","type":"01","k":"0.002347","b":"-21232.372256","width":148,"height":341,"depth":460,"defaultGoodsId":"8718104912182","status":0,"goodsList":[],"points":[{"x":-820,"y":1076,"z":800},{"x":-820,"y":1228,"z":800},{"x":-820,"y":1228,"z":1141},{"x":-820,"y":1076,"z":1141}]},{"laneId":"SHELF17101600005105","shelfId":"SHELF171016000051","rackId":"1001","storeId":"SHP001","type":"01","k":"0.002282","b":"-18824.614348","width":148,"height":341,"depth":460,"defaultGoodsId":"6923450662113","status":0,"goodsList":[{"goodsId":"6923450662113","goodsName":"冰爽冰柠味18粒","goodsPhotoUrl":"http://img.yunatop.com/6923450662113.jpg","goodsNum":1,"goodsUnit":"","goodsPrice":"0.00","diffRisk":5}],"points":[{"x":-820,"y":1228,"z":800},{"x":-820,"y":1380,"z":800},{"x":-820,"y":1380,"z":1141},{"x":-820,"y":1228,"z":1141}]}]
     */

    private String shelfId;
    private String smpMac;
    private String shelfType;
    private int laneAmount;
    private int shelfWidth;
    private int shelfDepth;
    private String status;
    private String storeId;
    private String rackId;
    private int startHeight;
    private int height;
    private List<LaneModelListBean> laneModelList;

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public String getSmpMac() {
        return smpMac;
    }

    public void setSmpMac(String smpMac) {
        this.smpMac = smpMac;
    }

    public String getShelfType() {
        return shelfType;
    }

    public void setShelfType(String shelfType) {
        this.shelfType = shelfType;
    }

    public int getLaneAmount() {
        return laneAmount;
    }

    public void setLaneAmount(int laneAmount) {
        this.laneAmount = laneAmount;
    }

    public int getShelfWidth() {
        return shelfWidth;
    }

    public void setShelfWidth(int shelfWidth) {
        this.shelfWidth = shelfWidth;
    }

    public int getShelfDepth() {
        return shelfDepth;
    }

    public void setShelfDepth(int shelfDepth) {
        this.shelfDepth = shelfDepth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }

    public int getStartHeight() {
        return startHeight;
    }

    public void setStartHeight(int startHeight) {
        this.startHeight = startHeight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<LaneModelListBean> getLaneModelList() {
        return laneModelList;
    }

    public void setLaneModelList(List<LaneModelListBean> laneModelList) {
        this.laneModelList = laneModelList;
    }

    public static class LaneModelListBean {
        /**
         * laneId : SHELF17101600005100
         * shelfId : SHELF171016000051
         * rackId : 1001
         * storeId : SHP001
         * type : 01
         * k : 0.002339
         * b : -16973.934054
         * width : 148
         * height : 341
         * depth : 460
         * defaultGoodsId : 613008715267
         * status : 0
         * goodsList : []
         * points : [{"x":-820,"y":470,"z":800},{"x":-820,"y":621,"z":800},{"x":-820,"y":621,"z":1141},{"x":-820,"y":470,"z":1141}]
         */

        private String laneId;
        private String shelfId;
        private String rackId;
        private String storeId;
        private String type;
        private String k;
        private String b;
        private int width;
        private int height;
        private int depth;
        private String defaultGoodsId;
        private String defGoodsPrice;
        private int status;
        private ArrayList<GoodsInfoBean> goodsList;
        private List<PointsBean> points;

        public String getLaneId() {
            return laneId;
        }

        public void setLaneId(String laneId) {
            this.laneId = laneId;
        }

        public String getShelfId() {
            return shelfId;
        }

        public void setShelfId(String shelfId) {
            this.shelfId = shelfId;
        }

        public String getRackId() {
            return rackId;
        }

        public void setRackId(String rackId) {
            this.rackId = rackId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public String getDefaultGoodsId() {
            return defaultGoodsId;
        }

        public void setDefaultGoodsId(String defaultGoodsId) {
            this.defaultGoodsId = defaultGoodsId;
        }

        public String getDefGoodsPrice() {
            return defGoodsPrice;
        }

        public void setDefGoodsPrice(String defGoodsPrice) {
            this.defGoodsPrice = defGoodsPrice;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ArrayList<GoodsInfoBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(ArrayList<GoodsInfoBean> goodsList) {
            this.goodsList = goodsList;
        }

        public List<PointsBean> getPoints() {
            return points;
        }

        public void setPoints(List<PointsBean> points) {
            this.points = points;
        }

        public static class PointsBean {
            /**
             * x : -820
             * y : 470
             * z : 800
             */

            private int x;
            private int y;
            private int z;

            public void setX(int x) {
                this.x = x;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getZ() {
                return z;
            }

            public void setZ(int z) {
                this.z = z;
            }
        }
    }
}
