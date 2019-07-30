package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.Party_membershipDuesBean;

import java.util.List;

public class MyPartyPaymentHisPageListAdapter extends BaseQuickAdapter<Party_membershipDuesBean.DataBean.PartyPaymentHisListBean.DatasBean, BaseViewHolder> {
    private Context mContext;

    public MyPartyPaymentHisPageListAdapter(Context context, @Nullable List<Party_membershipDuesBean.DataBean.PartyPaymentHisListBean.DatasBean> data) {
        super(R.layout.item_mypartypaymenthispagelist, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Party_membershipDuesBean.DataBean.PartyPaymentHisListBean.DatasBean item) {
        TextView colortv = helper.getView(R.id.item_status);
        if (item.getPayStatus() == 0) {//0已缴纳1未缴纳
            helper.setText(R.id.item_context, item.getPayYearMonth() + "日已缴纳党员")
                    .setText(R.id.item_status, "已缴纳");
            colortv.setTextColor(Color.parseColor("#429B35"));
        } else {
            helper.setText(R.id.item_context, item.getPayYearMonth() + "日未缴纳党员")
                    .setText(R.id.item_status, "未缴纳");
            colortv.setTextColor(Color.parseColor("#E24B1D"));
        }
        helper.setText(R.id.price, item.getMoney() + "元");
        helper.addOnClickListener(R.id.item_clcik);
    }
}
