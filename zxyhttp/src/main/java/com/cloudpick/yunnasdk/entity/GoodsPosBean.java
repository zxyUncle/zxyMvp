package com.cloudpick.yunnasdk.entity;

import java.io.Serializable;
import java.util.List;

public class GoodsPosBean implements Serializable{

    /**
     * code : 0000
     * laneInfos : [{"rackId":"0002","shelfId":"SFSHP002000204","laneId":"SFSHP00200020405"}]
     * success : true
     */

    private String code;
    private boolean success;
    private List<LaneInfosBean> laneInfos;
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

    public List<LaneInfosBean> getLaneInfos() {
        return laneInfos;
    }

    public void setLaneInfos(List<LaneInfosBean> laneInfos) {
        this.laneInfos = laneInfos;
    }

    public static class LaneInfosBean implements Serializable{
        /**
         * rackId : 0002
         * shelfId : SFSHP002000204
         * laneId : SFSHP00200020405
         */

        private String rackId;
        private String shelfId;
        private String laneId;

        public String getRackId() {
            return rackId;
        }

        public void setRackId(String rackId) {
            this.rackId = rackId;
        }

        public String getShelfId() {
            return shelfId;
        }

        public void setShelfId(String shelfId) {
            this.shelfId = shelfId;
        }

        public String getLaneId() {
            return laneId;
        }

        public void setLaneId(String laneId) {
            this.laneId = laneId;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
