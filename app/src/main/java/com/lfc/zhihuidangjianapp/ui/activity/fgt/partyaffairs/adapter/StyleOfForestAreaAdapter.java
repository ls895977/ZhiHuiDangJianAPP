package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;
import android.content.Context;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.StyleOfForestAreaBean;
import java.util.List;
public class StyleOfForestAreaAdapter extends BaseQuickAdapter<StyleOfForestAreaBean, BaseViewHolder> {
    Context mContext;

    public StyleOfForestAreaAdapter(Context context, @Nullable List<StyleOfForestAreaBean> data) {
        super(R.layout.item_styleofforestarea, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, StyleOfForestAreaBean item) {
    }
}

