package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.fgt;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
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
    String title = "";
    public static Fgt_StyleOfForestArea getInstance(String title1) {
        Fgt_StyleOfForestArea sf = new Fgt_StyleOfForestArea();
        sf.title = title1;
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
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {

            refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        my_RecyclerView.setLayoutManager(gridLayoutManager);
    }
    private List<StyleOfForestAreaBean> data=new ArrayList<>();
    private StyleOfForestAreaAdapter adapter;
    @Override
    protected void initData() {
        data.clear();
        for (int i=0;i<5;i++){
            data.add(new StyleOfForestAreaBean());
        }
        adapter=new StyleOfForestAreaAdapter(getContext(),data);
        my_RecyclerView.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
