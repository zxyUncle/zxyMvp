package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class NegativeStorageGoodsRequest {
    private String code;
    private String message;
    private int count;
    private List<NegativeStorageGoodsBean> data;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<NegativeStorageGoodsBean> getData() {
        return data;
    }

    public void setData(List<NegativeStorageGoodsBean> data) {
        this.data = data;
    }

}
