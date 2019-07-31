package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.adapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.bean.MuneBean;

import java.util.List;

public class MuneAdapter extends BaseQuickAdapter<MuneBean.DataBean, BaseViewHolder> {
    private Context context;
    public MuneAdapter(@Nullable List<MuneBean.DataBean> data, Context context1) {
        super(R.layout.item_mune, data);
        this.context = context1;
    }
    @Override
    protected void convert(BaseViewHolder helper,MuneBean.DataBean item) {
        TextView room_title = helper.getView(R.id.room_title);
        room_title.setText(item.getValue());
        room_title.setSelected(item.isStatus());
        helper.addOnClickListener(R.id.room_title);
    }
}
