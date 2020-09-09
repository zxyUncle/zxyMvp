package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class OrderTransInfoBean {

    /**
     * code : 0000
     * data : [{"transId":"190627SHP001810T00007581","transAmt":100,"transStatus":"04","payType":"wechat_preauth_yunna_refund","transDesc":"退款成功","accAmt":0,"transTime":"2019-06-27 14:49:26"}]
     * success : true
     */

    private String code;
    private boolean success;
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
         * transId : 190627SHP001810T00007581
         * transAmt : 100
         * transStatus : 04
         * payType : wechat_preauth_yunna_refund
         * transDesc : 退款成功
         * accAmt : 0
         * transTime : 2019-06-27 14:49:26
         */

        private String transId;
        private float transAmt;
        private String transStatus;
        private String payType;
        private String transDesc;
        private int accAmt;
        private String transTime;

        public String getTransId() {
            return transId;
        }

        public void setTransId(String transId) {
            this.transId = transId;
        }

        public float getTransAmt() {
            return transAmt;
        }

        public void setTransAmt(float transAmt) {
            this.transAmt = transAmt;
        }

        public String getTransStatus() {
            return transStatus;
        }

        public void setTransStatus(String transStatus) {
            this.transStatus = transStatus;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getTransDesc() {
            return transDesc;
        }

        public void setTransDesc(String transDesc) {
            this.transDesc = transDesc;
        }

        public int getAccAmt() {
            return accAmt;
        }

        public void setAccAmt(int accAmt) {
            this.accAmt = accAmt;
        }

        public String getTransTime() {
            return transTime;
        }

        public void setTransTime(String transTime) {
            this.transTime = transTime;
        }
    }
}
