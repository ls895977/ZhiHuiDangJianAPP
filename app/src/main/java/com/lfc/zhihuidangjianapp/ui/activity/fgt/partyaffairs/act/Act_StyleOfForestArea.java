package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.fgt.Fgt_StyleOfForestArea;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 林区风采
 */
public class Act_StyleOfForestArea extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.tl_4)
    SlidingTabLayout tabLayout_4;
    @BindView(R.id.vp)
    ViewPager vp;
    private String[] title = {"先进基层党组织", "优秀共产党员", "优秀党务工作者", "优秀党建联络员"};

    @Override
    protected int getLayoutId() {
        return R.layout.act_styleofforestarea;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.imgBack).setOnClickListener(this);
    }
    MyPagerAdapter mAdapter;
    @Override
    protected void initData() {
        for (int i = 0; i < title.length; i++) {
            mFragments.add(Fgt_StyleOfForestArea.getInstance(i + ""));
        }
        vp = findViewById(R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tabLayout_4.setViewPager(vp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
