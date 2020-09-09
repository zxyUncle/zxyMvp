package com.cloudpick.yunnasdk.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsActivityBean implements Serializable {

    private Map<String, List<GoodsActivity>> map = new HashMap<>();

    public Map<String, List<GoodsActivity>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<GoodsActivity>> map) {
        this.map = map;
    }

    public static class GoodsActivity implements Serializable{
        private String activityId;
        private String activityName;
        private String activityTypeId;
        private String timeSlot;

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityTypeId() {
            return activityTypeId;
        }

        public void setActivityTypeId(String activityTypeId) {
            this.activityTypeId = activityTypeId;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(String timeSlot) {
            this.timeSlot = timeSlot;
        }
    }
}
