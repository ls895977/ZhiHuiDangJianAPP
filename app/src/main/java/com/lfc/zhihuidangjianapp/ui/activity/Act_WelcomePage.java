package com.lfc.zhihuidangjianapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
import com.lfc.zhihuidangjianapp.utlis.ACache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 欢迎页
 */
public class Act_WelcomePage extends BaseActivity {
    @BindView(R.id.myViewPager)
    ViewPager myViewPager;
    @BindView(R.id.start_Main)
    TextView startMain;
    TextView tab[] = new TextView[4];
    LinearLayout llmain;
    @Override
    protected int getLayoutId() {
        return R.layout.act_welcomepage;
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
        tab[0] = findViewById(R.id.tab1);
        tab[1] = findViewById(R.id.tab2);
        tab[2] = findViewById(R.id.tab3);
        tab[3] = findViewById(R.id.tab4);
        llmain = findViewById(R.id.ll_Main);
        startMain.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), Act_Login.class));
            finish();
        });
    }

    List<BaseFragment> datas = new ArrayList<>();
    private ACache aCache;

    @Override
    protected void initData() {
        aCache = ACache.get(this);
        aCache.put("loginStatus", "yes");
        for (int i = 0; i < 4; i++) {
            Fgt_Welcome fgt_welcome1 = new Fgt_Welcome();
            Bundle bundle = new Bundle();
            bundle.putString("page", i + "");
            fgt_welcome1.setArguments(bundle);
            datas.add(fgt_welcome1);
        }
        myViewPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                choseStatus(i);
                if (i == 3) {
                    llmain.setVisibility(View.GONE);
                    startMain.setVisibility(View.VISIBLE);
                } else {
                    llmain.setVisibility(View.VISIBLE);
                    startMain.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        choseStatus(0);
    }

    private class pagerAdapter extends FragmentPagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int i) {

            return datas.get(i);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
    private int indext = 0;
    public void choseStatus(int status) {
        tab[indext].setSelected(false);
        tab[status].setSelected(true);
        indext = status;
    }
}
