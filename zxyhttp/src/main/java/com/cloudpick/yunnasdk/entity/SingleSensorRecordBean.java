package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class SingleSensorRecordBean {
    private String code;
    private List<TemperatureBean> sensorRecordModels;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<TemperatureBean> getSensorRecordModels() {
        return sensorRecordModels;
    }

    public void setSensorRecordModels(List<TemperatureBean> sensorRecordModels) {
        this.sensorRecordModels = sensorRecordModels;
    }
}
