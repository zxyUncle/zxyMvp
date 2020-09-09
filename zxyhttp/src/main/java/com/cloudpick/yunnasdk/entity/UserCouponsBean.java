package com.cloudpick.yunnasdk.entity;

import android.text.TextUtils;

import com.cloudpick.yunnasdk.R;

import java.util.List;


/**
 * Created by admin on 2018/5/3.
 */

public class UserCouponsBean {

    /**
     * code : 0000
     * data : [{"couponType":"CASH_DISCOUNT","couponDesc":"无使用门槛","amount":"1.00","fromDate":"2018-05-02 18:00:00","toDate":"2018-06-01 23:59:59","scence":"1","couponName":"现金抵用券"},{"couponType":"REACH_10_DISCOUNT","couponDesc":"满十元可使用","amount":"2.00","fromDate":"2018-05-02 18:00:00","toDate":"2018-06-01 23:59:59","scence":"3","couponName":"满减券"}]
     */

    private String code;
    private List<DataBean> data;
    private String message;
    //        1 ： green  2:red 3:yellow  4:blue
    public static KeyValuePairs<String, Integer> scences = new KeyValuePairs<>(
            new String[]{"1", "2", "3", "4"},
            new Integer[]{R.drawable.coupon_green, R.drawable.coupon_red, R.drawable.coupon_orange, R.drawable.coupon_blue}
    );

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * couponType : CASH_DISCOUNT
         * couponDesc : 无使用门槛
         * amount : 1.00
         * fromDate : 2018-05-02 18:00:00
         * toDate : 2018-06-01 23:59:59
         * scence : 1
         * couponName : 现金抵用券
         */

        private String couponType;
        private String couponDesc;
        private String amount;
        private String fromDate;
        private String toDate;
        private String scence;
        private String couponName;
        private String showAmount;

        public String getShowAmount() {
            if (TextUtils.isEmpty(showAmount))
                return "";
            return showAmount;
        }

        public void setShowAmount(String showAmount) {
            this.showAmount = showAmount;
        }

        public String getCouponType() {
            return couponType;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public String getCouponDesc() {
            if (TextUtils.isEmpty(couponDesc))
                return "";
            return couponDesc;
        }

        public void setCouponDesc(String couponDesc) {
            this.couponDesc = couponDesc;
        }

        public String getAmount() {
            if (TextUtils.isEmpty(amount))
                return "";
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getFromDate() {
            if (TextUtils.isEmpty(fromDate))
                return "";
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            if (TextUtils.isEmpty(toDate))
                return "";
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public String getScence() {
            if (TextUtils.isEmpty(scence))
                return "1";
            return scence;
        }

        public void setScence(String scence) {
            this.scence = scence;
        }

        public String getCouponName() {
            if (TextUtils.isEmpty(couponName))
                return "";
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
