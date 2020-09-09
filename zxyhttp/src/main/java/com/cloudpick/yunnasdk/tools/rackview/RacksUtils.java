package com.cloudpick.yunnasdk.tools.rackview;

import android.graphics.PointF;

import com.cloudpick.yunnasdk.entity.RacksBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zxy on 2020/8/6 10:31
 * ******************************************
 * * sao操作
 * ******************************************
 */
public class RacksUtils {

    public RacksBean convert(String s) {
        RacksBean racksBean = new RacksBean();
        ArrayList<RacksBean.Rack> list = new ArrayList<>();
        racksBean.setRacks(list);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject data = (JSONObject) jsonObject.get("data");
            int maxX = (int) data.opt("maxX");
            int maxY = (int) data.opt("maxY");
            racksBean.setRatio(1.0f * maxY / maxX);
            int originX = (int) data.opt("originX");
            int originY = (int) data.opt("originY");
            racksBean.setOriginX(originX);
            racksBean.setOriginY(originY);
            racksBean.setMaxX(maxX);
            racksBean.setMaxY(maxY);
            JSONArray rackList = (JSONArray) data.opt("rackList");

            for (int i = 0; i < rackList.length(); i++) {
                JSONObject rack = (JSONObject) rackList.get(i);
                String rackId = (String) rack.opt("rackId");
                RacksBean.Rack rackBean = racksBean.new Rack();
                ArrayList<PointF> points = new ArrayList<>();
                rackBean.setRackId(rackId);
                rackBean.setPoints(points);
                list.add(rackBean);
                try {
                    for (int j = 0; j < 10; j++) {
                        int x = (int) rack.get("x" + j);
                        int y = (int) rack.get("y" + j);
                        points.add(new PointF(1.0f * (x - originX) / maxX, 1.0f * (originY - y) / maxY));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return racksBean;
    }
}
