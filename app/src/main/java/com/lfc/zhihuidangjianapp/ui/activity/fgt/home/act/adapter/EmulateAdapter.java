package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.EmulateBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class EmulateAdapter extends BaseQuickAdapter<EmulateBean.DataBean.StudyStrongBureauListBean.DatasBean, BaseViewHolder> {
    Context mContext;

    public EmulateAdapter(Context context, @Nullable List<EmulateBean.DataBean.StudyStrongBureauListBean.DatasBean> data) {
        super(R.layout.item_emulate, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, EmulateBean.DataBean.StudyStrongBureauListBean.DatasBean item) {
        helper.setText(R.id.item_demonstration_title, item.getTitle());
        helper.setText(R.id.item_demonstration_context, Html.fromHtml(item.getComment()));
        Glide.with(mContext).load(item.getThumbnailUrl()).into((RoundedImageView)helper.getView(R.id.item_demonstration_img));
    }
}