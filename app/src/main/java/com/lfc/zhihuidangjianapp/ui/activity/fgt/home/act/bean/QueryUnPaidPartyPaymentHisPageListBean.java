package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean;

import java.util.List;

public class QueryUnPaidPartyPaymentHisPageListBean {

    /**
     * code : 0
     * data : {"money":42,"unPaidPartyPaymentHisList":[{"agent":"","beginCreateDt":null,"betweenAnd":null,"createCode":"1002","createName":"赵真鹏","createTime":1562920830000,"deptCode":"10011","deptCodeList":null,"deptName":"中共山西涑水河源头省级自然保护区管理局支部委员会","endCreateDt":null,"groupByClause":null,"idStr":null,"ifPay":1,"ifSubstitute":1,"isDeleteText":null,"isEnableText":null,"money":42,"name":"张继红","orderByClause":null,"pageNumber":1,"pageSize":10,"partyPaymentHisId":30,"payDate":"","payStatus":1,"payYear":"2019","payYearMonth":"2019-06","remark":null,"startIndex":0,"userCode":"1003","userCodeList":null}]}
     * msg : 请求成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * money : 42
         * unPaidPartyPaymentHisList : [{"agent":"","beginCreateDt":null,"betweenAnd":null,"createCode":"1002","createName":"赵真鹏","createTime":1562920830000,"deptCode":"10011","deptCodeList":null,"deptName":"中共山西涑水河源头省级自然保护区管理局支部委员会","endCreateDt":null,"groupByClause":null,"idStr":null,"ifPay":1,"ifSubstitute":1,"isDeleteText":null,"isEnableText":null,"money":42,"name":"张继红","orderByClause":null,"pageNumber":1,"pageSize":10,"partyPaymentHisId":30,"payDate":"","payStatus":1,"payYear":"2019","payYearMonth":"2019-06","remark":null,"startIndex":0,"userCode":"1003","userCodeList":null}]
         */

        private int money;
        private List<UnPaidPartyPaymentHisListBean> unPaidPartyPaymentHisList;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public List<UnPaidPartyPaymentHisListBean> getUnPaidPartyPaymentHisList() {
            return unPaidPartyPaymentHisList;
        }

        public void setUnPaidPartyPaymentHisList(List<UnPaidPartyPaymentHisListBean> unPaidPartyPaymentHisList) {
            this.unPaidPartyPaymentHisList = unPaidPartyPaymentHisList;
        }

        public static class UnPaidPartyPaymentHisListBean {
            /**
             * agent :
             * beginCreateDt : null
             * betweenAnd : null
             * createCode : 1002
             * createName : 赵真鹏
             * createTime : 1562920830000
             * deptCode : 10011
             * deptCodeList : null
             * deptName : 中共山西涑水河源头省级自然保护区管理局支部委员会
             * endCreateDt : null
             * groupByClause : null
             * idStr : null
             * ifPay : 1
             * ifSubstitute : 1
             * isDeleteText : null
             * isEnableText : null
             * money : 42
             * name : 张继红
             * orderByClause : null
             * pageNumber : 1
             * pageSize : 10
             * partyPaymentHisId : 30
             * payDate :
             * payStatus : 1
             * payYear : 2019
             * payYearMonth : 2019-06
             * remark : null
             * startIndex : 0
             * userCode : 1003
             * userCodeList : null
             */

            private String agent;
            private Object beginCreateDt;
            private Object betweenAnd;
            private String createCode;
            private String createName;
            private long createTime;
            private String deptCode;
            private Object deptCodeList;
            private String deptName;
            private Object endCreateDt;
            private Object groupByClause;
            private Object idStr;
            private int ifPay;
            private int ifSubstitute;
            private Object isDeleteText;
            private Object isEnableText;
            private int money;
            private String name;
            private Object orderByClause;
            private int pageNumber;
            private int pageSize;
            private int partyPaymentHisId;
            private String payDate;
            private int payStatus;
            private String payYear;
            private String payYearMonth;
            private Object remark;
            private int startIndex;
            private String userCode;
            private Object userCodeList;

            public String getAgent() {
                return agent;
            }

            public void setAgent(String agent) {
                this.agent = agent;
            }

            public Object getBeginCreateDt() {
                return beginCreateDt;
            }

            public void setBeginCreateDt(Object beginCreateDt) {
                this.beginCreateDt = beginCreateDt;
            }

            public Object getBetweenAnd() {
                return betweenAnd;
            }

            public void setBetweenAnd(Object betweenAnd) {
                this.betweenAnd = betweenAnd;
            }

            public String getCreateCode() {
                return createCode;
            }

            public void setCreateCode(String createCode) {
                this.createCode = createCode;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getDeptCode() {
                return deptCode;
            }

            public void setDeptCode(String deptCode) {
                this.deptCode = deptCode;
            }

            public Object getDeptCodeList() {
                return deptCodeList;
            }

            public void setDeptCodeList(Object deptCodeList) {
                this.deptCodeList = deptCodeList;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public Object getEndCreateDt() {
                return endCreateDt;
            }

            public void setEndCreateDt(Object endCreateDt) {
                this.endCreateDt = endCreateDt;
            }

            public Object getGroupByClause() {
                return groupByClause;
            }

            public void setGroupByClause(Object groupByClause) {
                this.groupByClause = groupByClause;
            }

            public Object getIdStr() {
                return idStr;
            }

            public void setIdStr(Object idStr) {
                this.idStr = idStr;
            }

            public int getIfPay() {
                return ifPay;
            }

            public void setIfPay(int ifPay) {
                this.ifPay = ifPay;
            }

            public int getIfSubstitute() {
                return ifSubstitute;
            }

            public void setIfSubstitute(int ifSubstitute) {
                this.ifSubstitute = ifSubstitute;
            }

            public Object getIsDeleteText() {
                return isDeleteText;
            }

            public void setIsDeleteText(Object isDeleteText) {
                this.isDeleteText = isDeleteText;
            }

            public Object getIsEnableText() {
                return isEnableText;
            }

            public void setIsEnableText(Object isEnableText) {
                this.isEnableText = isEnableText;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getOrderByClause() {
                return orderByClause;
            }

            public void setOrderByClause(Object orderByClause) {
                this.orderByClause = orderByClause;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPartyPaymentHisId() {
                return partyPaymentHisId;
            }

            public void setPartyPaymentHisId(int partyPaymentHisId) {
                this.partyPaymentHisId = partyPaymentHisId;
            }

            public String getPayDate() {
                return payDate;
            }

            public void setPayDate(String payDate) {
                this.payDate = payDate;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public String getPayYear() {
                return payYear;
            }

            public void setPayYear(String payYear) {
                this.payYear = payYear;
            }

            public String getPayYearMonth() {
                return payYearMonth;
            }

            public void setPayYearMonth(String payYearMonth) {
                this.payYearMonth = payYearMonth;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getStartIndex() {
                return startIndex;
            }

            public void setStartIndex(int startIndex) {
                this.startIndex = startIndex;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public Object getUserCodeList() {
                return userCodeList;
            }

            public void setUserCodeList(Object userCodeList) {
                this.userCodeList = userCodeList;
            }
        }
    }
}
