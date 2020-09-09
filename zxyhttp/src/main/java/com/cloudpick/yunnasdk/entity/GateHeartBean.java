package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class GateHeartBean {

    /**
     * code : 0000
     * heartBeatInfos : [{"type":"01","sourceId":"00E04C6803E7","storeId":"SHP001","lastBeatTime":"2018-12-05 15:09:40","intervalTime":90,"valid":"Y","status":"00"},{"type":"01","sourceId":"00E04C6803E8","storeId":"SHP001","lastBeatTime":"2018-12-05 15:09:40","intervalTime":90,"valid":"Y","status":"00"}]
     * success : true
     */

    private String code;
    private boolean success;
    private List<HeartBeatInfosBean> heartBeatInfos;
    private String message;

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

    public List<HeartBeatInfosBean> getHeartBeatInfos() {
        return heartBeatInfos;
    }

    public void setHeartBeatInfos(List<HeartBeatInfosBean> heartBeatInfos) {
        this.heartBeatInfos = heartBeatInfos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class HeartBeatInfosBean {
        /**
         * type : 01
         * sourceId : 00E04C6803E7
         * storeId : SHP001
         * lastBeatTime : 2018-12-05 15:09:40
         * intervalTime : 90
         * valid : Y
         * status : 00
         */

        private String type;
        private String sourceId;
        private String storeId;
        private String lastBeatTime;
        private int intervalTime;
        private String valid;
        private String status;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getLastBeatTime() {
            return lastBeatTime;
        }

        public void setLastBeatTime(String lastBeatTime) {
            this.lastBeatTime = lastBeatTime;
        }

        public int getIntervalTime() {
            return intervalTime;
        }

        public void setIntervalTime(int intervalTime) {
            this.intervalTime = intervalTime;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
