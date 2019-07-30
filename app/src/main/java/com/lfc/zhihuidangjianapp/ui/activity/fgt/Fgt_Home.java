package com.lfc.zhihuidangjianapp.ui.activity.fgt;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.app.MyApplication;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
import com.lfc.zhihuidangjianapp.base.GlideImageLoader;
import com.lfc.zhihuidangjianapp.bean.BaseBean;
import com.lfc.zhihuidangjianapp.bean.LoginBean;
import com.lfc.zhihuidangjianapp.bean.QueryAppConfigListBean;
import com.lfc.zhihuidangjianapp.bean.QueryHomeNoticeAnnouncementPageListBean;
import com.lfc.zhihuidangjianapp.image.ImageLoader;
import com.lfc.zhihuidangjianapp.net.http.ApiConstant;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.Act_Main;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_Announcement;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_AnnouncementList;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_Demonstration_Leadership;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_Emulate;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_PartyBuilding;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_PartyBuildingMatrix;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_Party_membershipDues;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_WebView;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.HomeAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryAppConfigListBean1;
import com.lfc.zhihuidangjianapp.widget.MyListView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fgt_Home extends BaseFragment {
    @BindView(R.id.homeListView)
    MyListView homeListView;
    @BindView(R.id.imgBanner)
    Banner imgBanner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    private String imgPath = "";
    private ArrayList<QueryHomeNoticeAnnouncementPageListBean.DataBean.NoticeAnnouncementListBean.DatasBean> list = new ArrayList<>();
    private HomeAdapter homeAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        homeAdapter = new HomeAdapter(list, getActivity());
        homeListView.setAdapter(homeAdapter);
        homeListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), Act_Announcement.class);
            intent.putExtra("id", list.get(position).getNoticeAnnouncementId() + "");
            startActivity(intent);
        });
        ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        mRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置 Header 为 Material风格
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(false));
        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            queryHomeNoticeAnnouncementPageList();
            list.clear();
            queryAppConfigList();
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        queryHomeNoticeAnnouncementPageList();
        queryAppConfigList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void startWebView(String title, String url) {
        Intent intent = new Intent(getActivity(), Act_WebView.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.linear1)//党建矩阵
    public void onLinear1Clicked() {
        startActivity(new Intent(getContext(), Act_PartyBuildingMatrix.class));
    }

    @OnClick(R.id.linear2)//学习强局
    public void onLinear2Clicked() {
        startActivity(new Intent(getContext(), Act_Emulate.class));
    }

    @OnClick(R.id.linear3)//党建动态
    public void onLinear3Clicked() {
        startActivity(new Intent(getContext(), Act_PartyBuilding.class));
    }

    @OnClick(R.id.linear5)//党费缴纳
    public void onLinear5Clicked() {
        startActivity(new Intent(getContext(), Act_Party_membershipDues.class));
    }

    @OnClick(R.id.linear4)//专题专栏
    public void onLinear4Clicked() {
        startActivity(new Intent(getContext(), Act_Demonstration_Leadership.class));
    }
    @OnClick(R.id.relativeAnnoun)//公告公示
    public void onRelativeAnnounClicked() {
        getActivity().startActivity(new Intent(getActivity(), Act_AnnouncementList.class));
    }
    /**
     * 获取公告列表
     */
    public void queryHomeNoticeAnnouncementPageList() {
        HttpHelper.queryHomeNoticeAnnouncementPageList(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }
            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                QueryHomeNoticeAnnouncementPageListBean entity = gson.fromJson(succeed, QueryHomeNoticeAnnouncementPageListBean.class);
                if (entity.getCode() == 0) {
                    list.addAll(entity.getData().getNoticeAnnouncementList().getDatas());
                    homeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
            }
        });
    }
    /**
     * 首页banner
     */
    private QueryAppConfigListBean1 bannerEntity;
    public void queryAppConfigList() {
        HttpHelper.queryAppConfigList(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                bannerEntity = gson.fromJson(succeed, QueryAppConfigListBean1.class);
                if (bannerEntity.getCode() == 0) {
                    List<String > image=new ArrayList<>();
                    for (int i=0;i<bannerEntity.getData().getAppConfigList().getDatas().size();i++){
                        image.add(bannerEntity.getData().getAppConfigList().getDatas().get(i).getUrl());
                    }
                    //设置图片加载器
                    imgBanner.setImageLoader(new GlideImageLoader());
                    imgBanner.setImages(image).start();
                    imgBanner.setOnBannerListener(position -> {
                        startWebView("详情", bannerEntity.getData().getAppConfigList().getDatas().get(position).getPath());
                    });
                }
            }

            @Override
            public void onError(String error) {
            }
        });
    }
}
