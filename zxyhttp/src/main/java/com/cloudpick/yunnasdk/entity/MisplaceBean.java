package com.cloudpick.yunnasdk.entity;

import java.util.List;

/**
 * 脏栏
 */
public class MisplaceBean {

    /**
     * code : 0000
     * laneGoodsList : [{"laneId":"SFSHP0010160401","storeId":"SHP001","rackId":"016","defaultGoodsId":"8801048311002","minDefaultWeight":369,"maxDefaultWeight":374,"status":0,"laneGoodsList":[{"laneId":"SFSHP0010160401","goodsId":"613008722869","goodsNum":5,"goodsUnit":"","minWeight":719,"maxWeight":729,"diffRisk":5}]},{"laneId":"SFSHP00110030000","storeId":"SHP001","rackId":"1003","defaultGoodsId":"613008719302","minDefaultWeight":716,"maxDefaultWeight":728,"status":0,"laneGoodsList":[{"laneId":"SFSHP00110030000","goodsId":"XGOOD488381","goodsNum":1,"goodsUnit":"","minWeight":367,"maxWeight":377,"diffRisk":0}]}]
     * success : true
     */

    private String code;
    private boolean success;
    private List<LaneGoodsListBeanX> laneGoodsList;

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

    public List<LaneGoodsListBeanX> getLaneGoodsList() {
        return laneGoodsList;
    }

    public void setLaneGoodsList(List<LaneGoodsListBeanX> laneGoodsList) {
        this.laneGoodsList = laneGoodsList;
    }

    public static class LaneGoodsListBeanX {
        /**
         * laneId : SFSHP0010160401
         * storeId : SHP001
         * rackId : 016
         * defaultGoodsId : 8801048311002
         * minDefaultWeight : 369
         * maxDefaultWeight : 374
         * status : 0
         * laneGoodsList : [{"laneId":"SFSHP0010160401","goodsId":"613008722869","goodsNum":5,"goodsUnit":"","minWeight":719,"maxWeight":729,"diffRisk":5}]
         */

        private String laneId;
        private String storeId;
        private String rackId;
        private String defaultGoodsId;
        private int minDefaultWeight;
        private int maxDefaultWeight;
        private int status;
        private List<LaneGoodsListBean> laneGoodsList;

        public String getLaneId() {
            return laneId;
        }

        public void setLaneId(String laneId) {
            this.laneId = laneId;
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

        public String getDefaultGoodsId() {
            return defaultGoodsId;
        }

        public void setDefaultGoodsId(String defaultGoodsId) {
            this.defaultGoodsId = defaultGoodsId;
        }

        public int getMinDefaultWeight() {
            return minDefaultWeight;
        }

        public void setMinDefaultWeight(int minDefaultWeight) {
            this.minDefaultWeight = minDefaultWeight;
        }

        public int getMaxDefaultWeight() {
            return maxDefaultWeight;
        }

        public void setMaxDefaultWeight(int maxDefaultWeight) {
            this.maxDefaultWeight = maxDefaultWeight;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<LaneGoodsListBean> getLaneGoodsList() {
            return laneGoodsList;
        }

        public void setLaneGoodsList(List<LaneGoodsListBean> laneGoodsList) {
            this.laneGoodsList = laneGoodsList;
        }

        public static class LaneGoodsListBean {
            /**
             * laneId : SFSHP0010160401
             * goodsId : 613008722869
             * goodsNum : 5
             * goodsUnit :
             * minWeight : 719
             * maxWeight : 729
             * diffRisk : 5
             */

            private String laneId;
            private String goodsId;
            private int goodsNum;
            private String goodsUnit;
            private int minWeight;
            private int maxWeight;
            private int diffRisk;

            public String getLaneId() {
                return laneId;
            }

            public void setLaneId(String laneId) {
                this.laneId = laneId;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getGoodsUnit() {
                return goodsUnit;
            }

            public void setGoodsUnit(String goodsUnit) {
                this.goodsUnit = goodsUnit;
            }

            public int getMinWeight() {
                return minWeight;
            }

            public void setMinWeight(int minWeight) {
                this.minWeight = minWeight;
            }

            public int getMaxWeight() {
                return maxWeight;
            }

            public void setMaxWeight(int maxWeight) {
                this.maxWeight = maxWeight;
            }

            public int getDiffRisk() {
                return diffRisk;
            }

            public void setDiffRisk(int diffRisk) {
                this.diffRisk = diffRisk;
            }
        }
    }
}
