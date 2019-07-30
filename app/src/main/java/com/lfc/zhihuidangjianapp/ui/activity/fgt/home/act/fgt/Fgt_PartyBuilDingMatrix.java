package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.fgt;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.app.MyApplication;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_TitleDetails;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.PartyBuilDingMatrixAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter.queryPartyPaymentHisPageListAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.PartyBuilDingMatrixBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryDeptListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryDeptListChlideBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryUnPaidPartyPaymentHisPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.Act_PartyBuilDingMatrixDetails;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.QueryDeptGroupListBean;
import com.lfc.zhihuidangjianapp.widget.MyListView;
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
 * 党建矩阵子类
 */
public class Fgt_PartyBuilDingMatrix extends BaseFragment {
    SmartRefreshLayout mRefreshLayout;
    RecyclerView recyclerview;
    private String mTitle;

    public static Fgt_PartyBuilDingMatrix getInstance(String title) {
        Fgt_PartyBuilDingMatrix sf = new Fgt_PartyBuilDingMatrix();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fgt_partybuildingmatrix;
    }

    Unbinder unbinder;

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        mRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        recyclerview = rootView.findViewById(R.id.RecyclerView);
        mRefreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerview.setLayoutManager(gridLayoutManager);
        adapter = new PartyBuilDingMatrixAdapter(data);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (mTitle.equals("党支部")) {
                Intent intent = new Intent();
                intent.putExtra("CompanyName", data.get(position).getCompanyName());
                intent.putExtra("deptNumber", data.get(position).getDeptNumber() + "");
                intent.setClass(getContext(), Act_PartyBuilDingMatrixDetails.class);
                startActivity(intent);
            }
        });
    }

    private PartyBuilDingMatrixAdapter adapter;
    List<QueryDeptListChlideBean> data = new ArrayList<>();

    @Override
    protected void initData() {
        if (mTitle.equals("党小组")) {
            queryDeptGroupList();
        } else {
            queryDeptList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 党建矩阵
     */
    public void queryDeptList() {
        data.clear();
        HttpHelper.queryDeptList(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                Log.e("aa", "------------su==" + succeed);
                QueryDeptListBean entity = gson.fromJson(succeed, QueryDeptListBean.class);
                switch (mTitle) {
                    case "党委":
                        for (int i = 0; i < entity.getData().getDeptList1().size(); i++) {
                            QueryDeptListChlideBean bean = new QueryDeptListChlideBean();
                            bean.setAbbreviation(entity.getData().getDeptList1().get(i).getAbbreviation());
                            bean.setBeginCreateDt(entity.getData().getDeptList1().get(i).getBeginCreateDt());
                            bean.setBriefIntroduction(entity.getData().getDeptList1().get(i).getBriefIntroduction());
                            bean.setCity(entity.getData().getDeptList1().get(i).getCity());
                            bean.setCompanyCreateSituation(entity.getData().getDeptList1().get(i).getCompanyCreateSituation());
                            bean.setCompanyName(entity.getData().getDeptList1().get(i).getCompanyName());
                            bean.setCompanySituation(entity.getData().getDeptList1().get(i).getCompanySituation());
                            bean.setCompanyType(entity.getData().getDeptList1().get(i).getCompanyType() + "");
                            bean.setDeptNumber(entity.getData().getDeptList1().get(i).getDeptNumber());
                            data.add(bean);
                        }
                        break;
                    case "党总支":
                        for (int i = 0; i < entity.getData().getDeptList2().size(); i++) {
                            QueryDeptListChlideBean bean = new QueryDeptListChlideBean();
                            bean.setAbbreviation(entity.getData().getDeptList2().get(i).getAbbreviation());
                            bean.setBeginCreateDt(entity.getData().getDeptList2().get(i).getBeginCreateDt());
                            bean.setBriefIntroduction(entity.getData().getDeptList2().get(i).getBriefIntroduction());
                            bean.setCity(entity.getData().getDeptList2().get(i).getCity());
                            bean.setCompanyCreateSituation(entity.getData().getDeptList2().get(i).getCompanyCreateSituation());
                            bean.setCompanyName(entity.getData().getDeptList2().get(i).getCompanyName());
                            bean.setCompanySituation(entity.getData().getDeptList2().get(i).getCompanySituation());
                            bean.setCompanyType(entity.getData().getDeptList2().get(i).getCompanyType() + "");
                            bean.setDeptNumber(entity.getData().getDeptList2().get(i).getDeptNumber());
                            data.add(bean);
                        }
                        break;
                    case "党支部":
                        for (int i = 0; i < entity.getData().getDeptList3().size(); i++) {
                            QueryDeptListChlideBean bean = new QueryDeptListChlideBean();
                            bean.setAbbreviation(entity.getData().getDeptList3().get(i).getAbbreviation());
                            bean.setBeginCreateDt(entity.getData().getDeptList3().get(i).getBeginCreateDt());
                            bean.setBriefIntroduction(entity.getData().getDeptList3().get(i).getBriefIntroduction());
                            bean.setCity(entity.getData().getDeptList3().get(i).getCity());
                            bean.setCompanyCreateSituation(entity.getData().getDeptList3().get(i).getCompanyCreateSituation());
                            bean.setCompanyName(entity.getData().getDeptList3().get(i).getCompanyName());
                            bean.setCompanySituation(entity.getData().getDeptList3().get(i).getCompanySituation());
                            bean.setCompanyType(entity.getData().getDeptList3().get(i).getCompanyType() + "");
                            bean.setDeptNumber(entity.getData().getDeptList3().get(i).getDeptNumber());
                            data.add(bean);
                        }
                        break;
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
    }

    /**
     * 查询分组
     */
    public void queryDeptGroupList() {
        data.clear();
        Log.e("aa", "--------------------title====" + mTitle + "-----------" + MyApplication.getLoginBean().getToken());
        HttpHelper.queryDeptGroupList(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Log.e("aa", "--------------------title===onSucceed=" + succeed);
                Gson gson = new Gson();
                QueryDeptGroupListBean entity = gson.fromJson(succeed, QueryDeptGroupListBean.class);
                for (int i = 0; i < entity.getData().getGroupList().size(); i++) {
                    QueryDeptListChlideBean bean = new QueryDeptListChlideBean();
                    bean.setAbbreviation(entity.getData().getGroupList().get(i).getDeptName());
                    bean.setBeginCreateDt(entity.getData().getGroupList().get(i).getBeginCreateDt());
                    bean.setDeptNumber(entity.getData().getGroupList().get(i).getDeptNumber());
                    data.add(bean);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                Log.e("aa", "--------------------title===onError=" + error);
                ToastUtils.show(error);
            }
        });
    }
}
