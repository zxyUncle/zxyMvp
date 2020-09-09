package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class GatesBean {

    /**
     * code : 0000
     * gates : [{"storeId":"CS0001","gateId":"gate","type":"03","regionA":{"x":0,"y":-300,"z":0},"regionB":{"x":0,"y":-1000,"z":0},"regionC":{"x":700,"y":-300,"z":0},"regionD":{"x":700,"y":-1000,"z":0},"status":"00"}]
     * success : true
     */

    public String code;
    public String message;
    public boolean success;
    public List<GateBean> gates;

    public static class GateBean {
        /**
         * storeId : CS0001
         * gateId : gate
         * type : 03
         * regionA : {"x":0,"y":-300,"z":0}
         * regionB : {"x":0,"y":-1000,"z":0}
         * regionC : {"x":700,"y":-300,"z":0}
         * regionD : {"x":700,"y":-1000,"z":0}
         * status : 00
         */

        public String storeId;
        public String gateId;
        public String type;
        public RegionABean regionA;
        public RegionBBean regionB;
        public RegionCBean regionC;
        public RegionDBean regionD;
        public String status;

        public static class RegionABean {
            /**
             * x : 0.0
             * y : -300.0
             * z : 0.0
             */

            public double x;
            public double y;
            public double z;
        }

        public static class RegionBBean {
            /**
             * x : 0.0
             * y : -1000.0
             * z : 0.0
             */

            public double x;
            public double y;
            public double z;
        }

        public static class RegionCBean {
            /**
             * x : 700.0
             * y : -300.0
             * z : 0.0
             */

            public double x;
            public double y;
            public double z;
        }

        public static class RegionDBean {
            /**
             * x : 700.0
             * y : -1000.0
             * z : 0.0
             */

            public double x;
            public double y;
            public double z;
        }
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<GateBean> getGates() {
        return gates;
    }

    public void setGates(List<GateBean> gates) {
        this.gates = gates;
    }
}
