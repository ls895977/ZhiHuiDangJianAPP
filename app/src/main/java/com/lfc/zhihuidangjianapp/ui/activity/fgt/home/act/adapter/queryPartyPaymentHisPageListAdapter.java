package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryUnPaidPartyPaymentHisPageListBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.queryPartyPaymentHisPageListBean;

import java.util.List;

public class queryPartyPaymentHisPageListAdapter extends BaseQuickAdapter<queryPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean.DatasBean, BaseViewHolder> {
    public queryPartyPaymentHisPageListAdapter(@Nullable List<queryPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean.DatasBean> data) {
        super(R.layout.item_querypartypaymenthispagelist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, queryPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean.DatasBean item) {
            helper.setText(R.id.item_name,item.getName())
                    .setText(R.id.item_time,item.getPayYearMonth())
                    .setText(R.id.item_price,item.getMoney()+"元");

    }
}