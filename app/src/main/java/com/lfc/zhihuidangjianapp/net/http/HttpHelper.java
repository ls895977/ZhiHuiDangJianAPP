package com.lfc.zhihuidangjianapp.net.http;

import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.lfc.zhihuidangjianapp.app.MyApplication;
import com.lfc.zhihuidangjianapp.bean.LoginBean;
import com.lfc.zhihuidangjianapp.bean.QueryHomeNoticeAnnouncementPageListBean;
import com.lfc.zhihuidangjianapp.bean.QueryPartyDynamicPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.EmulateBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryAppConfigListBean1;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryDeptListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryPartyDynamicDetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryUnPaidPartyPaymentHisPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.queryNoticeAnnouncementDetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.queryNoticeAnnouncementPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.Party_membershipDuesBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.QueryDeptDetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.QueryDeptGroupListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.QueryLeadDemonstrationPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.queryUserListByDeptNumber;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.CraftsmanTrainingBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.CraftsmanTrainingDetailsBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apiorglifedetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apistyledetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.appApiinsertTransferOrganizationalRelationsBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.appApiqueryTransferOrganizationalRelationsDetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.bean.MuneBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.OrganizingLifeBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.StyleOfForestAreaBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.queryUserListByFirstPinYinBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.personal.act.bean.UserDataBean;
import com.lfc.zhihuidangjianapp.utlis.Debug;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.HeaderMap;

