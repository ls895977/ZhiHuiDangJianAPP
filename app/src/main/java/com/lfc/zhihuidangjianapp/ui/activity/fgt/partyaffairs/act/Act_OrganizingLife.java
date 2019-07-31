package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.MuneDilog;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.bean.MuneBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter.OrganizingLifeAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.OrganizingLifeBean;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 组织生活
 */
public class Act_OrganizingLife extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.my_RecyclerView)
    RecyclerView my_RecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private TextView title[] = new TextView[3];
    private MuneDilog muneDilog;

    @Override
    protected int getLayoutId() {
        return R.layout.act_organizinglife;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        ButterKnife.bind(this);
        findViewById(R.id.imgBack).setOnClickListener(this);
        muneDilog = new MuneDilog(this);
        title[0] = findViewById(R.id.tv_title1);
        title[1] = findViewById(R.id.tv_title2);
        title[2] = findViewById(R.id.tv_title3);
        title[0].setOnClickListener(this);
        title[1].setOnClickListener(this);
        title[2].setOnClickListener(this);
        mRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置 Header 为 Material风格
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(false));
        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            data.clear();
            apiorglifepage();
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
//        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
//
//            refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
//        });
        my_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        my_RecyclerView.setNestedScrollingEnabled(false);
    }

    private List<OrganizingLifeBean.DataBean.DatasBean> data = new ArrayList<>();
    private OrganizingLifeAdapter adapter;

    @Override
    protected void initData() {
        muneDilog.setOnBackItem((key, vlue) -> {//获取主师菜单
            title[1].setText(vlue);
            data.clear();
            muneDilog.dismiss();
            studyType = key;
            orderTime = "1";
            loding.show();
            apiorglifepage();
        });
        adapter = new OrganizingLifeAdapter(getContext(), data);
        my_RecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra("id",data.get(position).getOrganizationalLifeId()+"");
            intent.setClass(getContext(), Act_OrganizingLifeDeatle.class);
            startActivity(intent);
        });
        swiche(0);
        orderTime = "2";
        apiorglifepage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tv_title1:
                swiche(0);
                loding.show();
                studyType = "";
                orderTime = "0";
                apiorglifepage();
                break;
            case R.id.tv_title2:
                swiche(1);
                muneDilog.show();
                break;
            case R.id.tv_title3:
                swiche(2);
                loding.show();
                studyType = "";
                orderTime = "1";
                apiorglifepage();
                break;
        }
    }

    private int indext = 0;

    public void swiche(int page) {
        title[indext].setSelected(false);
        title[page].setSelected(true);
        indext = page;
    }

    private String stTitle = "", studyType = "", orderTime = "0";

    /**
     * 分页查询组织生活
     * title标题
     * studyType("组织生活类别(0民主生活会1组织生活会2党课3主题党日4民主评议党员5其他)
     * orderTime ("时间排序（1升序 2 降序）
     */
    public void apiorglifepage() {
        HttpHelper.apiorglifepage(stTitle, studyType, orderTime, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                OrganizingLifeBean entity = gson.fromJson(succeed, OrganizingLifeBean.class);
                if (entity.getCode() == 0) {
                    data.addAll(entity.getData().getDatas());
                    adapter.notifyDataSetChanged();
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
