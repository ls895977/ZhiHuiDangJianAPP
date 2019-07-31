package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.MyBaseDialog;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.appApiinsertTransferOrganizationalRelationsBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.adapter.MuneAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.bean.MuneBean;
import com.lfc.zhihuidangjianapp.utlis.Debug;

import java.util.ArrayList;
import java.util.List;

public class MuneDilog extends MyBaseDialog {
    public MuneDilog(Context context) {
        super(context);
    }

    public onBackItem onBackItem;

    public void setOnBackItem(MuneDilog.onBackItem onBackItem) {
        this.onBackItem = onBackItem;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dilog_mune;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.TOP);
    }

    private RecyclerView myReclerView;
    private MuneAdapter adapter;
    List<MuneBean.DataBean> data = new ArrayList<>();
    private int indext = 0;

    @Override
    protected void initView() {
        myReclerView = getView(R.id.myRecyclerview);
        myReclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new MuneAdapter(data, getContext());
        myReclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            data.get(indext).setStatus(false);
            data.get(position).setStatus(true);
            indext = position;
            adapter.notifyDataSetChanged();
            onBackItem.onBackClick(data.get(position).getKey() + "", data.get(position).getValue());
        });
        apiorglifetopic();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onViewClick(View v) {
    }

    public void apiorglifetopic() {
        HttpHelper.apiorglifetopic(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                MuneBean entity = gson.fromJson(succeed, MuneBean.class);
                if (entity.getCode() == 0) {
                    data.addAll(entity.getData());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                ToastUtils.show(error);
            }
        });
    }

    public interface onBackItem {
        void onBackClick(String key, String vlue);
    }
}
