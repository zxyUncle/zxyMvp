package com.cloudpick.yunnasdk.entity;

import java.util.List;


public class LandBeanRequest {
    private String code;
    private String message;
    private List<String> rackId;
    private List<RackLane> rackLanes;

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


    public List<String> getRackId() {
        return rackId;
    }

    public void setRackId(List<String> rackId) {
        this.rackId = rackId;
    }

    public List<RackLane> getRackLanes() {
        return rackLanes;
    }

    public void setRackLanes(List<RackLane> rackLanes) {
        this.rackLanes = rackLanes;
    }

    public static class RackLane{
         private String count;
         private List<LaneId> lanelList;
         private String rackId;//

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<LaneId> getLanelList() {
            return lanelList;
        }

        public void setLanelList(List<LaneId> lanelList) {
            this.lanelList = lanelList;
        }

        public String getRackId() {
            return rackId;
        }

        public void setRackId(String rackId) {
            this.rackId = rackId;
        }
    }
    public static class LaneId{
        private String laneId;

        public String getLaneId() {
            return laneId;
        }

        public void setLaneId(String laneId) {
            this.laneId = laneId;
        }
    }
}
