package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class ReplenishmentGroupofRackIdsRequest {
    private String code;
    private String message;
    private List<String> rackIds;

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

    public List<String> getRackIds() {
        return rackIds;
    }

    public void setRackIds(List<String> rackIds) {
        this.rackIds = rackIds;
    }
}
