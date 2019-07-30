package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryUnPaidPartyPaymentHisPageListBean;

import java.util.List;

/**
 * 分页查询党费缴费记录信息-本月未缴列表
 */
public class QueryUnPaidPartyPaymentHisPageListAdapter extends BaseQuickAdapter<QueryUnPaidPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean, BaseViewHolder> {
    private Context mContext;

    public QueryUnPaidPartyPaymentHisPageListAdapter(Context context, @Nullable List<QueryUnPaidPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean> data) {
        super(R.layout.item_queryunpaidpartypaymenthispagelist, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryUnPaidPartyPaymentHisPageListBean.DataBean.UnPaidPartyPaymentHisListBean item) {
        helper.setText(R.id.item_name, item.getName())
                .setText(R.id.item_yearMoth, item.getPayYearMonth())
                .setText(R.id.item_money, item.getMoney()+"元");
        helper.addOnClickListener(R.id.item_bt_daijiao);
    }
}
