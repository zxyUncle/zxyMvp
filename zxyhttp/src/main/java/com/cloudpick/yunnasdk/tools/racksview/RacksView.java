package com.cloudpick.yunnasdk.tools.racksview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.cloudpick.yunnasdk.R;
import com.cloudpick.yunnasdk.entity.GatesBean;
import com.cloudpick.yunnasdk.entity.GoodsPosBean;
import com.cloudpick.yunnasdk.entity.LandBeanRequest;
import com.cloudpick.yunnasdk.entity.MisplaceBean;
import com.cloudpick.yunnasdk.entity.RacksBean;
import com.cloudpick.yunnasdk.tools.rackview.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 */
public class RacksView extends View {

    Paint paintNormal;
    Paint paintSelected;
    Paint paintRed;
    Paint textPaint;
    Paint textMisPaint;
    //每个rack的坐标
    ArrayList<ArrayList<PointF>> areaList = new ArrayList<>();
    //每个rack的id,与坐标集合 一一对应
    ArrayList<String> rackIds = new ArrayList<>();
    //返回的id有“”的，避免默认选中，给个none~
    public String selectedId = "none";
    //脏栏
    private MisplaceBean misplace;
    // 脏栏
    private List<LandBeanRequest.RackLane> rackLanes;
    //商品位置   从商品列表跳转过来的时候，需要处理；其余情况不用管
    private GoodsPosBean mGoodsPosBean;
    //闸机
    private GatesBean mGatesBean;
    //闸机区域集合
    ArrayList<ArrayList<PointF>> gatesAreaList = new ArrayList<>();
    //每个gate的id,与坐标集合 一一对应
    ArrayList<String> gateIds = new ArrayList<>();
    //闸机状态  false:关闭    true 开启
    ArrayList<Boolean> gateStates = new ArrayList<>();
    private boolean showId;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, String rackId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RacksView(Context context) {
        super(context);
        init();
    }

    public RacksView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paintNormal = new Paint();
        paintNormal.setColor(Color.parseColor("#6785C1"));
        paintNormal.setStyle(Paint.Style.FILL_AND_STROKE);
        paintNormal.setAntiAlias(true);

        paintSelected = new Paint();
        paintSelected.setColor(getResources().getColor(R.color.colorLightOrange));
        paintSelected.setStyle(Paint.Style.FILL_AND_STROKE);
        paintSelected.setAntiAlias(true);

        paintRed = new Paint();
        paintRed.setColor(0XFFFF0000);
        paintRed.setStyle(Paint.Style.FILL_AND_STROKE);
        paintRed.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setAntiAlias(true);

