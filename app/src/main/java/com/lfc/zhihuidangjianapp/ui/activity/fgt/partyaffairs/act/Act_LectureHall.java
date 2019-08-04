package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gyf.barlibrary.ImmersionBar;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 林场大讲堂
 */
public class Act_LectureHall extends BaseActivity {
    @BindView(R.id.my_RecyclerView)
    RecyclerView myRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.act_lecturehall;
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
//            pageNumber = 1;
//            datas.clear();
//            apistudycraftsmanpage();
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
//            pageNumber++;
//            apistudycraftsmanpage();
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
        });
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {

    }


}
