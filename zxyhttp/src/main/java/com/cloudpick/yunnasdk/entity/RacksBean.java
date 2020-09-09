package com.cloudpick.yunnasdk.entity;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/22.
 */

public class RacksBean {
    private float ratio;
//    原点x
    private int originX;
//    圆点y
    private int originY;
    //画布大小
    private int maxX;
    private int maxY;
    private ArrayList<Rack> racks;

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public ArrayList<Rack> getRacks() {
        return racks;
    }

    public void setRacks(ArrayList<Rack> racks) {
        this.racks = racks;
    }

    public class Rack {
        private String rackId;
        private ArrayList<PointF> points;

        public String getRackId() {
            return rackId;
        }

        public void setRackId(String rackId) {
            this.rackId = rackId;
        }

        public ArrayList<PointF> getPoints() {
            return points;
        }

        public void setPoints(ArrayList<PointF> points) {
            this.points = points;
        }
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
}
