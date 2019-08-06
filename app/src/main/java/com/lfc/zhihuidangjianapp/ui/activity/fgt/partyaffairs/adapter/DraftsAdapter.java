package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.DraftsBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.StrengthenBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class DraftsAdapter extends BaseQuickAdapter<DraftsBean, BaseViewHolder> {

    public DraftsAdapter(@Nullable List<DraftsBean> data) {
        super(R.layout.item_drafts, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DraftsBean item) {
    }
}
