package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.StrengthenBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class XinDeAdapter extends BaseQuickAdapter<StrengthenBean.DataBean.StudyBean, BaseViewHolder> {

    public XinDeAdapter(@Nullable List<StrengthenBean.DataBean.StudyBean> data) {
        super(R.layout.item_xinde, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StrengthenBean.DataBean.StudyBean item) {
        RequestOptions options=new RequestOptions();
        options.error(R.mipmap.icon_test2);
        Glide.with(mContext).load(item.getThumbnailUrl()).apply(options).into((RoundedImageView) helper.getView(R.id.item_demonstration_img));
    }
}
