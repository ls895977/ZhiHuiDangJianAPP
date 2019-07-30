package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.adapter.QueryUserListByDeptNumberAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.QueryDeptDetailBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.queryUserListByDeptNumber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 党建矩阵详情
 */
public class Act_PartyBuilDingMatrixDetails extends BaseActivity {
    @BindView(R.id.TitleDetailsBack)
    ImageView TitleDetailsBack;
    @BindView(R.id.TitleDetail_title)
    TextView TitleDetail_title;
    @BindView(R.id.partybuildingmatrix_context)
    TextView partybuildingmatrix_context;
    @BindView(R.id.myRecyclerview)
    RecyclerView myRecyclerview;
    private String deptNumber = "";

    @Override
    protected int getLayoutId() {
        return R.layout.act_partybuildingmatrixdetails;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    private String title, context;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.TitleDetailsBack).setOnClickListener(v -> finish());
        deptNumber = getIntent().getStringExtra("deptNumber");
        myRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        queryPartyDynamicDetail();
        queryUserListByDeptNumber();
    }

    /**
     * 党支部详情
     */
    public void queryPartyDynamicDetail() {
        HttpHelper.queryDeptDetail(deptNumber, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                QueryDeptDetailBean entity = gson.fromJson(succeed, QueryDeptDetailBean.class);
                if (entity.getCode() == 0) {
                    if (entity.getData().getPartyDynamic() != null) {
                        partybuildingmatrix_context.setText(Html.fromHtml(entity.getData().getPartyDynamic().getComment()));
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

    private QueryUserListByDeptNumberAdapter adapter;
    private List<queryUserListByDeptNumber.DataBean.SysUserListBean> datas = new ArrayList<>();

    /**
     * 根据部门number查询人员信息
     */
    public void queryUserListByDeptNumber() {
        HttpHelper.queryUserListByDeptNumber(deptNumber, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                queryUserListByDeptNumber entity = gson.fromJson(succeed, queryUserListByDeptNumber.class);
                if (entity.getCode() == 0) {
                    for (int i = 0; i < entity.getData().getSysUserList().size(); i++) {
                        datas.add(entity.getData().getSysUserList().get(i));
                    }
                    if (adapter == null) {
                        adapter = new QueryUserListByDeptNumberAdapter(getContext(), datas);
                        myRecyclerview.setAdapter(adapter);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
