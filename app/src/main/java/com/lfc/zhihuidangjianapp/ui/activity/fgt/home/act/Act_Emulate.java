package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.fgt.Fgt_Demonstration_Leadership;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.fgt.Fgt_Emulate;
import com.lfc.zhihuidangjianapp.widget.MyListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 学习强局
 */
public class Act_Emulate extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.textTitle)
    TextView textTitle;
    private TextView title[] = new TextView[3];
    ViewPager vp;

    @Override
    protected int getLayoutId() {
        return R.layout.act_emulate;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initImmersionBar(0);
        textTitle.setText("学习强局");
        findViewById(R.id.imgBack).setOnClickListener(v -> finish());
        vp = findViewById(R.id.vp);
        title[0] = findViewById(R.id.tv_title1);
        title[1] = findViewById(R.id.tv_title2);
        title[2] = findViewById(R.id.tv_title3);
        title[0].setOnClickListener(this);
        title[1].setOnClickListener(this);
        title[2].setOnClickListener(this);
    }

    private MyPagerAdapter mAdapter;

    @Override
    protected void initData() {
        mFragments.add(Fgt_Emulate.getInstance("0"));
        mFragments.add(Fgt_Emulate.getInstance("1"));
        mFragments.add(Fgt_Emulate.getInstance("2"));
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

    private int indext = 0;

    public void swiche(int page) {
        title[indext].setSelected(false);
        title[page].setSelected(true);
        indext = page;
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
}