public class HttpHelper {
    /**
     * 验证码登录
     */
    public static void login(String phone, String loginPwd, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("loginName", phone);
        map.put("loginPwd", loginPwd);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.LOGIN(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        LoginBean entity = gson.fromJson(succeed, LoginBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("aa", "---------------onError==" + e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 获取验证码
     */
    public static void loginCaptcha(String timeStr, final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("timeStr", timeStr);
        httpService.loginCaptcha(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        Log.e("aa", "------------获取验证码-----" + succeed);
//                        QueryHomeNoticeAnnouncementPageListBean entity = gson.fromJson(succeed, QueryHomeNoticeAnnouncementPageListBean.class);
//                        if (entity.getCode() == 0) {
//                            callBack.onSucceed(succeed);
//                        } else {
//                            callBack.onError(entity.getMsg() + "");
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("aa", "------------获取验证码---onError--" + e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 分页公告信息
     */
    public static void queryHomeNoticeAnnouncementPageList(final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryHomeNoticeAnnouncementPageList(MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        QueryHomeNoticeAnnouncementPageListBean entity = gson.fromJson(succeed, QueryHomeNoticeAnnouncementPageListBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页app-Banner
     */
    public static void queryAppConfigList(final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryAppConfigList(MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        QueryAppConfigListBean1 entity = gson.fromJson(succeed, QueryAppConfigListBean1.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 查看公告详情信息
     */
    public static void queryNoticeAnnouncementDetail(String noticeAnnouncementId, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("noticeAnnouncementId", noticeAnnouncementId);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryNoticeAnnouncementDetail(hashMap, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        queryNoticeAnnouncementDetailBean entity = gson.fromJson(succeed, queryNoticeAnnouncementDetailBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 分页公告信息
     */
    public static void queryNoticeAnnouncementPageList(final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryNoticeAnnouncementPageList(MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        queryNoticeAnnouncementPageListBean entity = gson.fromJson(succeed, queryNoticeAnnouncementPageListBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 通讯录-按首字母查询
     */
    public static void queryUserListByFirstPinYin(String partyPosts, final HttpUtilsCallBack<String> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("partyPosts", partyPosts);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryUserListByFirstPinYin(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        queryUserListByFirstPinYinBean entity = gson.fromJson(succeed, queryUserListByFirstPinYinBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 专题专栏
     * 引领示范类型(0:不忘初心 牢记使命1:改革创新 奋发有为)
     */
    public static void queryLeadDemonstrationPageList(String leadDemonstrationType, String pageNum, final HttpUtilsCallBack<String> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("leadDemonstrationType", leadDemonstrationType);
        map.put("pageNum", pageNum);
        map.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryLeadDemonstrationPageList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        QueryLeadDemonstrationPageListBean entity = gson.fromJson(succeed, QueryLeadDemonstrationPageListBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的党费
     */
    public static void queryMyPartyPaymentHisPageList(String pageNum, final HttpUtilsCallBack<String> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum);
        map.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryMyPartyPaymentHisPageList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        try {
                            Party_membershipDuesBean entity = gson.fromJson(succeed, Party_membershipDuesBean.class);
                            if (entity.getCode() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg() + "");
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 查询发展党员信息
     */
    public static void queryJoinPartyStageDeatil(final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryJoinPartyStageDeatil(MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        UserDataBean entity = gson.fromJson(succeed, UserDataBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 分页查询党费缴费记录信息-本月未缴列表
     */
    public static void queryUnPaidPartyPaymentHisPageList(int pageNum, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryUnPaidPartyPaymentHisPageList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        try {
                            QueryUnPaidPartyPaymentHisPageListBean entity = gson.fromJson(succeed, QueryUnPaidPartyPaymentHisPageListBean.class);
                            if (entity.getCode() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg() + "");
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 分页查询党费缴费记录信息-已缴列表
     */
    public static void queryPartyPaymentHisPageList(int pageNum, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryPartyPaymentHisPageList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        UserDataBean entity = gson.fromJson(succeed, UserDataBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 54486AFE755FA9944C2FE6DD51EF0CA5
     * 党建矩阵
     */
    public static void queryDeptList(final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryDeptList(MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        QueryDeptListBean entity = gson.fromJson(succeed, QueryDeptListBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 分页查询党建动态
     */
    public static void queryPartyDynamicPageList(String partyDynamicType, String pageNum, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("partyDynamicType", partyDynamicType);
        map.put("pageNum", pageNum);
        map.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryPartyDynamicPageList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        QueryPartyDynamicPageListBean entity = gson.fromJson(succeed, QueryPartyDynamicPageListBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 分页查询学习强局
     */
    public static void queryStudyStrongBureauPageList(String studyStrongBureauType, String pageNum, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("studyStrongBureauType", studyStrongBureauType);
        map.put("pageNum", pageNum);
        map.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryStudyStrongBureauPageList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        EmulateBean entity = gson.fromJson(succeed, EmulateBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 党支部详情
     */
    public static void queryDeptDetail(String deptNumber, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("deptNumber", deptNumber);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryDeptDetail(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        QueryDeptDetailBean entity = gson.fromJson(succeed, QueryDeptDetailBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 查询分组
     */
    public static void queryDeptGroupList(final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("deptNumber", "deptNumber");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryDeptGroupList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        QueryDeptGroupListBean entity = gson.fromJson(succeed, QueryDeptGroupListBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 根据部门number查询人员信息
     */
    public static void queryUserListByDeptNumber(String deptNumber, final HttpUtilsCallBack<String> callBack) {
        Log.e("aa", "-------------deptNumber==" + deptNumber + "---------token==" + MyApplication.getLoginBean().getToken());
        HashMap<String, String> map = new HashMap<>();
        map.put("deptNumber", deptNumber);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryUserListByDeptNumber(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        queryUserListByDeptNumber entity = gson.fromJson(succeed, queryUserListByDeptNumber.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 分页查询组织生活信息
     */
    public static void queryOrganizationalLifePageList(String pageNum, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum);
        map.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.queryOrganizationalLifePageList(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        queryUserListByDeptNumber entity = gson.fromJson(succeed, queryUserListByDeptNumber.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 党员关系转移详情
     */
    public static void appApiqueryTransferOrganizationalRelationsDetail(final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.appApiqueryTransferOrganizationalRelationsDetail(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        appApiqueryTransferOrganizationalRelationsDetailBean entity = gson.fromJson(succeed, appApiqueryTransferOrganizationalRelationsDetailBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 新增党组织关系转移信息
     * name 名字-[必填]
     * nowDept 现所在组织-[必填]
     * applyDeptName 申请转入组织
     * 转移原因-[必填]
     * status 状态(0:待转出1:待转入2:已转出3:已转入)-[必填]
     */
    public static void appApiinsertTransferOrganizationalRelations(String name, String nowDept, String applyDeptName, String transferReason, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
//        map.put("phone", phone);
        map.put("nowDept", nowDept);
        map.put("applyDeptName", applyDeptName);
        map.put("transferReason", transferReason);
        map.put("status ", "1");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.appApiinsertTransferOrganizationalRelations(map, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        appApiinsertTransferOrganizationalRelationsBean entity = gson.fromJson(succeed, appApiinsertTransferOrganizationalRelationsBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 获取筛选条件（组织生活会主题)
     */
    public static void apiorglifetopic(final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.apiorglifetopic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        MuneBean entity = gson.fromJson(succeed, MuneBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 分页查询组织生活
     * title标题
     * studyType("组织生活类别(0民主生活会1组织生活会2党课3主题党日4民主评议党员5其他)
     * orderTime ("时间排序（1升序 2 降序）
     */
    public static void apiorglifepage(String title, String studyType, String orderTime, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("title", title);
        hashMap.put("studyType", studyType);
        hashMap.put("orderTime", orderTime);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.apiorglifepage(hashMap, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        OrganizingLifeBean entity = gson.fromJson(succeed, OrganizingLifeBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Debug.e("-----分页查询组织生活---onError---" + e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 查看组织生活详情信息
     */
    public static void apiorglifedetail(String id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.apiorglifedetail(hashMap, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        apiorglifedetailBean entity = gson.fromJson(succeed, apiorglifedetailBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 分页查询林区风采信息
     */
    public static void apistylepage(String pageNumber, String forestDistrictType, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("forestDistrictType", forestDistrictType);
        hashMap.put("pageNumber", pageNumber);
        hashMap.put("pageSize", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.apistylepage(hashMap, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        StyleOfForestAreaBean entity = gson.fromJson(succeed, StyleOfForestAreaBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 分页查询林区风采信息
     */
    public static void apistyledetail(String id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.apistyledetail(hashMap, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        apistyledetailBean entity = gson.fromJson(succeed, apistyledetailBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 工匠分页数据
     */
    public static void apistudycraftsmanpage(String pageNumber, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageSize", "10");
        hashMap.put("pageNumber", pageNumber);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.apistudycraftsmanpage(hashMap, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        CraftsmanTrainingBean entity = gson.fromJson(succeed, CraftsmanTrainingBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 工匠详情
     */
    public static void apistudycraftsmandetail(String id , final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id );
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.apistudycraftsmandetail(hashMap, MyApplication.getLoginBean().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        CraftsmanTrainingDetailsBean entity = gson.fromJson(succeed, CraftsmanTrainingDetailsBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    public interface HttpUtilsCallBack<T> {
        public void onFailure(String failure);

        public void onSucceed(T succeed);

        public void onError(String error);
    }

    private static String httpFailureMsg() {
        if (NetUtils.isConnected()) {
            return "很抱歉，系统繁忙，请稍后重试。";
        } else {
            return "检查网络连接情况，请稍后重试。";
        }
    }

    public static boolean choseLoginStatis = false;

}