        textMisPaint = new Paint();
        textMisPaint.setColor(Color.WHITE);
        textMisPaint.setTextSize(Tools.dp2px(getContext(), 10));
        textMisPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textMisPaint.setAntiAlias(true);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String rackId = RacksView.this.getClickRackId();
                String gateId =  RacksView.this.getClickGateId();
                if (gateId != null) {
                    setSelectedId(gateId);
                    return;
                }
                if (rackId != null) {
                    RacksView.this.setSelectedId(rackId);
                    if(onItemClickListener!=null) {
                        onItemClickListener.onItemClick(view, rackId);
                    }
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < areaList.size(); i++) {
            Path path = new Path();
            for (int j = 0; j < areaList.get(i).size(); j++) {
                PointF pointF = areaList.get(i).get(j);
                if (j == 0)
                    path.moveTo(pointF.x, pointF.y);
                else
                    path.lineTo(pointF.x, pointF.y);
            }
            int selectedIndex = rackIds.indexOf(selectedId);
            if (selectedIndex == i) {       //选中的话，先画选中色
                canvas.drawPath(path, paintSelected);
            } else {
                canvas.drawPath(path, paintNormal);
            }
            if (selectedIndex == i) {       //选中的话，各个点向中心点偏移点，再画个未选中色
                float offset = Tools.dp2px(getContext(), 2);
                float minX = areaList.get(i).get(0).x;
                float maxX = areaList.get(i).get(0).x;
                float minY = areaList.get(i).get(0).y;
                float maxY = areaList.get(i).get(0).y;
                for (int j = 0; j < areaList.get(i).size(); j++) {
                    if (areaList.get(i).get(j).x < minX)
                        minX = areaList.get(i).get(j).x;
                    if (areaList.get(i).get(j).y < minY)
                        minY = areaList.get(i).get(j).y;
                    if (areaList.get(i).get(j).x > maxX)
                        maxX = areaList.get(i).get(j).x;
                    if (areaList.get(i).get(j).y > maxY)
                        maxY = areaList.get(i).get(j).y;
                }
                float avgX = (minX + maxX) / 2;
                float avgY = (minY + maxY) / 2;
                Path selectedInner = new Path();
                for (int j = 0; j < areaList.get(i).size(); j++) {
                    PointF pointF = areaList.get(i).get(j);
                    PointF pointNew = new PointF();
                    pointNew.set(pointF);
                    if (pointNew.x < avgX)
                        pointNew.x = pointF.x + offset;
                    if (pointNew.x > avgX)
                        pointNew.x = pointNew.x - offset;
                    if (pointNew.y < avgY)
                        pointNew.y = pointNew.y + offset;
                    if (pointNew.y > avgY)
                        pointNew.y = pointNew.y - offset;
                    if (j == 0)
                        selectedInner.moveTo(pointNew.x, pointNew.y);
                    else if (j == areaList.get(i).size())
                        selectedInner.close();
                    else
                        selectedInner.lineTo(pointNew.x, pointNew.y);
                }
                canvas.drawPath(selectedInner, paintNormal);
            }

            //绘制rackId文字
            if (showId) {
                measureTextSize();
                String text = rackIds.get(i);

                int left = 10000;
                int right = 0;
                int top = 10000;
                int bottom = 0;
                for (PointF pointF : areaList.get(i)) {
                    if (pointF.x < left) {
                        left = (int) pointF.x;
                    }
                    if (pointF.x > right) {
                        right = (int) pointF.x;
                    }
                    if (pointF.y > bottom) {
                        bottom = (int) pointF.y;
                    }
                    if (pointF.y < top) {
                        top = (int) pointF.y;
                    }
                }
                Paint.FontMetrics fm = textPaint.getFontMetrics();
                int y = (int) ((top + bottom) / 2 - fm.descent + (fm.bottom - fm.top) / 2);
                int textWidth = (int) textPaint.measureText(text);
                canvas.drawText(text, (left + right) / 2 - textWidth / 2, y, textPaint);
            }

//            //绘制脏栏，右上角的小圆点
//            if (misplace != null && misplace.getLaneGoodsList().size() > 0) {
//                List<MisplaceBean.LaneGoodsListBeanX> laneGoodsList = misplace.getLaneGoodsList();
//                for (MisplaceBean.LaneGoodsListBeanX lane : laneGoodsList) {
//                    //这个rack有脏栏
//                    if (rackIds.get(i).equals(lane.getRackId())) {
//                        ArrayList<PointF> points = areaList.get(i);
//                        //找到右上角
//                        PointF pointF = points.get(0);
//                        for (PointF point : points) {
//                            if (point.x >= pointF.x && point.y <= pointF.y) {
//                                pointF = point;
//                            }
//                        }
//                        int r = Tools.dp2px(getContext(), 2);
//                        canvas.drawCircle(pointF.x - 3 * r, pointF.y + 3 * r, 2 * r, paintRed);
//                        break;
//                    }
//                }
//            }

            //绘制脏栏，右上角的小圆点
            if (rackLanes != null && rackLanes.size() > 0) {
                for (LandBeanRequest.RackLane lane : rackLanes) {
                    //这个rack有脏栏
                    if (rackIds.get(i).equals(lane.getRackId())) {
                        ArrayList<PointF> points = areaList.get(i);
                        //找到右上角
                        PointF pointF = points.get(0);
                        for (PointF point : points) {
                            if (point.x >= pointF.x && point.y <= pointF.y) {
                                pointF = point;
                            }
                        }
                        int r = Tools.dp2px(getContext(), 4);
                        canvas.drawCircle(pointF.x - 2 * r, pointF.y + 2 * r, 2 * r, paintRed);

                        Rect lRect = new Rect();
                        textMisPaint.getTextBounds(lane.getCount(), 0, lane.getCount().length(), lRect);
                        canvas.drawText(lane.getCount(), pointF.x - 2 * r - lRect.width() / 2, pointF.y + 2 * r + lRect.height() / 2, textMisPaint);
                        break;
                    }
                }
            }
        }
        //绘制包含查找的商品的货架
        if (mGoodsPosBean != null) {
            List<GoodsPosBean.LaneInfosBean> laneInfos = mGoodsPosBean.getLaneInfos();
            for (GoodsPosBean.LaneInfosBean laneInfo : laneInfos) {
                String rackId = laneInfo.getRackId();
                int index = rackIds.indexOf(rackId);
                if (index < 0)
                    return;
                ArrayList<PointF> pointFS = areaList.get(index);
                PointF pointLeftTop = pointFS.get(0);
                PointF pointRightTop = pointFS.get(0);
                PointF pointLeftBottom = pointFS.get(0);
                for (PointF point : pointFS) {
                    if (point.x <= pointLeftTop.x && point.y <= pointLeftTop.y) {
                        pointLeftTop = point;
                    } else if (point.x >= pointLeftTop.x && point.y <= pointLeftTop.y) {
                        pointRightTop = point;
                    } else if (point.x <= pointLeftTop.x && point.y >= pointLeftTop.y) {
                        pointLeftBottom = point;
                    }
                }
                float width = pointRightTop.x - pointLeftTop.x;
                float height = pointLeftBottom.y - pointLeftTop.y;
                float centerX = (pointRightTop.x + pointLeftTop.x) / 2;
                float centerY = (pointLeftTop.y + pointLeftBottom.y) / 2;
                float size = Math.min(width, height) / 2;
                RectF rectF = new RectF(centerX - size / 2, centerY - size / 2, centerX + size / 2, centerY + size / 2);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_goods_position);
                canvas.drawBitmap(bitmap, null, rectF, paintNormal);
            }
        }

        //绘制闸机 有重复的边框，所以先画未选中的，再画选中的，防止覆盖
        if (gatesAreaList.size() > 0) {
            Paint paint = getPaint("#6785C1", 8);
            Paint paint2 = getPaint("#6785C1", 3);
            Paint paintSelected = getPaint("#FBB039", 8);
            for (int i = 0; i < gatesAreaList.size(); i++) {
                PointF p0 = gatesAreaList.get(i).get(0);
                PointF p1 = gatesAreaList.get(i).get(1);
                PointF p2 = gatesAreaList.get(i).get(2);
                PointF p3 = gatesAreaList.get(i).get(3);
                Path path = new Path();
                path.moveTo(p0.x, p0.y);
                path.lineTo(p1.x, p1.y);
                path.moveTo(p2.x, p2.y);
                path.lineTo(p3.x, p3.y);
                if (gateIds.indexOf(selectedId) != i) {
                    canvas.drawPath(path, paint);
                }
            }
            for (int i = 0; i < gatesAreaList.size(); i++) {

                PointF p0 = gatesAreaList.get(i).get(0);
                PointF p1 = gatesAreaList.get(i).get(1);
                PointF p2 = gatesAreaList.get(i).get(2);
                PointF p3 = gatesAreaList.get(i).get(3);
                Path path = new Path();
                path.moveTo(p0.x, p0.y);
                path.lineTo(p1.x, p1.y);
                path.moveTo(p2.x, p2.y);
                path.lineTo(p3.x, p3.y);
                if (gateIds.indexOf(selectedId) == i) {
                    canvas.drawPath(path, paintSelected);
                    canvas.drawPath(path, paint2);
                }

            }
        }
    }

    public Paint getPaint(String color, int dpWidth) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(Tools.dp2px(getContext(), dpWidth));
        return paint;
    }

    private int realWidth;
    private int realHeight;

