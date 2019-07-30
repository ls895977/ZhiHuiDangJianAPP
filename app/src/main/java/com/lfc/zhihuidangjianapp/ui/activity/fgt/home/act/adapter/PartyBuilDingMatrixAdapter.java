package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.PartyBuilDingMatrixBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.QueryDeptListChlideBean;

import java.util.List;

public class PartyBuilDingMatrixAdapter extends BaseQuickAdapter<QueryDeptListChlideBean, BaseViewHolder> {
    public PartyBuilDingMatrixAdapter(@Nullable List<QueryDeptListChlideBean> data) {
        super(R.layout.item_partybuildingmatrix, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryDeptListChlideBean item) {
        helper.setText(R.id.item_name,item.getCompanyName());

    }
}