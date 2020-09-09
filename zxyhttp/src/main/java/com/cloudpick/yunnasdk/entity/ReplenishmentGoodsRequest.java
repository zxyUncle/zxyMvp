package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class ReplenishmentGoodsRequest {
    private String code;
    private String message;
    private List<ReplenishmentGoodsBean> replenishBill;

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

    public List<ReplenishmentGoodsBean> getReplenishBill() {
        return replenishBill;
    }

    public void setReplenishBill(List<ReplenishmentGoodsBean> replenishBill) {
        this.replenishBill = replenishBill;
    }
}
