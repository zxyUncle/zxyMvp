package com.cloudpick.yunnasdk.tools.rackview;


import com.cloudpick.yunnasdk.R;

public enum SensorTypeEnum {
//coldTemper,hotTemper
     COLD("coldTemper", R.mipmap.sensor_tem_common),
     HOT( "hotTemper",R.mipmap.sensor_tem_common),
     DEFAULT( "unknown",R.mipmap.sensor_error);

    int resId;
    String type;

    SensorTypeEnum(String type, int resId){
        this.type = type;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int getValue(String status) {
        for (SensorTypeEnum ele : values()) {
            if (ele.getType().equals(status))
                return ele.getResId();
        }
        return 0;
    }
}
