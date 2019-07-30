package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.bean.queryUserListByDeptNumber;

import java.util.List;

public class QueryUserListByDeptNumberAdapter extends BaseQuickAdapter<queryUserListByDeptNumber .DataBean.SysUserListBean, BaseViewHolder> {
    Context mContext;

    public QueryUserListByDeptNumberAdapter(Context context, @Nullable List<queryUserListByDeptNumber .DataBean.SysUserListBean> data) {
        super(R.layout.item_queryuserlistbydeptnumber, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, queryUserListByDeptNumber .DataBean.SysUserListBean item) {
        helper.setText(R.id.name,item.getDisplayName())
                .setText(R.id.zhiwu,item.getSubordinatePartyGroup())
                .setText(R.id.phone,item.getPhone()+"");
    }
}