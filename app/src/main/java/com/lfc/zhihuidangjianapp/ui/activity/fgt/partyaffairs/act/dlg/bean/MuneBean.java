package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.bean;

import java.util.List;

public class MuneBean {

    /**
     * code : 0
     * msg : null
     * data : [{"value":"民主生活会","key":0},{"value":"组织生活会","key":1},{"value":"党课","key":2},{"value":"主题党日","key":3},{"value":"民主评议党员","key":4},{"value":"其他","key":5}]
     */

    private int code;
    private Object msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * value : 民主生活会
         * key : 0
         */
        private boolean status;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        private String value;
        private int key;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }
}
