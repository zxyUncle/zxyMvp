package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class SensorListResultBean {
    private String code;
    private List<SensorBean> rackSensorDTOs;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SensorBean> getRackSensorDTOs() {
        return rackSensorDTOs;
    }

    public void setRackSensorDTOs(List<SensorBean> rackSensorDTOs) {
        this.rackSensorDTOs = rackSensorDTOs;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
