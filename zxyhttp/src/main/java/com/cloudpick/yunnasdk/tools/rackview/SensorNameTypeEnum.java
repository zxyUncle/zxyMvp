package com.cloudpick.yunnasdk.tools.rackview;

import android.content.Context;

import com.cloudpick.yunnasdk.R;


public enum SensorNameTypeEnum {
//coldTemper,hotTemper
     COLD("coldTemper", R.string.cold_temperatrue),
     HOT( "hotTemper",R.string.hot_temperatrue),
     DEFAULT( "unknown",R.string.error_temperatrue);

    int resId;
    String type;

    SensorNameTypeEnum(String type, int resId){
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

    public static String getValue(Context context, String status) {
        for (SensorNameTypeEnum ele : values()) {
            if (ele.getType().equals(status))
                return context.getResources().getString(ele.getResId());
        }
        return "";
    }
}
