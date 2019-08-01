package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.fgt;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_StyleOfForestAreaDetails;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apiorglifedetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter.OrganizingLifeAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter.StyleOfForestAreaAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.OrganizingLifeBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.StyleOfForestAreaBean;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 林区风采子类
 */
public class Fgt_StyleOfForestArea extends BaseFragment {
    @BindView(R.id.my_RecyclerView)
    RecyclerView my_RecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    String id = "";

    public static Fgt_StyleOfForestArea getInstance(String id1) {
        Fgt_StyleOfForestArea sf = new Fgt_StyleOfForestArea();
        sf.id = id1;
        return sf;
    }

    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fgt_organizinglife;
    }

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
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
            data.clear();
            apistylepage(id);
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageNumber++;
            apistylepage(id);
            refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        my_RecyclerView.setLayoutManager(gridLayoutManager);
    }

    private List<StyleOfForestAreaBean.DataBean.DatasBean> data = new ArrayList<>();
    private StyleOfForestAreaAdapter adapter;

    @Override
    protected void initData() {
        adapter = new StyleOfForestAreaAdapter(getContext(), data);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra("id", data.get(position).getForestDistrictId() + "");
            intent.setClass(getContext(), Act_StyleOfForestAreaDetails.class);
            startActivity(intent);
        });
        my_RecyclerView.setAdapter(adapter);
        data.clear();
        apistylepage(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int pageNumber = 1;

    /**
     * 查看组织生活详情信息
     */
    public void apistylepage(String forestDistrictType) {
        HttpHelper.apistylepage(pageNumber + "", forestDistrictType, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                StyleOfForestAreaBean entity = gson.fromJson(succeed, StyleOfForestAreaBean.class);
                if (entity.getCode() == 0) {
                    if (pageNumber <= entity.getData().getPageNumber()) {
                        for (int i = 0; i < entity.getData().getDatas().size(); i++) {
                            data.add(entity.getData().getDatas().get(i));
                        }
                    }
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
