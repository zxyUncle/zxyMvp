package com.cloudpick.yunnasdk.entity;

import java.util.List;
import java.util.Map;

public class SensorRecordBean {
    private String code;
    private Map<String, List<TemperatureBean>> sensorModeMap;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, List<TemperatureBean>> getSensorModeMap() {
        return sensorModeMap;
    }

    public void setSensorModeMap(Map<String, List<TemperatureBean>> sensorModeMap) {
        this.sensorModeMap = sensorModeMap;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
