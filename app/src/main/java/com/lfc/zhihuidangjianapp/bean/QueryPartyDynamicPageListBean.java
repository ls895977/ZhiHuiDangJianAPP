package com.lfc.zhihuidangjianapp.bean;

import java.util.List;

public class QueryPartyDynamicPageListBean {

    /**
     * code : 0
     * data : {"partyDynamicList":{"datas":[{"author":"dsfa","comment":"<p>dsfsafa<\/p>","create_code":"1002","create_name":"赵真鹏","create_time":1559553756000,"dynamic_source":"dsfafs","if_pass":0,"if_top":1,"party_dynamic_id":7,"party_dynamic_type":0,"release_date":"2019-05-29","thumbnail_url":"Path/20190529/01c87c97-3f1d-407b-bdb0-9bf26f03cbc7.jpeg","title":"fsdf"}],"isLastPage":true,"pageCount":1,"pageNumber":1,"pageSize":10,"total":1}}
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
         * partyDynamicList : {"datas":[{"author":"dsfa","comment":"<p>dsfsafa<\/p>","create_code":"1002","create_name":"赵真鹏","create_time":1559553756000,"dynamic_source":"dsfafs","if_pass":0,"if_top":1,"party_dynamic_id":7,"party_dynamic_type":0,"release_date":"2019-05-29","thumbnail_url":"Path/20190529/01c87c97-3f1d-407b-bdb0-9bf26f03cbc7.jpeg","title":"fsdf"}],"isLastPage":true,"pageCount":1,"pageNumber":1,"pageSize":10,"total":1}
         */

        private PartyDynamicListBean partyDynamicList;

        public PartyDynamicListBean getPartyDynamicList() {
            return partyDynamicList;
        }

        public void setPartyDynamicList(PartyDynamicListBean partyDynamicList) {
            this.partyDynamicList = partyDynamicList;
        }

        public static class PartyDynamicListBean {
            /**
             * datas : [{"author":"dsfa","comment":"<p>dsfsafa<\/p>","create_code":"1002","create_name":"赵真鹏","create_time":1559553756000,"dynamic_source":"dsfafs","if_pass":0,"if_top":1,"party_dynamic_id":7,"party_dynamic_type":0,"release_date":"2019-05-29","thumbnail_url":"Path/20190529/01c87c97-3f1d-407b-bdb0-9bf26f03cbc7.jpeg","title":"fsdf"}]
             * isLastPage : true
             * pageCount : 1
             * pageNumber : 1
             * pageSize : 10
             * total : 1
             */

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
                 * author : dsfa
                 * comment : <p>dsfsafa</p>
                 * create_code : 1002
                 * create_name : 赵真鹏
                 * create_time : 1559553756000
                 * dynamic_source : dsfafs
                 * if_pass : 0
                 * if_top : 1
                 * party_dynamic_id : 7
                 * party_dynamic_type : 0
                 * release_date : 2019-05-29
                 * thumbnail_url : Path/20190529/01c87c97-3f1d-407b-bdb0-9bf26f03cbc7.jpeg
                 * title : fsdf
                 */

                private String author;
                private String comment;
                private String create_code;
                private String create_name;
                private long create_time;
                private String dynamic_source;
                private int if_pass;
                private int if_top;
                private int party_dynamic_id;
                private int party_dynamic_type;
                private String release_date;
                private String thumbnailUrl;
                private String title;

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

                public String getDynamic_source() {
                    return dynamic_source;
                }

                public void setDynamic_source(String dynamic_source) {
                    this.dynamic_source = dynamic_source;
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

                public int getParty_dynamic_id() {
                    return party_dynamic_id;
                }

                public void setParty_dynamic_id(int party_dynamic_id) {
                    this.party_dynamic_id = party_dynamic_id;
                }

                public int getParty_dynamic_type() {
                    return party_dynamic_type;
                }

                public void setParty_dynamic_type(int party_dynamic_type) {
                    this.party_dynamic_type = party_dynamic_type;
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

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
