package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.demonstration_leadership.fgt.Fgt_LearningExperience;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg.Dilog_Xinde;

import java.util.ArrayList;
/**
 * 学习心得
 */
public class Act_LearningExperience extends BaseActivity implements View.OnClickListener {

    private TextView title[] = new TextView[4];
    private Dilog_Xinde dilog_xinde;
    @Override
    protected int getLayoutId() {
        return R.layout.act_learningexperience;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        title[0] = findViewById(R.id.tv_title1);
        title[1] = findViewById(R.id.tv_title2);
        title[2] = findViewById(R.id.tv_title3);
        title[3] = findViewById(R.id.tv_title4);
        title[0].setOnClickListener(this);
        title[1].setOnClickListener(this);
        title[2].setOnClickListener(this);
        title[3].setOnClickListener(this);
        findViewById(R.id.item_show).setOnClickListener(this);
        dilog_xinde=new Dilog_Xinde(Act_LearningExperience.this,new Dilog_Xinde.OnBackCenter(){
            @Override
            public void onNewXingDe() {//新增学习心得
                startActivity(new Intent(getContext(),Act_WritingExperience.class));
            }

            @Override
            public void onXingDeCaoGao() {//草稿箱
                startActivity(new Intent(getContext(),Act_Drafts.class));
            }
        });
    }
    MyPagerAdapter mAdapter;
    ViewPager vp;
    @Override
    protected void initData() {
        vp = findViewById(R.id.vp);
        mFragments.add(Fgt_LearningExperience.getInstance("0"));
        mFragments.add(Fgt_LearningExperience.getInstance("1"));
        mFragments.add(Fgt_LearningExperience.getInstance("2"));
        mFragments.add(Fgt_LearningExperience.getInstance("4"));
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
        initImmersionBar(0);
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
            case R.id.tv_title4:
                swiche(3);
                vp.setCurrentItem(3);
                break;
            case R.id.item_show://弹框
                dilog_xinde.show();
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
