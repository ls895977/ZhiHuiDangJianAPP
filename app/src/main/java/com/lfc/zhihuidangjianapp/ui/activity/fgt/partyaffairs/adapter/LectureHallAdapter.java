package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.CraftsmanTrainingBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.LectureHallBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class LectureHallAdapter  extends BaseQuickAdapter<LectureHallBean, BaseViewHolder> {

    public LectureHallAdapter( @Nullable List<LectureHallBean> data) {
        super(R.layout.item_lecturehall, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LectureHallBean item) {
    }
}