//    public void updateCanvas(RacksBean bean, MisplaceBean misplaceBean) {
//        //脏栏
//        this.misplace = misplaceBean;
//        //商品位置清空
//        this.mGoodsPosBean = null;
//        //货架id清空
//        setShowId(false);
//        //设置高度
////        float ratio = bean.getRatio();
//        float r1 = bean.getRatio();
//        float r2 = 1.0f * getHeight() / getWidth();
//        if (r1 >= r2) {     //高度固定，计算宽度
//            realHeight = getHeight() - getPaddingTop() - getPaddingBottom();
//            realWidth = (int) (realHeight / r1);
//        } else {        //宽度固定，计算高度
//            realWidth = getWidth() - getPaddingLeft() - getPaddingRight() - 15;
//            realHeight = (int) (r1 * realWidth);
//        }
//        ArrayList<RacksBean.Rack> rackBeans = bean.getRacks();
//        areaList.clear();
//        rackIds.clear();
//        for (int i = 0; i < rackBeans.size(); i++) {
//            ArrayList<PointF> points = new ArrayList<>();
//            for (int j = 0; j < rackBeans.get(i).getPoints().size(); j++) {
//                PointF pointF = rackBeans.get(i).getPoints().get(j);
//                PointF e = new PointF(realWidth * pointF.x, realHeight * pointF.y);
//                points.add(e);
//            }
//            points.add(points.get(0));
//            rackIds.add(rackBeans.get(i).getRackId());
//            areaList.add(points);
//        }
//        postInvalidate();
//    }


