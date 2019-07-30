package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.Party_membershipDuesBean;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 党费缴纳
 */
public class Act_Party_membershipDues extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.My_organization)
    TextView MyOrganization;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_guanli)
    TextView profile_guanli;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.myRecyclerview)
    RecyclerView myRecyclerview;
    @BindView(R.id.bt_status)
    TextView bt_status;
    @Override
    protected int getLayoutId() {
        return R.layout.act_party_membership_dues;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.imgBack).setOnClickListener(this);
        MyOrganization.setOnClickListener(this);
        Glide.with(getContext()).load(ApiConstant.ROOT_URL + MyApplication.getLoginBean().getContextUrl()).into(profileImage);
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

            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
        });
        myRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        bt_status.setOnClickListener(v -> {
            if (bt_status.isSelected()) {

            }
        });
    }

    @Override
    protected void initData() {
        queryMyPartyPaymentHisPageList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.My_organization:
                Intent intent = new Intent();
                intent.setClass(getContext(), Act_Party_membershipPay.class);
                startActivity(intent);
                break;
        }
    }

    private int pageNum = 1;
    private List<Party_membershipDuesBean.DataBean.PartyPaymentHisListBean.DatasBean> datas = new ArrayList<>();
    MyPartyPaymentHisPageListAdapter adapter;

    /**
     * 我的党费
     */
    public void queryMyPartyPaymentHisPageList() {
        HttpHelper.queryMyPartyPaymentHisPageList(pageNum + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }
            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                Party_membershipDuesBean entity = gson.fromJson(succeed, Party_membershipDuesBean.class);
                if (entity.getCode() == 0) {
                    profileName.setText(entity.getData().getUserName());
                    for (int i = 0; i < entity.getData().getPartyPaymentHisList().getDatas().size(); i++) {
                        datas.add(entity.getData().getPartyPaymentHisList().getDatas().get(i));
                    }
                    if (adapter == null) {
                        adapter = new MyPartyPaymentHisPageListAdapter(getContext(), datas);
                        adapter.setOnItemChildClickListener(Act_Party_membershipDues.this);
                        myRecyclerview.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                    bt_status.setText("已缴纳党费" + entity.getData().getMoney() + "元");
                    if (entity.getData().getMoney() == 0) {
                        bt_status.setSelected(false);
                    } else {
                        bt_status.setSelected(true);
                    }
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
