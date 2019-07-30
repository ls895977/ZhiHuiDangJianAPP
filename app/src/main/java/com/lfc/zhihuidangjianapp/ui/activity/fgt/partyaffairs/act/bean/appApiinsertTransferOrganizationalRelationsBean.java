package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean;

public class appApiinsertTransferOrganizationalRelationsBean {

    /**
     * code : 0
     * msg : null
     * data : {"transferOrganizationalRelations":{"startIndex":0,"pageNumber":1,"pageSize":10,"orderByClause":null,"groupByClause":null,"beginCreateDt":null,"endCreateDt":null,"isEnableText":null,"isDeleteText":null,"transferOrganizationalRelationsId":3,"name":"赵真鹏","userCode":"1002","phone":null,"nowDept":"中共山西涑水河源头省级自然保护区管理局支部委员会","nowDeptCode":"10011","applyDeptName":"060c2cf2828945e49e49b89808574fc6","transferReason":"在在在在在在在","status":1,"approvalOpinion":"","approvalTime":null,"createTime":1564502826000,"createCode":"1002","createName":"赵真鹏","remark":null}}
     */

    private int code;
    private Object msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * transferOrganizationalRelations : {"startIndex":0,"pageNumber":1,"pageSize":10,"orderByClause":null,"groupByClause":null,"beginCreateDt":null,"endCreateDt":null,"isEnableText":null,"isDeleteText":null,"transferOrganizationalRelationsId":3,"name":"赵真鹏","userCode":"1002","phone":null,"nowDept":"中共山西涑水河源头省级自然保护区管理局支部委员会","nowDeptCode":"10011","applyDeptName":"060c2cf2828945e49e49b89808574fc6","transferReason":"在在在在在在在","status":1,"approvalOpinion":"","approvalTime":null,"createTime":1564502826000,"createCode":"1002","createName":"赵真鹏","remark":null}
         */

        private TransferOrganizationalRelationsBean transferOrganizationalRelations;

        public TransferOrganizationalRelationsBean getTransferOrganizationalRelations() {
            return transferOrganizationalRelations;
        }

        public void setTransferOrganizationalRelations(TransferOrganizationalRelationsBean transferOrganizationalRelations) {
            this.transferOrganizationalRelations = transferOrganizationalRelations;
        }

        public static class TransferOrganizationalRelationsBean {
            /**
             * startIndex : 0
             * pageNumber : 1
             * pageSize : 10
             * orderByClause : null
             * groupByClause : null
             * beginCreateDt : null
             * endCreateDt : null
             * isEnableText : null
             * isDeleteText : null
             * transferOrganizationalRelationsId : 3
             * name : 赵真鹏
             * userCode : 1002
             * phone : null
             * nowDept : 中共山西涑水河源头省级自然保护区管理局支部委员会
             * nowDeptCode : 10011
             * applyDeptName : 060c2cf2828945e49e49b89808574fc6
             * transferReason : 在在在在在在在
             * status : 1
             * approvalOpinion :
             * approvalTime : null
             * createTime : 1564502826000
             * createCode : 1002
             * createName : 赵真鹏
             * remark : null
             */

            private int startIndex;
            private int pageNumber;
            private int pageSize;
            private Object orderByClause;
            private Object groupByClause;
            private Object beginCreateDt;
            private Object endCreateDt;
            private Object isEnableText;
            private Object isDeleteText;
            private int transferOrganizationalRelationsId;
            private String name;
            private String userCode;
            private Object phone;
            private String nowDept;
            private String nowDeptCode;
            private String applyDeptName;
            private String transferReason;
            private int status;
            private String approvalOpinion;
            private Object approvalTime;
            private long createTime;
            private String createCode;
            private String createName;
            private Object remark;

            public int getStartIndex() {
                return startIndex;
            }

            public void setStartIndex(int startIndex) {
                this.startIndex = startIndex;
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

            public Object getOrderByClause() {
                return orderByClause;
            }

            public void setOrderByClause(Object orderByClause) {
                this.orderByClause = orderByClause;
            }

            public Object getGroupByClause() {
                return groupByClause;
            }

            public void setGroupByClause(Object groupByClause) {
                this.groupByClause = groupByClause;
            }

            public Object getBeginCreateDt() {
                return beginCreateDt;
            }

            public void setBeginCreateDt(Object beginCreateDt) {
                this.beginCreateDt = beginCreateDt;
            }

            public Object getEndCreateDt() {
                return endCreateDt;
            }

            public void setEndCreateDt(Object endCreateDt) {
                this.endCreateDt = endCreateDt;
            }

            public Object getIsEnableText() {
                return isEnableText;
            }

            public void setIsEnableText(Object isEnableText) {
                this.isEnableText = isEnableText;
            }

            public Object getIsDeleteText() {
                return isDeleteText;
            }

            public void setIsDeleteText(Object isDeleteText) {
                this.isDeleteText = isDeleteText;
            }

            public int getTransferOrganizationalRelationsId() {
                return transferOrganizationalRelationsId;
            }

            public void setTransferOrganizationalRelationsId(int transferOrganizationalRelationsId) {
                this.transferOrganizationalRelationsId = transferOrganizationalRelationsId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getNowDept() {
                return nowDept;
            }

            public void setNowDept(String nowDept) {
                this.nowDept = nowDept;
            }

            public String getNowDeptCode() {
                return nowDeptCode;
            }

            public void setNowDeptCode(String nowDeptCode) {
                this.nowDeptCode = nowDeptCode;
            }

            public String getApplyDeptName() {
                return applyDeptName;
            }

            public void setApplyDeptName(String applyDeptName) {
                this.applyDeptName = applyDeptName;
            }

            public String getTransferReason() {
                return transferReason;
            }

            public void setTransferReason(String transferReason) {
                this.transferReason = transferReason;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getApprovalOpinion() {
                return approvalOpinion;
            }

            public void setApprovalOpinion(String approvalOpinion) {
                this.approvalOpinion = approvalOpinion;
            }

            public Object getApprovalTime() {
                return approvalTime;
            }

            public void setApprovalTime(Object approvalTime) {
                this.approvalTime = approvalTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }
        }
    }
}
