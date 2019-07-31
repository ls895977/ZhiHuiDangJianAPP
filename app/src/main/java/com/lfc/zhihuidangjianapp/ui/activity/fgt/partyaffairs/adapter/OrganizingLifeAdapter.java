package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.OrganizingLifeBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class OrganizingLifeAdapter extends BaseQuickAdapter<OrganizingLifeBean.DataBean.DatasBean, BaseViewHolder> {
    Context mContext;

    public OrganizingLifeAdapter(Context context, @Nullable List<OrganizingLifeBean.DataBean.DatasBean> data) {
        super(R.layout.item_organizinglife, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrganizingLifeBean.DataBean.DatasBean item) {
        helper.setText(R.id.item_demonstration_title, item.getTitle())
                .setText(R.id.item_demonstration_context, Html.fromHtml(item.getComment()));
        Glide.with(mContext).load(item.getThumbnailUrl()).into((RoundedImageView) helper.getView(R.id.item_demonstration_img));
    }
}
