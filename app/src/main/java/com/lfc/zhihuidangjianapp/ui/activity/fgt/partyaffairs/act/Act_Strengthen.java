package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.base.GlideImageLoader;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.StrengthenBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter.GongJIangAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter.XinDeAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 党务工作学习强局
 */
public class Act_Strengthen extends BaseActivity {
    @BindView(R.id.imgBanner)
    Banner imgBanner;
    @BindView(R.id.gongjiang_RecyclerView)
    RecyclerView gongjiangRecyclerView;
    @BindView(R.id.xuexingde_RecyclerView)
    RecyclerView xuexingde_RecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.act_strengthen;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gongjiangRecyclerView.setLayoutManager(linearLayoutManager);
        xuexingde_RecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    @Override
    protected void initData() {
        apistudyindex();
    }

    List<String> image = new ArrayList<>();

    /**
     * 学习强局首页
     */
    public void apistudyindex() {
        image.clear();
        HttpHelper.apistudyindex(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                StrengthenBean entity = gson.fromJson(succeed, StrengthenBean.class);
                if (entity.getCode() == 0) {
                    image.add(entity.getData().getOpenClass().getAttachmentUrl());
                    //设置图片加载器
                    imgBanner.setImageLoader(new GlideImageLoader());
                    imgBanner.setImages(image).start();
                    imgBanner.setOnBannerListener(position -> {
                    });
                    GongJIangAdapter adapter = new GongJIangAdapter(entity.getData().getCraftsman());
                    gongjiangRecyclerView.setAdapter(adapter);
                    XinDeAdapter adapter1 = new XinDeAdapter(entity.getData().getStudy());
                    xuexingde_RecyclerView.setAdapter(adapter1);

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

    @OnClick({R.id.imgBack, R.id.bt_gongkaike, R.id.bt_gongjiangpeiyang, R.id.bt_xuexixingde})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.bt_gongkaike:
                startActivity(new Intent(getContext(),Act_LectureHall.class));
                break;
            case R.id.bt_gongjiangpeiyang:
                startActivity(new Intent(getContext(), Act_CraftsmanTraining.class));
                break;
            case R.id.bt_xuexixingde:

                break;
        }
    }
}
