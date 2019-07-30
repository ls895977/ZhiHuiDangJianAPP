package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean;

public class QueryDeptDetailBean {

    /**
     * code : 0
     * data : {"partyDynamic":{"author":"dsfa","beginCreateDt":null,"comment":"<p>dsfsafa<\/p>","createCode":"1002","createName":"赵真鹏","createTime":1559553756000,"dynamicSource":"dsfafs","endCreateDt":null,"groupByClause":null,"idList":null,"ifPass":0,"ifTop":1,"isDeleteText":null,"isEnableText":null,"orderByClause":null,"pageNumber":1,"pageSize":10,"partyDynamicId":7,"partyDynamicType":0,"releaseDate":"2019-05-29","remark":null,"startIndex":0,"thumbnailUrl":"Path/20190529/01c87c97-3f1d-407b-bdb0-9bf26f03cbc7.jpeg","title":"fsdf","topNum":null,"urlList":null}}
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
         * partyDynamic : {"author":"dsfa","beginCreateDt":null,"comment":"<p>dsfsafa<\/p>","createCode":"1002","createName":"赵真鹏","createTime":1559553756000,"dynamicSource":"dsfafs","endCreateDt":null,"groupByClause":null,"idList":null,"ifPass":0,"ifTop":1,"isDeleteText":null,"isEnableText":null,"orderByClause":null,"pageNumber":1,"pageSize":10,"partyDynamicId":7,"partyDynamicType":0,"releaseDate":"2019-05-29","remark":null,"startIndex":0,"thumbnailUrl":"Path/20190529/01c87c97-3f1d-407b-bdb0-9bf26f03cbc7.jpeg","title":"fsdf","topNum":null,"urlList":null}
         */

        private PartyDynamicBean partyDynamic;

        public PartyDynamicBean getPartyDynamic() {
            return partyDynamic;
        }

        public void setPartyDynamic(PartyDynamicBean partyDynamic) {
            this.partyDynamic = partyDynamic;
        }

        public static class PartyDynamicBean {
            /**
             * author : dsfa
             * beginCreateDt : null
             * comment : <p>dsfsafa</p>
             * createCode : 1002
             * createName : 赵真鹏
             * createTime : 1559553756000
             * dynamicSource : dsfafs
             * endCreateDt : null
             * groupByClause : null
             * idList : null
             * ifPass : 0
             * ifTop : 1
             * isDeleteText : null
             * isEnableText : null
             * orderByClause : null
             * pageNumber : 1
             * pageSize : 10
             * partyDynamicId : 7
             * partyDynamicType : 0
             * releaseDate : 2019-05-29
             * remark : null
             * startIndex : 0
             * thumbnailUrl : Path/20190529/01c87c97-3f1d-407b-bdb0-9bf26f03cbc7.jpeg
             * title : fsdf
             * topNum : null
             * urlList : null
             */

            private String author;
            private Object beginCreateDt;
            private String comment;
            private String createCode;
            private String createName;
            private long createTime;
            private String dynamicSource;
            private Object endCreateDt;
            private Object groupByClause;
            private Object idList;
            private int ifPass;
            private int ifTop;
            private Object isDeleteText;
            private Object isEnableText;
            private Object orderByClause;
            private int pageNumber;
            private int pageSize;
            private int partyDynamicId;
            private int partyDynamicType;
            private String releaseDate;
            private Object remark;
            private int startIndex;
            private String thumbnailUrl;
            private String title;
            private Object topNum;
            private Object urlList;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public Object getBeginCreateDt() {
                return beginCreateDt;
            }

            public void setBeginCreateDt(Object beginCreateDt) {
                this.beginCreateDt = beginCreateDt;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
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

            public String getDynamicSource() {
                return dynamicSource;
            }

            public void setDynamicSource(String dynamicSource) {
                this.dynamicSource = dynamicSource;
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

            public Object getIdList() {
                return idList;
            }

            public void setIdList(Object idList) {
                this.idList = idList;
            }

            public int getIfPass() {
                return ifPass;
            }

            public void setIfPass(int ifPass) {
                this.ifPass = ifPass;
            }

            public int getIfTop() {
                return ifTop;
            }

            public void setIfTop(int ifTop) {
                this.ifTop = ifTop;
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

            public int getPartyDynamicId() {
                return partyDynamicId;
            }

            public void setPartyDynamicId(int partyDynamicId) {
                this.partyDynamicId = partyDynamicId;
            }

            public int getPartyDynamicType() {
                return partyDynamicType;
            }

            public void setPartyDynamicType(int partyDynamicType) {
                this.partyDynamicType = partyDynamicType;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
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

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getTopNum() {
                return topNum;
            }

            public void setTopNum(Object topNum) {
                this.topNum = topNum;
            }

            public Object getUrlList() {
                return urlList;
            }

            public void setUrlList(Object urlList) {
                this.urlList = urlList;
            }
        }
    }
}
