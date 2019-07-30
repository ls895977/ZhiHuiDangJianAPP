package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act;

import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * 一建代缴费
 */
public class Act_PartyMembershipDues extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_partymembershipdues;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.pay).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.pay://支付完成
                finish();
                break;
            case R.id.cancel://取消支付
                finish();
                break;
        }
    }
}
