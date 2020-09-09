package com.cloudpick.yunnasdk.tools.rackview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cloudpick.yunnasdk.R;
import com.cloudpick.yunnasdk.entity.GoodsActivityBean;
import com.cloudpick.yunnasdk.entity.GoodsInfoBean;
import com.cloudpick.yunnasdk.entity.GoodsPosBean;
import com.cloudpick.yunnasdk.entity.RackInfoBean;
import com.cloudpick.yunnasdk.entity.SensorBean;
import com.cloudpick.yunnasdk.entity.ShelfBean;
import com.cloudpick.yunnasdk.entity.TemperatureBean;
import com.cloudpick.yunnasdk.utils.ImageLoaderWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Created by Administrator on 2018/4/23.
 */

public class RackView extends FrameLayout {
    //货架信息
    public RackInfoBean rackInfo;
    //货架每行的集合
    public ArrayList<ShelfBean> shelfList;
    private float heightScale;
    private float widthScale;
    private String selectedLaneId = "";
    //是否显示价格
    boolean showPrice;

    private Map<String, List<TemperatureBean>> sensorRecordMap = null;
    private List<SensorBean> sensorList = null;

    private List<View> listViews = new ArrayList<>();

    //商品列表->商品位置过来的时候
    GoodsPosBean mGoodsPosBean;
    //    录入模式--批量设置默认商品
    boolean enteringDefaultGoods;
    public String enteringMode;
    public GoodsActivityBean mGoodsActivityBean = new GoodsActivityBean();
    //手机扫码
    public static final String ENTERING_MODE_MOBILE = "by_mobile";
    //扫码枪
    public static final String ENTERING_MODE_GUN = "by_sao_ma_qiang";
    //    private ChangeDefGoodsDialog changDefGoodsDialog;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ArrayList<GoodsInfoBean> list, String defaultGoodsId, String laneId, GoodsActivityBean goodsActivityBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RackView(@NonNull Context context) {
        super(context);
    }

    public RackView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(RackInfoBean rackInfo, ArrayList<ShelfBean> shelfList, GoodsPosBean goodsPosBean, GoodsActivityBean goodsActivityBean) {
        this.mGoodsActivityBean = goodsActivityBean;
        this.rackInfo = rackInfo;
        this.shelfList = shelfList;
        this.mGoodsPosBean = goodsPosBean;
        removeAllViews();
        //最终比例
        heightScale = 1.0f * getHeight() / rackInfo.getHeight();
        widthScale = 1.0f * getWidth() / rackInfo.getWidth();
        //添加行  view的index从上到下要倒叙
        listViews.clear();
        for (int i = shelfList.size() - 1; i >= 0; i--) {
            shelfList.get(i).getStartHeight();
            final List<ShelfBean.LaneModelListBean> laneModelList = shelfList.get(i).getLaneModelList();
            if (laneModelList == null || laneModelList.size() == 0) return;
            SlideLayout sensorLayout = (SlideLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_sensor_item, null, false);
            LayoutParams sensorLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (heightScale * laneModelList.get(0).getHeight()));
            if (sensorLayoutParams.height > Tools.dp2px(getContext(), 80)) {
                sensorLayoutParams.height = Tools.dp2px(getContext(), 80);
            }
            sensorLayoutParams.topMargin = (int) ((getHeight()
                    - shelfList.get(i).getStartHeight() * heightScale
                    - sensorLayoutParams.height));
            sensorLayout.setLayoutParams(sensorLayoutParams);
            sensorLayout.setBackgroundColor(getContext().getResources().getColor(R.color.c_a0ff));
            sensorLayout.openMenu();
            addView(sensorLayout);
            LinearLayout parent = sensorLayout.findViewById(R.id.menu);
            for (int j = 0; j < laneModelList.size(); j++) {
                View lanModelView = LayoutInflater.from(getContext()).inflate(R.layout.layout_goods_item, null, false);
//                addView(lanModelView);
                parent.addView(lanModelView);
                listViews.add(lanModelView);


                //格子的参数
                LinearLayout.LayoutParams lanModel_layoutParams = (LinearLayout.LayoutParams) lanModelView.getLayoutParams();

                //-----宽度均分
                //todo 宽度等比分配
//                lanModel_layoutParams.width = getWidth() / laneModelList.size();
                lanModel_layoutParams.width = (getWidth() - Tools.dp2px(getContext(), 5)) * laneModelList.get(j).getWidth() / shelfList.get(i).getShelfWidth();
                lanModel_layoutParams.height = (int) (heightScale * laneModelList.get(j).getHeight());
                if (lanModel_layoutParams.height > Tools.dp2px(getContext(), 80)) {
                    lanModel_layoutParams.height = Tools.dp2px(getContext(), 80);
                }
                //-----宽度均分
//                lanModel_layoutParams.leftMargin = getWidth() / laneModelList.size() * j;
                //计算上边距
//                lanModel_layoutParams.topMargin = (int) ((getHeight()
//                        - shelfList.get(i).getStartHeight() * heightScale
//                        - lanModel_layoutParams.height)-(100*i*heightScale));
                lanModelView.setLayoutParams(lanModel_layoutParams);
                if (this.selectedLaneId.equals(laneModelList.get(j).getLaneId())) {
                    lanModelView.findViewById(R.id.ll_goods_stroke).setSelected(true);
                } else {
                    lanModelView.findViewById(R.id.ll_goods_stroke).setSelected(false);
                }
                //只要有非默认、数量大于0商品，就变红  |  包含要查找的商品栏位，也变红
                if (laneModelList.get(j).getGoodsList() != null) {
                    for (GoodsInfoBean goods : laneModelList.get(j).getGoodsList()) {
                        if (!goods.getGoodsId().equals(laneModelList.get(j).getDefaultGoodsId())
                                && goods.getGoodsNum() > 0) {
                            lanModelView.findViewById(R.id.ll_goods_inner).setBackgroundColor(getResources().getColor(R.color.colorRed));
                            break;
                        }
                    }
                }
                //查找的商品绿色
                if (mGoodsPosBean != null) {
                    List<GoodsPosBean.LaneInfosBean> laneInfos = mGoodsPosBean.getLaneInfos();
                    if (laneInfos == null || laneInfos.size() == 0)
                        return;
                    for (GoodsPosBean.LaneInfosBean laneInfo : laneInfos) {
                        if (laneInfo.getRackId().equals(rackInfo.getRackId()) && laneInfo.getLaneId().equals(laneModelList.get(j).getLaneId()))
                            lanModelView.findViewById(R.id.ll_goods_inner).setBackgroundColor(0XFFCDCD00);
                    }
                }

                ImageView iv = lanModelView.findViewById(R.id.iv);
                TextView tvCount = lanModelView.findViewById(R.id.tv_count);
                View ivIcon = lanModelView.findViewById(R.id.ivIcon);
                final ArrayList<GoodsInfoBean> goodsList = laneModelList.get(j).getGoodsList();
                if (goodsList != null && goodsList.size() > 0) {
                    String defaultGoodsId = laneModelList.get(j).getDefaultGoodsId();
                    List<GoodsActivityBean.GoodsActivity> activities = mGoodsActivityBean.getMap().get(defaultGoodsId);
                    if (activities != null && activities.size() > 0) {
                        ivIcon.setVisibility(View.VISIBLE);
                    } else {
                        ivIcon.setVisibility(View.GONE);
                    }
                    //图片和价格都是默认商品的~
                    String defGoodsPrice = laneModelList.get(j).getDefGoodsPrice();
                    if (defGoodsPrice == null)
                        defGoodsPrice = "";
                    String s = "X" + goodsList.get(0).getGoodsNum() +
                            (showPrice ? "\n" + defGoodsPrice : "");
                    tvCount.setText(s);
                } else {
                    try {
                        //图片和价格都是默认商品的~     数量为0
                        String defGoodsPrice = laneModelList.get(j).getDefGoodsPrice();
                        if (defGoodsPrice == null) {
                            defGoodsPrice = "";
                        }
                        String s = "X0" +
                                (showPrice ? "\n"
                                        + defGoodsPrice : "");
                        tvCount.setText(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                ImageLoaderWrapper.displayImageByGoodsId(getContext(), iv, laneModelList.get(j).getDefaultGoodsId());

                lanModelView.setTag(laneModelList.get(j));
                final int finalJ = j;
                final View container = lanModelView;
                if (j == 0 && i == shelfList.size() - 1) {
                    changeSelected(container);
                }
                container.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        container.setClickable(false);
                        container.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (container != null)
                                    container.setClickable(true);
                            }
                        }, 500);
                        changeSelected(v);
                        selectedLaneId = laneModelList.get(finalJ).getLaneId();
//                        if (!enteringDefaultGoods) {
                        if (!Authority.check(Authority.EditGoods)) {
                            Tools.ToastMessage(getContext(), Authority.HAS_NO_AUTHORITY);
                            return;
                        }
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(goodsList
                                    , laneModelList.get(finalJ).getDefaultGoodsId(), selectedLaneId, mGoodsActivityBean);
                        } else {
                            Toast.makeText(getContext(), getContext().getString(R.string.yn_noclick), Toast.LENGTH_SHORT).show();
                        }
                        //todo 不可点击
//                            getContext().startActivity(LanModelDetailActivity.newIntent(getContext()
//                                    , goodsList
//                                    , laneModelList.get(finalJ).getDefaultGoodsId(), selectedLaneId, mGoodsActivityBean));
//                        } else if (enteringDefaultGoods && ENTERING_MODE_MOBILE.equals(this.enteringMode)) {
//                            if (!Authority.check(Authority.EditGoods)) {
//                                Tools.ToastMessage(getContext(), Authority.HAS_NO_AUTHORITY);
//                                return;
//                            }
//                        ((RackActivity) getContext()).toQrCode();跳转
//                        }
                    }
                });
            }

            if (laneModelList.size() > 0) {
                LinearLayout contentLayout = sensorLayout.findViewById(R.id.content);
                View tabMenu = sensorLayout.findViewById(R.id.tab_menu);
                //添加温度计
                if (sensorList != null) {
                    for (int n = 0; n < sensorList.size(); n++) {
                        String[] sensorsplits = sensorList.get(n).getSensorId().split("_");
                        if (shelfList.get(i).getShelfId().equals(sensorsplits[0])) {
                            sensorLayout.openScroll(true);
                            tabMenu.setVisibility(View.VISIBLE);
                            List<TemperatureBean> list = sensorRecordMap.get(sensorList.get(n).getSensorId());

                            LinearLayout sensorItem = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.sensor_item, contentLayout, false);
                            sensorItem.setTag(sensorsplits[1]);

                            ImageView imageView = sensorItem.findViewById(R.id.image);
                            TextView textMsg = sensorItem.findViewById(R.id.text);
                            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                            params.height = (int) ((heightScale * laneModelList.get(0).getHeight()) * 0.6);
                            params.weight = (int) ((heightScale * laneModelList.get(0).getHeight()) * 0.6);
                            params.topMargin = 20;
                            imageView.setLayoutParams(params);
                            imageView.setImageDrawable(getContext().getResources().getDrawable(SensorTypeEnum.getValue(sensorList.get(n).getSensorType())));

                            List<View> views = new ArrayList<>();
                            views.clear();
                            for (int index = 0; index < contentLayout.getChildCount(); index++) {
                                views.add(contentLayout.getChildAt(index));
                            }

                            boolean isAdd = false;
                            for (int index = 0; index < views.size(); index++) {
                                String tag = (String) views.get(index).getTag();
                                if (Integer.parseInt(sensorsplits[1]) < Integer.parseInt(tag)) {
                                    views.add(index, sensorItem);
                                    isAdd = true;
                                    break;
                                }
                            }
                            if (!isAdd) {
                                views.add(sensorItem);
                            }
                            contentLayout.removeAllViews();
                            for (int index = 0; index < views.size(); index++) {
                                contentLayout.addView(views.get(index));
                            }
                            SensorBean sensorBean = sensorList.get(n);
                            String name = SensorNameTypeEnum.getValue(getContext(), sensorList.get(n).getSensorType());
                            //zxy 温度传感器
                            imageView.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Intent intent = TemperatureSensorActivity.newIntent(getContext(),sensorBean.getStoreId(),sensorBean.getSensorId(),name,sensorBean);
//                                    getContext().startActivity(intent);

                                }
                            });
                            if (sensorList.get(n).getFinalStatus().equals("00")) {
                                imageView.setImageDrawable(getContext().getResources().getDrawable(SensorErrorTypeEnum.getValue(sensorList.get(n).getSensorType())));
                                textMsg.setText(getContext().getResources().getString(R.string.temperatrue_error));
                                textMsg.setTextColor(getContext().getResources().getColor(R.color.c_f52828));
                                tabMenu.setBackgroundColor(getContext().getResources().getColor(R.color.c_f52828));
                            }
                        }
                    }
                }
            }
            //每一行的横线
            View view = new View(getContext());
            addView(view);
            LayoutParams line_params = (LayoutParams) view.getLayoutParams();
            line_params.width = getWidth();
            line_params.height = Tools.dp2px(getContext(), 3);
            line_params.topMargin = (int) ((getHeight() - shelfList.get(i).getStartHeight() * heightScale));
            view.setLayoutParams(line_params);
            view.setBackgroundColor(Color.BLACK);
        }
    }

    public void setData(RackInfoBean rackInfo, ArrayList<ShelfBean> shelfList, GoodsPosBean goodsPosBean) {
        setData(rackInfo, shelfList, goodsPosBean, this.mGoodsActivityBean);
    }

    private void changeSelected(View v) {
//        for (int i = 0; i < getChildCount(); i++) {
//            if (getChildAt(i) instanceof LinearLayout)
//                try{
//                    getChildAt(i).findViewById(R.id.ll_goods_stroke).setSelected(false);
//                }catch (Exception e){
//
//                }
//        }
        for (int i = 0; i < listViews.size(); i++) {
            if (listViews.get(i) != null) {
                listViews.get(i).findViewById(R.id.ll_goods_stroke).setSelected(false);
            }
        }
        v.findViewById(R.id.ll_goods_stroke).setSelected(true);
        if (v.getTag() != null) {
            this.selectedLaneId = ((ShelfBean.LaneModelListBean) v.getTag()).getLaneId();
        }
    }

    /**
     * 更改默认商品和显示的图片
     */
    public void changeDefGoods(String laneId, String goodsId) {
        for (int i = 0; i < listViews.size(); i++) {
            View laneView = listViews.get(i);
            if (laneView instanceof LinearLayout) {
                ShelfBean.LaneModelListBean laneBean = (ShelfBean.LaneModelListBean) laneView.getTag();
                if (laneBean != null && laneBean.getLaneId().equals(laneId)) {
                    laneBean.setDefaultGoodsId(goodsId);
                    ImageView iv = laneView.findViewById(R.id.iv);
                    ImageLoaderWrapper.displayImageByGoodsId(getContext(), iv, goodsId);
                }
            }
        }
    }

    public void filterRepeat() {
        ArrayList<String> repeatGoods = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < listViews.size(); i++) {
            if (listViews.get(i).getTag() != null && listViews.get(i).getTag() instanceof ShelfBean.LaneModelListBean) {
                //去掉所有颜色
                listViews.get(i).findViewById(R.id.ll_goods_inner)
                        .setBackgroundColor(getResources().getColor(R.color.colorTransparent));
                String goodsId = ((ShelfBean.LaneModelListBean) listViews.get(i).getTag()).getDefaultGoodsId();
                if (builder.indexOf("," + goodsId + ",") > -1) {
                    repeatGoods.add(goodsId);
                } else {
                    builder.append(",").append(goodsId).append(",");
                }
            }
        }
        Random random = new Random();
        for (String repeatGood : repeatGoods) {
            if (repeatGood == null) continue;
            int color = Color.rgb(random.nextInt(155), random.nextInt(255), random.nextInt(255));
            for (int i = 0; i < listViews.size(); i++) {
                if (listViews.get(i).getTag() != null && listViews.get(i).getTag() instanceof ShelfBean.LaneModelListBean) {
                    String goodsId = ((ShelfBean.LaneModelListBean) listViews.get(i).getTag()).getDefaultGoodsId();
                    if (goodsId == null) continue;
                    if (repeatGood.equals(goodsId)) {
                        listViews.get(i).findViewById(R.id.ll_goods_inner).setBackgroundColor(color);
                    }
                }
            }
        }
    }

    public void filterCount(int num) {
        for (int i = 0; i < listViews.size(); i++) {
            View view = listViews.get(i);
            if (view.getTag() instanceof ShelfBean.LaneModelListBean
                    && ((ShelfBean.LaneModelListBean) view.getTag()).getGoodsList().size() > 0
                    && ((ShelfBean.LaneModelListBean) view.getTag()).getGoodsList().get(0).getGoodsNum() < num) {
                view.findViewById(R.id.ll_goods_inner)
                        .setBackgroundColor(getResources().getColor(R.color.colorTransparentGrey));
            } else {
                view.findViewById(R.id.ll_goods_inner)
                        .setBackgroundColor(getResources().getColor(R.color.colorTransparent));
            }

        }
    }

    /**
     * 选中下一个栏位
     */
    public void moveToNext(final Activity mActivity) {
        for (int i = 0; i < listViews.size(); i++) {
            View view = listViews.get(i).findViewById(R.id.ll_goods_stroke);
            if (view.isSelected()) {
                if (i + 1 < listViews.size()) {
                    changeSelected(listViews.get(i + 1));//默认下一个view
                    final int index = i + 1;
                    new Handler(mActivity.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listViews.get(index).performClick();
                                }
                            });
                        }
                    }, 500);

                    return;
                }
            }
        }
    }

    public boolean getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(boolean showPrice) {
        this.showPrice = showPrice;
    }

    public GoodsPosBean getGoodsPosBean() {
        return mGoodsPosBean;
    }

    public void setGoodsPosBean(GoodsPosBean goodsPosBean) {
        mGoodsPosBean = goodsPosBean;
    }

    public boolean isEnteringDefaultGoods() {
        return enteringDefaultGoods;
    }

    public String getSelectedLaneId() {
        return selectedLaneId;
    }

    public void setSelectedLaneId(String selectedLaneId) {
        this.selectedLaneId = selectedLaneId;
    }

    public void setEnteringDefaultGoods(boolean enteringDefaultGoods, String mode) {
        this.enteringDefaultGoods = enteringDefaultGoods;
        this.enteringMode = mode;
    }

    public void setSensorData(List<SensorBean> sensorList, Map<String, List<TemperatureBean>> sensorRecordMap) {
        this.sensorList = sensorList;
        this.sensorRecordMap = sensorRecordMap;
        setData(this.rackInfo, this.shelfList, this.mGoodsPosBean, this.mGoodsActivityBean);

    }
}
