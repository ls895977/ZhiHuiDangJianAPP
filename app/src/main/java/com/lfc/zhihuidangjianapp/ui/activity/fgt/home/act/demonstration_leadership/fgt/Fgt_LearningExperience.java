package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.fgt;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
import com.lfc.zhihuidangjianapp.net.http.ApiConstant;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_TitleDetails;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.adapter.Demonstration_LeadershipAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.QueryLeadDemonstrationPageListBean;
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
 * 学习心得子类
 */
public class Fgt_LearningExperience extends BaseFragment implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.my_RecyclerView)
    RecyclerView my_RecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    String leadDemonstrationType = "";
    public static Fgt_LearningExperience getInstance(String title) {
        Fgt_LearningExperience sf = new Fgt_LearningExperience();
        sf.leadDemonstrationType = title;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fgt_learningexperience;
    }

    Unbinder unbinder;
//    private Demonstration_LeadershipAdapter adapter;
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
        my_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new Demonstration_LeadershipAdapter(getContext(), datas);
//        adapter.setOnItemChildClickListener(this);
//        my_RecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
//        queryLeadDemonstrationPageList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private List<QueryLeadDemonstrationPageListBean.DataBean.LeadDemonstrationListBean.DatasBean> datas = new ArrayList<>();
    private int pageNum = 1;
    /**
     * 专题专栏
     */
    public void queryLeadDemonstrationPageList() {
        HttpHelper.queryLeadDemonstrationPageList(leadDemonstrationType, pageNum + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                QueryLeadDemonstrationPageListBean entity = gson.fromJson(succeed, QueryLeadDemonstrationPageListBean.class);
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_title://跳转详情页
            case R.id.item_hader:
                if (datas.size() < 0) {
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("title", datas.get(0).getTitle());
                intent.putExtra("context", datas.get(0).getComment());
                intent.setClass(getContext(), Act_TitleDetails.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.demonstration_item:
                Intent intent = new Intent();
                intent.putExtra("title", datas.get(position).getTitle());
                intent.putExtra("context", datas.get(position).getComment());
                intent.setClass(getContext(), Act_TitleDetails.class);
                startActivity(intent);
                break;
        }
    }
}
