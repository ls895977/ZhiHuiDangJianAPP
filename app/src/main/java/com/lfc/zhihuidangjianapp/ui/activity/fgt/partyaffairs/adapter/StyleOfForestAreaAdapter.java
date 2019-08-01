package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.StyleOfForestAreaBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class StyleOfForestAreaAdapter extends BaseQuickAdapter<StyleOfForestAreaBean.DataBean.DatasBean, BaseViewHolder> {
    Context mContext;

    public StyleOfForestAreaAdapter(Context context, @Nullable List<StyleOfForestAreaBean.DataBean.DatasBean> data) {
        super(R.layout.item_styleofforestarea, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StyleOfForestAreaBean.DataBean.DatasBean item) {
        Glide.with(mContext).load(item.getThumbnailUrl()).into((RoundedImageView) helper.getView(R.id.item_demonstration_img));
        helper.setText(R.id.item_demonstration_title, item.getDeptName())
                .setText(R.id.item_demonstration_context, Html.fromHtml(item.getComment()));
    }
}

