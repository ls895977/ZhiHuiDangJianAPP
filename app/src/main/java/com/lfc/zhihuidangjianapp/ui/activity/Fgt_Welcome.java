package com.lfc.zhihuidangjianapp.ui.activity;


import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseFragment;

public class Fgt_Welcome extends BaseFragment {
    ImageView myImage;

    @Override
    protected int getLayoutId() {
        return R.layout.fgt_welcome;
    }

    @Override
    protected void initView(View rootView) {
        myImage = rootView.findViewById(R.id.myImage);
    }

    private String status = "";

    @Override
    protected void initData() {
        status = getArguments().getString("page");
        switch (status) {
            case "0":
                Glide.with(getContext()).load(R.mipmap.icon_d1).into(myImage);
                break;
            case "1":
                Glide.with(getContext()).load(R.mipmap.icon_d2).into(myImage);
                break;
            case "2":
                Glide.with(getContext()).load(R.mipmap.icon_d3).into(myImage);
                break;
            case "3":
                Glide.with(getContext()).load(R.mipmap.icon_d4).into(myImage);
                break;
        }
    }
}
