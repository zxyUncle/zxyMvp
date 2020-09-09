package com.cloudpick.yunnasdk.entity;

/**
 * Created by zxy on 2020/8/6 11:17
 * ******************************************
 * *
 * ******************************************
 */
public class RackInfoResBean {
    private String code;
    private String message;
    private RackInfoBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RackInfoBean getData() {
        return data;
    }

    public void setData(RackInfoBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
