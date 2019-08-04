package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.CraftsmanTrainingBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.StrengthenBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class GongJIangAdapter extends BaseQuickAdapter<StrengthenBean.DataBean.CraftsmanBean, BaseViewHolder> {

    public GongJIangAdapter( @Nullable List<StrengthenBean.DataBean.CraftsmanBean> data) {
        super(R.layout.item_gongjang, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StrengthenBean.DataBean.CraftsmanBean item) {
        helper.setText(R.id.title, " | "+item.getTitle());
        RequestOptions options=new RequestOptions();
        options.error(R.mipmap.icon_test2);
        Glide.with(mContext).load(item.getThumbnailUrl()).apply(options).into((RoundedImageView) helper.getView(R.id.item_demonstration_img));
    }
}
