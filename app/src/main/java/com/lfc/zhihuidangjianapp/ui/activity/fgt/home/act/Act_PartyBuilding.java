package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.fgt.Fgt_Demonstration_Leadership;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.fgt.Fgt_PartyBuilding;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 党建动态
 */
public class Act_PartyBuilding extends BaseActivity implements View.OnClickListener {

    private TextView title[] = new TextView[4];

    @Override
    protected int getLayoutId() {
        return R.layout.act_partybuilding;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.imgBack).setOnClickListener(this);
        title[0] = findViewById(R.id.tv_title1);
        title[1] = findViewById(R.id.tv_title2);
        title[2] = findViewById(R.id.tv_title3);
        title[0].setOnClickListener(this);
        title[1].setOnClickListener(this);
        title[2].setOnClickListener(this);
    }

    MyPagerAdapter mAdapter;
    ViewPager vp;

    @Override
    protected void initData() {
        mFragments.add(Fgt_PartyBuilding.getInstance("0"));
        mFragments.add(Fgt_PartyBuilding.getInstance("1"));
        mFragments.add(Fgt_PartyBuilding.getInstance("1"));
        vp = findViewById(R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        vp.setCurrentItem(0);
        swiche(0);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                swiche(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tv_title1:
                swiche(0);
                vp.setCurrentItem(0);
                break;
            case R.id.tv_title2:
                swiche(1);
                vp.setCurrentItem(1);
                break;
            case R.id.tv_title3:
                swiche(2);
                vp.setCurrentItem(2);
                break;
        }
    }

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
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

    private int indext = 0;

    public void swiche(int page) {
        title[indext].setSelected(false);
        title[page].setSelected(true);
        indext = page;
    }
}
