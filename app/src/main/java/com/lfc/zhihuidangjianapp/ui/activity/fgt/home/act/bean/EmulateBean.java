package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean;

import java.util.List;

public class EmulateBean {

    /**
     * code : 0
     * data : {"studyStrongBureauList":{"datas":[{"author":"d","comment":"<p>d<img src=\"http://192.168.0.165:8099/Path/20190517/77c56373-0a96-4046-b34f-21d25028440b.jpg\"><\/p>","create_code":"1002","create_name":"test","create_time":1559372964000,"if_pass":0,"if_top":0,"release_date":"2019-05-16","remark":"小张2019-05-17 19:57:47执行修改操作---","study_strong_bureau_id":11,"study_strong_bureau_type":2,"title":"d4444","top_num":2}],"isLastPage":true,"pageCount":1,"pageNumber":1,"pageSize":10,"total":1}}
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
         * studyStrongBureauList : {"datas":[{"author":"d","comment":"<p>d<img src=\"http://192.168.0.165:8099/Path/20190517/77c56373-0a96-4046-b34f-21d25028440b.jpg\"><\/p>","create_code":"1002","create_name":"test","create_time":1559372964000,"if_pass":0,"if_top":0,"release_date":"2019-05-16","remark":"小张2019-05-17 19:57:47执行修改操作---","study_strong_bureau_id":11,"study_strong_bureau_type":2,"title":"d4444","top_num":2}],"isLastPage":true,"pageCount":1,"pageNumber":1,"pageSize":10,"total":1}
         */

        private StudyStrongBureauListBean studyStrongBureauList;

        public StudyStrongBureauListBean getStudyStrongBureauList() {
            return studyStrongBureauList;
        }

        public void setStudyStrongBureauList(StudyStrongBureauListBean studyStrongBureauList) {
            this.studyStrongBureauList = studyStrongBureauList;
        }

        public static class StudyStrongBureauListBean {
            /**
             * datas : [{"author":"d","comment":"<p>d<img src=\"http://192.168.0.165:8099/Path/20190517/77c56373-0a96-4046-b34f-21d25028440b.jpg\"><\/p>","create_code":"1002","create_name":"test","create_time":1559372964000,"if_pass":0,"if_top":0,"release_date":"2019-05-16","remark":"小张2019-05-17 19:57:47执行修改操作---","study_strong_bureau_id":11,"study_strong_bureau_type":2,"title":"d4444","top_num":2}]
             * isLastPage : true
             * pageCount : 1
             * pageNumber : 1
             * pageSize : 10
             * total : 1
             */
            private String thumbnailUrl;

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            private boolean isLastPage;
            private int pageCount;
            private int pageNumber;
            private int pageSize;
            private int total;
            private List<DatasBean> datas;

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
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

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DatasBean> getDatas() {
                return datas;
            }

            public void setDatas(List<DatasBean> datas) {
                this.datas = datas;
            }

            public static class DatasBean {
                /**
                 * author : d
                 * comment : <p>d<img src="http://192.168.0.165:8099/Path/20190517/77c56373-0a96-4046-b34f-21d25028440b.jpg"></p>
                 * create_code : 1002
                 * create_name : test
                 * create_time : 1559372964000
                 * if_pass : 0
                 * if_top : 0
                 * release_date : 2019-05-16
                 * remark : 小张2019-05-17 19:57:47执行修改操作---
                 * study_strong_bureau_id : 11
                 * study_strong_bureau_type : 2
                 * title : d4444
                 * top_num : 2
                 */

                private String author;
                private String comment;
                private String create_code;
                private String create_name;
                private long create_time;
                private int if_pass;
                private int if_top;
                private String release_date;
                private String remark;
                private int study_strong_bureau_id;
                private int study_strong_bureau_type;
                private String title;
                private int top_num;
                private String thumbnailUrl;

                public String getThumbnailUrl() {
                    return thumbnailUrl;
                }

                public void setThumbnailUrl(String thumbnailUrl) {
                    this.thumbnailUrl = thumbnailUrl;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getCreate_code() {
                    return create_code;
                }

                public void setCreate_code(String create_code) {
                    this.create_code = create_code;
                }

                public String getCreate_name() {
                    return create_name;
                }

                public void setCreate_name(String create_name) {
                    this.create_name = create_name;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public int getIf_pass() {
                    return if_pass;
                }

                public void setIf_pass(int if_pass) {
                    this.if_pass = if_pass;
                }

                public int getIf_top() {
                    return if_top;
                }

                public void setIf_top(int if_top) {
                    this.if_top = if_top;
                }

                public String getRelease_date() {
                    if (release_date == null||release_date.equals("null")) {
                        release_date = "";
                    }
                    return release_date;
                }

                public void setRelease_date(String release_date) {
                    this.release_date = release_date;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public int getStudy_strong_bureau_id() {
                    return study_strong_bureau_id;
                }

                public void setStudy_strong_bureau_id(int study_strong_bureau_id) {
                    this.study_strong_bureau_id = study_strong_bureau_id;
                }

                public int getStudy_strong_bureau_type() {
                    return study_strong_bureau_type;
                }

                public void setStudy_strong_bureau_type(int study_strong_bureau_type) {
                    this.study_strong_bureau_type = study_strong_bureau_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getTop_num() {
                    return top_num;
                }

                public void setTop_num(int top_num) {
                    this.top_num = top_num;
                }
            }
        }
    }
}
