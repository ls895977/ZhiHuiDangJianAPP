package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.adapter;
import android.content.Context;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.bean.OrganizingLifeBean;
import java.util.List;

public class OrganizingLifeAdapter extends BaseQuickAdapter<OrganizingLifeBean, BaseViewHolder> {
    Context mContext;

    public OrganizingLifeAdapter(Context context, @Nullable List<OrganizingLifeBean> data) {
        super(R.layout.item_organizinglife, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrganizingLifeBean item) {
    }
}
