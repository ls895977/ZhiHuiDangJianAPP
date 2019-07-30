package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.bean.QueryPartyDynamicPageListBean;
import com.lfc.zhihuidangjianapp.net.http.ApiConstant;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.bean.PartyBuildingBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PartyBuildingAdapter extends BaseQuickAdapter<QueryPartyDynamicPageListBean.DataBean.PartyDynamicListBean.DatasBean, BaseViewHolder> {
    Context mContext;

    public PartyBuildingAdapter(Context context, @Nullable List<QueryPartyDynamicPageListBean.DataBean.PartyDynamicListBean.DatasBean> data) {
        super(R.layout.item_partybuilding, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryPartyDynamicPageListBean.DataBean.PartyDynamicListBean.DatasBean item) {
        Glide.with(mContext).load( item.getThumbnailUrl()).into((RoundedImageView) helper.getView(R.id.item_demonstration_img));
        helper.setText(R.id.item_demonstration_title, item.getTitle())
                .setText(R.id.item_demonstration_context, Html.fromHtml(item.getComment()));

    }
}