package com.cloudpick.yunnasdk.entity;

import java.util.List;

public class UserCardsBean {

    /**
     * code : 0000
     * data : [{"cardId":"181030111MC000002","cardName":"充值卡","cardDesc":"充值卡","cardType":"DISCOUNT_CARD"}]
     */

    private String code;
    private List<DataBean> data;
    private String message;

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
         * cardId : 181030111MC000002
         * cardName : 充值卡
         * cardDesc : 充值卡
         * cardType : DISCOUNT_CARD
         */

        private String cardId;
        private String cardName;
        private String cardDesc;
        private String cardType;
        private String scence;
        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardDesc() {
            return cardDesc;
        }

        public void setCardDesc(String cardDesc) {
            this.cardDesc = cardDesc;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getScence() {
            return scence;
        }

        public void setScence(String scence) {
            this.scence = scence;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
