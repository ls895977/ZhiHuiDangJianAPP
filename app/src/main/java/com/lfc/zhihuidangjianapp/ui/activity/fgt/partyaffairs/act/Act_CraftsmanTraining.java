package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.CraftsmanTrainingBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apistyledetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter.CraftsmanTrainingAdapter;
import com.lfc.zhihuidangjianapp.view.HtmlFormat;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工匠培养
 */
public class Act_CraftsmanTraining extends BaseActivity {
    @BindView(R.id.my_RecyclerView)
    RecyclerView myRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.act_craftsmantraining;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        mRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置 Header 为 Material风格
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(false));
        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            pageNumber = 1;
            datas.clear();
            apistudycraftsmanpage();
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            pageNumber++;
            apistudycraftsmanpage();
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
        });
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void initData() {
        apistudycraftsmanpage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }

    private int pageNumber = 1;
    List<CraftsmanTrainingBean.DataBean.DatasBean> datas = new ArrayList<>();
    private CraftsmanTrainingAdapter adapter;

    /**
     * 工匠分页数据
     */
    public void apistudycraftsmanpage() {
        HttpHelper.apistudycraftsmanpage(pageNumber + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                CraftsmanTrainingBean entity = gson.fromJson(succeed, CraftsmanTrainingBean.class);
                if (entity.getCode() == 0) {
                    if (pageNumber <= entity.getData().getPageNumber()) {
                        for (int i = 0; i < entity.getData().getDatas().size(); i++) {
                            datas.add(entity.getData().getDatas().get(i));
                        }
                    }
                    if (adapter == null) {
                        adapter = new CraftsmanTrainingAdapter(getContext(), datas);
                        myRecyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener((adapter, view, position) -> {
                                Intent intent=new Intent();
                                intent.putExtra("id",datas.get(position).getStudyStrongBureauId()+"");
                                intent.setClass(getContext(),Act_CraftsmanTrainingDetails.class);
                                startActivity(intent);
                        });
                    } else {
                        adapter.notifyDataSetChanged();
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
}
