package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.app.MyApplication;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.ApiConstant;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.MyPartyPaymentHisPageListAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.QueryUnPaidPartyPaymentHisPageListAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.queryPartyPaymentHisPageListAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryUnPaidPartyPaymentHisPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.queryPartyPaymentHisPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.Party_membershipDuesBean;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 党费缴纳支付按钮进入页面
 */
public class Act_Party_membershipPay extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_guanli)
    TextView profile_guanli;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.weijiaoRecyclerview)
    RecyclerView weijiaoRecyclerview;
    @BindView(R.id.yijiaoRecyclerView)
    RecyclerView yijiaoRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.item_party_membershippay;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.yijiandaijiaofei).setOnClickListener(this);
        findViewById(R.id.imgBack).setOnClickListener(this);
        profileName.setText(MyApplication.getLoginBean().getRealName());
        profile_guanli.setText(MyApplication.getLoginBean().getRoleName());
        mRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置 Header 为 Material风格
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(false));
        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            pageNum = 1;
            weiJiaoDatas.clear();
            yijiaofei.clear();
            queryUnPaidPartyPaymentHisPageList();
            queryPartyPaymentHisPageList();
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageNum++;
            queryUnPaidPartyPaymentHisPageList();
            queryPartyPaymentHisPageList();
            refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        weijiaoRecyclerview.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 2);
        yijiaoRecyclerView.setLayoutManager(gridLayoutManager1);
    }

    @Override
    protected void initData() {
        queryUnPaidPartyPaymentHisPageList();
        queryPartyPaymentHisPageList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.yijiandaijiaofei:
                startActivity(Act_PartyMembershipDues.class);
                break;
        }
    }

    private int pageNum = 1;
    MyPartyPaymentHisPageListAdapter adapter;
    private List<QueryUnPaidPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean> weiJiaoDatas = new ArrayList<>();
    private QueryUnPaidPartyPaymentHisPageListAdapter queryUnPaidPartyPaymentHisPageListAdapter;

    /**
     * 分页查询党费缴费记录信息-本月未缴列表
     */
    public void queryUnPaidPartyPaymentHisPageList() {
        HttpHelper.queryUnPaidPartyPaymentHisPageList(pageNum, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                QueryUnPaidPartyPaymentHisPageListBean bean = gson.fromJson(succeed, QueryUnPaidPartyPaymentHisPageListBean.class);
                if (bean.getCode() == 0) {
                    for (int i = 0; i < bean.getData().getUnPaidPartyPaymentHisList().size(); i++) {
                        weiJiaoDatas.add(bean.getData().getUnPaidPartyPaymentHisList().get(i));
                    }
                }
                if (queryUnPaidPartyPaymentHisPageListAdapter == null) {
                    queryUnPaidPartyPaymentHisPageListAdapter = new QueryUnPaidPartyPaymentHisPageListAdapter(getContext(), weiJiaoDatas);
                    weijiaoRecyclerview.setAdapter(queryUnPaidPartyPaymentHisPageListAdapter);
                    queryUnPaidPartyPaymentHisPageListAdapter.setOnItemChildClickListener((adapter, view, position) -> {//代缴
                    });
                } else {
                    queryUnPaidPartyPaymentHisPageListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
    }

    List<queryPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean.DatasBean> yijiaofei = new ArrayList<>();
    queryPartyPaymentHisPageListAdapter yijiaoadapter;

    /**
     * 分页查询党费缴费记录信息-已缴列表
     */
    public void queryPartyPaymentHisPageList() {
        HttpHelper.queryPartyPaymentHisPageList(pageNum, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                queryPartyPaymentHisPageListBean bean = gson.fromJson(succeed, queryPartyPaymentHisPageListBean.class);
                if (bean.getCode() == 0) {
                    if (pageNum <= bean.getData().getUnPaidPartyPaymentHisList().getPageNumber()) {
                        for (int i = 0; i < bean.getData().getUnPaidPartyPaymentHisList().getDatas().size(); i++) {
                            yijiaofei.add(bean.getData().getUnPaidPartyPaymentHisList().getDatas().get(i));
                        }
                    }
                }
                if (yijiaoadapter == null) {
                    yijiaoadapter = new queryPartyPaymentHisPageListAdapter(yijiaofei);
                    yijiaoRecyclerView.setAdapter(yijiaoadapter);
                } else {
                    yijiaoadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_clcik:

                break;
        }
    }
}