//    public void updateCanvas(RacksBean bean, List<LandBeanRequest.RackLane> rackLanes) {
    public void updateCanvas(RacksBean bean) {
        //脏栏
        this.rackLanes = rackLanes;
        //商品位置清空
        this.mGoodsPosBean = null;
        //货架id清空
        setShowId(false);
        //设置高度
//        float ratio = bean.getRatio();
        float r1 = bean.getRatio();
//        float r2 = 1.0f * getHeight() / getWidth();
//        if (r1 >= r2) {     //高度固定，计算宽度
//            realHeight = getHeight() - getPaddingTop() - getPaddingBottom();
//            realWidth = (int) (realHeight / r1);
//        } else {        //宽度固定，计算高度
//            realWidth = getWidth() - getPaddingLeft() - getPaddingRight();
//            realHeight = (int) (r2 * realWidth);
//        }

        realWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        realHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        ArrayList<RacksBean.Rack> rackBeans = bean.getRacks();
        areaList.clear();
        rackIds.clear();
        for (int i = 0; i < rackBeans.size(); i++) {
            ArrayList<PointF> points = new ArrayList<>();
            for (int j = 0; j < rackBeans.get(i).getPoints().size(); j++) {
                PointF pointF = rackBeans.get(i).getPoints().get(j);
                PointF e = new PointF(realWidth * pointF.x, realHeight * pointF.y);
                points.add(e);
            }
            points.add(points.get(0));
            rackIds.add(rackBeans.get(i).getRackId());
            areaList.add(points);
        }
        postInvalidate();
    }


    PointF clickPoint = new PointF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickPoint.x = event.getX();
            clickPoint.y = event.getY();
        }
        return super.onTouchEvent(event);
    }

    /**
     * 功能：判断点是否在多边形内 方法：求解通过该点的水平线与多边形各边的交点 结论：单边交点为奇数，成立!
     *
     * @param point   指定的某个点
     * @param APoints 多边形的各个顶点坐标（首末点可以不一致）
     * @return
     */
    public boolean clickPointInArea(PointF point, List<PointF> APoints) {
        int nCross = 0;
        for (int i = 0; i < APoints.size(); i++) {
            PointF p1 = APoints.get(i);
            PointF p2 = APoints.get((i + 1) % APoints.size());
            // 求解 y=p.y 与 p1p2 的交点  
            if (p1.y == p2.y) // p1p2 与 y=p0.y平行  
                continue;
            if (point.y < Math.min(p1.y, p2.y)) // 交点在p1p2延长线上  
                continue;
            if (point.y >= Math.max(p1.y, p2.y)) // 交点在p1p2延长线上  
                continue;
            // 求交点的 X 坐标  
            // --------------------------------------------------------------  
            double x = (double) (point.y - p1.y)
                    * (double) (p2.x - p1.x)
                    / (double) (p2.y - p1.y) + p1.x;
            if (x > point.x)
                nCross++; // 只统计单边交点  
        }
        // 单边交点为偶数，点在多边形之外 ---
        return (nCross % 2 == 1);
    }

    /**
     * 闸机
     */
    public boolean inGateArea(PointF clickPoint, List<PointF> points) {
        float minX = 10000;
        float maxX = 0;
        float minY = 10000;
        float maxY = 0;
        for (PointF point : points) {
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }
        return clickPoint.x > minX && clickPoint.x < maxX && clickPoint.y > minY && clickPoint.y < maxY;
    }

    public ArrayList<ArrayList<PointF>> getAreaList() {
        return areaList;
    }

    public void setSelectedId(String selectedRackId) {
        this.selectedId = selectedRackId;
        postInvalidate();
    }

    public GoodsPosBean getGoodsPosBean() {
        return mGoodsPosBean;
    }

    public void setGoodsPosBean(GoodsPosBean goodsPosBean) {
        mGoodsPosBean = goodsPosBean;
        showId = false;
        postInvalidate();
    }

    /**
     * 闸机坐标
     */
    public void setGatesBean(GatesBean gatesBean) {
        mGatesBean = gatesBean;
        gatesAreaList.clear();
        gateIds.clear();
        gateStates.clear();
        if (mGatesBean == null || mGatesBean.gates == null) {
            postInvalidate();
            return;
        }
        for (int i = 0; i < gatesBean.gates.size(); i++) {
            ArrayList<PointF> list = new ArrayList<>();
            GatesBean.GateBean gate = gatesBean.gates.get(i);
            double x1 = gate.regionA.x;
            double y1 = gate.regionA.y;
            double x2 = gate.regionB.x;
            double y2 = gate.regionB.y;
            double x3 = gate.regionC.x;
            double y3 = gate.regionC.y;
            double x4 = gate.regionD.x;
            double y4 = gate.regionD.y;
            list.add(new PointF((float) x1 * realWidth, (float) y1 * realHeight));
            list.add(new PointF((float) x2 * realWidth, (float) y2 * realHeight));
            list.add(new PointF((float) x3 * realWidth, (float) y3 * realHeight));
            list.add(new PointF((float) x4 * realWidth, (float) y4 * realHeight));
            list.add(new PointF((float) x1 * realWidth, (float) y1 * realHeight));
            gatesAreaList.add(list);
            gateIds.add(gate.gateId);
            gateStates.add(false);
        }
        postInvalidate();
    }

    public String getClickRackId() {
        for (int i = 0; i < areaList.size(); i++) {
            if (clickPointInArea(clickPoint, areaList.get(i))) {
                return rackIds.get(i);
            }
        }
        return null;
    }

    public String getClickGateId() {
        for (int i = 0; i < gatesAreaList.size(); i++) {
            if (inGateArea(clickPoint, gatesAreaList.get(i))) {
                return gateIds.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> getGateIds() {
        return gateIds;
    }

    public void setGateIds(ArrayList<String> gateIds) {
        this.gateIds = gateIds;
    }

    public ArrayList<Boolean> getGateStates() {
        return gateStates;
    }

    public void setGateStates(ArrayList<Boolean> gateStates) {
        this.gateStates = gateStates;
    }

    /**
     * 是否显示货架id
     */
    public boolean isShowId() {
        return showId;
    }

    /**
     * 设置是否显示货架id
     */
    public void setShowId(boolean showId) {
        this.showId = showId;
        if (showId)
            mGoodsPosBean = null;
        postInvalidate();
    }

    //计算textSize
    public void measureTextSize() {
        //计算最小宽度
        int minWidth = 0;
        if (areaList != null) {
            for (ArrayList<PointF> list : areaList) {
                int left = 10000;
                int right = 0;
                for (PointF pointF : list) {
                    if (pointF.x < left)
                        left = (int) pointF.x;
                    else if (pointF.x > right)
                        right = (int) pointF.x;
                }
                int width = right - left;
                if (minWidth == 0 || width < minWidth) {
                    minWidth = width;
                }
            }

        }
        //减去选中的时候外层的边框
        minWidth -= Tools.dp2px(getContext(), 4);
        if (minWidth < 0)
            minWidth = 0;
        String longestId = "1";
        for (String rackId : rackIds) {
            if (rackId.length() > longestId.length()) {
                longestId = rackId;
            }
        }

        float textSize = Tools.sp2px(getContext(), 14);
        textPaint.setTextSize(textSize);
        while (textSize > 0 && (textPaint.measureText(longestId) > minWidth)) {
            textSize -= 1;
            textPaint.setTextSize(textSize);
        }
    }

    public void reInit() {
        selectedId = "none";
        areaList.clear();
        rackIds.clear();
        mGoodsPosBean = null;
        mGatesBean = null;
        areaList.clear();
        gatesAreaList.clear();
        gateIds.clear();
        gateStates.clear();
        setShowId(false);
        postInvalidate();
    }
}