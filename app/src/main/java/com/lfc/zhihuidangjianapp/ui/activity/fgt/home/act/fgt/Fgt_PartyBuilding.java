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
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.PartyBuildingAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.PartyBuildingBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryDeptListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryDeptListChlideBean;
import com.makeramen.roundedimageview.RoundedImageView;
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
 * 党建动态子类
 */
public class Fgt_PartyBuilding extends BaseFragment {
    @BindView(R.id.my_RecyclerView)
    RecyclerView my_RecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.item_hader)
    ImageView item_hader;
    @BindView(R.id.item_title)
    TextView item_title;
    @BindView(R.id.partybuilding_item)
    LinearLayout partybuilding_item;
    String title1 = "";

    public static Fgt_PartyBuilding getInstance(String title) {
        Fgt_PartyBuilding sf = new Fgt_PartyBuilding();
        sf.title1 = title;
        return sf;
    }

    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fgt_partybuilding;
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
            pageNum = 1;
            datasBeans.clear();
            queryPartyDynamicPageList();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageNum++;
            queryPartyDynamicPageList();
        });
        my_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PartyBuildingAdapter(getContext(), datasBeans);
        my_RecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra("title", datasBeans.get(position).getTitle());
            intent.putExtra("context", datasBeans.get(position).getComment());
            intent.putExtra("zuozhe",datasBeans.get(position).getRelease_date()+"电   (记者："+datasBeans.get(position).getAuthor()+")");
            intent.setClass(getContext(), Act_TitleDetails.class);
            startActivity(intent);
        });
        partybuilding_item.setOnClickListener(v -> {
            if (datasBeans.size() == 0) {
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("title", datasBeans.get(0).getTitle());
            intent.putExtra("context", datasBeans.get(0).getComment());
            intent.putExtra("zuozhe",datasBeans.get(0).getRelease_date()+"电   (记者："+datasBeans.get(0).getAuthor()+")");
            intent.setClass(getContext(), Act_TitleDetails.class);
            startActivity(intent);
        });
    }

    PartyBuildingAdapter adapter;

    @Override
    protected void initData() {
        datasBeans.clear();
        queryPartyDynamicPageList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int pageNum = 1;
    private List<QueryPartyDynamicPageListBean.DataBean.PartyDynamicListBean.DatasBean> datasBeans = new ArrayList<>();

    /**
     * 党建动态
     */
    public void queryPartyDynamicPageList() {
        HttpHelper.queryPartyDynamicPageList(title1, pageNum + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                QueryPartyDynamicPageListBean entity = gson.fromJson(succeed, QueryPartyDynamicPageListBean.class);
                if (entity.getCode() == 0) {
                    if (pageNum <= entity.getData().getPartyDynamicList().getPageNumber()) {
                        for (int i = 0; i < entity.getData().getPartyDynamicList().getDatas().size(); i++) {
                            datasBeans.add(entity.getData().getPartyDynamicList().getDatas().get(i));
                        }
                    }
                    if (entity.getData().getPartyDynamicList().getDatas().size() >0) {
                        item_title.setText(Html.fromHtml(entity.getData().getPartyDynamicList().getDatas().get(0).getTitle()));
                        Glide.with(getActivity()).load(entity.getData().getPartyDynamicList().getDatas().get(0).getThumbnailUrl()).into(item_hader);
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
}
