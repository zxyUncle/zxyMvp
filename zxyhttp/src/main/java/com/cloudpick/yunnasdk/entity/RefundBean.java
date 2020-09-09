package com.cloudpick.yunnasdk.entity;

public class RefundBean {

    /**
     * code : 0000
     * refundOrderId : 190213SHP001193D00004281
     * refundStatus : refund_fail
     * success : true
     */
    public static final String status_fail = "refund_fail";
    private String code;
    private String refundOrderId;
    private String refundStatus;
    private boolean success;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
