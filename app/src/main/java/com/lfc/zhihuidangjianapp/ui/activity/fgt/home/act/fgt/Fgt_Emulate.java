package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.fgt;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
import com.lfc.zhihuidangjianapp.bean.QueryPartyDynamicPageListBean;
import com.lfc.zhihuidangjianapp.net.http.ApiConstant;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_TitleDetails;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.EmulateAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.EmulateBean;
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
 * 学习强局 子类
 */
public class Fgt_Emulate extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.my_RecyclerView)
    RecyclerView my_RecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.item_hader)
    ImageView item_hader;
    @BindView(R.id.item_title)
    TextView item_title;
    @BindView(R.id.emulate_item1)
    LinearLayout emulate_item1;
    String title = "";
    Unbinder unbinder;

    public static Fgt_Emulate getInstance(String title1) {
        Fgt_Emulate sf = new Fgt_Emulate();
        sf.title = title1;
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fgt_emulate;
    }

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        emulate_item1.setOnClickListener(this);
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
            datas.clear();
            queryStudyStrongBureauPageList();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageNum++;
            queryStudyStrongBureauPageList();
        });
        my_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EmulateAdapter(getContext(), datas);
        my_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        my_RecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra("title", datas.get(position).getTitle());
            intent.putExtra("context", datas.get(position).getComment());
            intent.putExtra("zuozhe",datas.get(position).getRelease_date()+"电   (记者："+datas.get(position).getAuthor()+")");
            intent.setClass(getContext(), Act_TitleDetails.class);
            startActivity(intent);
        });
    }

    EmulateAdapter adapter;
    private List<EmulateBean.DataBean.StudyStrongBureauListBean.DatasBean> datas = new ArrayList<>();

    @Override
    protected void initData() {
        datas.clear();
        queryStudyStrongBureauPageList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int pageNum = 1;
    EmulateBean entity;

    /**
     * 分页查询学习强局
     */
    public void queryStudyStrongBureauPageList() {
        HttpHelper.queryStudyStrongBureauPageList(title, pageNum + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                entity = gson.fromJson(succeed, EmulateBean.class);
                if (entity.getCode() == 0) {
                    if (entity.getData().getStudyStrongBureauList().getDatas().size() > 0) {
                        Glide.with(getActivity()).load(entity.getData().getStudyStrongBureauList().getDatas().get(0).getThumbnailUrl()).into(item_hader);
                        item_title.setText(entity.getData().getStudyStrongBureauList().getDatas().get(0).getTitle());
                    }
                    if (pageNum <= entity.getData().getStudyStrongBureauList().getPageNumber()) {
                        for (int i = 0; i < entity.getData().getStudyStrongBureauList().getDatas().size(); i++) {
                            datas.add(entity.getData().getStudyStrongBureauList().getDatas().get(i));
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                mRefreshLayout.finishRefresh();
                mRefreshLayout.finishLoadMore();
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
        if (entity != null && entity.getData().getStudyStrongBureauList().getDatas().size() > 0) {
            Intent intent = new Intent();
            intent.putExtra("title", entity.getData().getStudyStrongBureauList().getDatas().get(0).getTitle());
            intent.putExtra("context", entity.getData().getStudyStrongBureauList().getDatas().get(0).getComment());
            intent.putExtra("zuozhe",datas.get(0).getRelease_date()+"电   (记者："+datas.get(0).getAuthor()+")");
            intent.setClass(getContext(), Act_TitleDetails.class);
            startActivity(intent);
        }
    }
}
