package com.cloudpick.yunnasdk.entity;

public class TemperatureBean implements Comparable<TemperatureBean>{
    private float calValue;
    private String rackId;
    private String sensorId;
    private String sensorType;
    private String storeId;
    private long timestamp;

    public float getCalValue() {
        return calValue;
    }

    public void setCalValue(float calValue) {
        this.calValue = calValue;
    }

    public String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(TemperatureBean o) {
        return (int)(o.getTimestamp()-this.getTimestamp());
    }
}
