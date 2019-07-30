package com.lfc.zhihuidangjianapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.rebound.SpringConfig;
import com.hjq.toast.ToastUtils;
import com.jpeng.jpspringmenu.MenuListener;
import com.jpeng.jpspringmenu.SpringMenu;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.base.BaseFragmentAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.Fgt_Home;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.Fgt_PartyAffairs;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.Fgt_Personal;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.personal.act.Act_SetUpc;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.personal.act.Act_WeeklyReport;
import com.lfc.zhihuidangjianapp.widget.NoScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Act_Main extends BaseActivity implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener, MenuListener {
    @BindView(R.id.vp_home_pager)
    NoScrollViewPager vpHomePager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView bvHomeNavigation;
    @BindView(R.id.v4_drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.imgHead)
    ImageView imgHead;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;

    private BaseFragmentAdapter<Fragment> mPagerAdapter;
    private SpringMenu menu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        initImmersionBar(1);
        ButterKnife.bind(this);
        vpHomePager.addOnPageChangeListener(this);
//        mViewPager.setPageTransformer(true, new ZoomFadePageTransformer());

        // 不使用图标默认变色
        bvHomeNavigation.setItemIconTintList(null);
        bvHomeNavigation.setOnNavigationItemSelectedListener(this);
//        sideSlideMenu();
    }

    private void sideSlideMenu() {
        menu = new SpringMenu(this, R.layout.menu_sideslip_menu);

        /**
         * 设置弹性和摩擦
         * Tension弹性，20即可
         * Friction摩擦 数值越大打开关闭速度越慢0-100  3就好看
         * friction : 2f -4f 是合适值的范围,太小速度很快,弹性时间长,太大速度会很慢
         * friction的值一定不能为0,否则会无限反弹,根据rebound api的定义
         *
         */

        //menu.setMenuSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(20, 3));
        //menu.setChildSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(20, 5));

        //添加menu监听
        menu.setMenuListener(this);
        menu.setChildSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(20, 3));
        // 内容页变暗的效果
        menu.setFadeEnable(true);
        //面板在左边
        menu.setDirection(SpringMenu.DIRECTION_LEFT);
        // 允许菜单开始拖动的距离
        menu.setDragOffset(0.1f);

    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(new Fgt_Home());
        mPagerAdapter.addFragment(new Fgt_PartyAffairs());
        mPagerAdapter.addFragment(new Fgt_Personal());
        vpHomePager.setAdapter(mPagerAdapter);

        // 限制页面数量
        vpHomePager.setOffscreenPageLimit(mPagerAdapter.getCount());
//        drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
//        drawerLayout.setScrimColor(Color.TRANSPARENT);
//        drawerLayout.setDrawerShadow();
        imgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("哈哈哈");
            }
        });
    }

    private void showDrawerLayout() {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                //mViewPager.setCurrentItem(0);
                //mViewPager.setCurrentItem(0, false);
                // 如果切换的是相邻之间的 Item 就显示切换动画，如果不是则不要动画
                vpHomePager.setCurrentItem(0, vpHomePager.getCurrentItem() == 1);
                return true;
            case R.id.home_supplement_sheet:
                //mViewPager.setCurrentItem(1);
                //mViewPager.setCurrentItem(1, false);
                vpHomePager.setCurrentItem(1, vpHomePager.getCurrentItem() == 0 || vpHomePager.getCurrentItem() == 2);
                return true;
            case R.id.invitation:
                //mViewPager.setCurrentItem(2);
                //mViewPager.setCurrentItem(2, false);
                vpHomePager.setCurrentItem(2, vpHomePager.getCurrentItem() == 1);
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
//        mPagerAdapter.notifyDataSetChanged();
        switch (position) {
            case 0:
                bvHomeNavigation.setSelectedItemId(R.id.menu_home);
                break;
            case 1:
                bvHomeNavigation.setSelectedItemId(R.id.home_supplement_sheet);
                break;
            case 2:
                bvHomeNavigation.setSelectedItemId(R.id.invitation);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    protected void onDestroy() {
        vpHomePager.removeOnPageChangeListener(this);
        vpHomePager.setAdapter(null);
        bvHomeNavigation.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }


    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出时的时间
    private long mExitTime;

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            activityManagerUtil.finishAllActivity();
//            System.exit(0);
        }

    }

    /**
     * 重写触摸方法
     * @param ev
     * @return
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return menu.dispatchTouchEvent(ev);
//    }


    /**
     * 菜单打开回调
     */
    @Override
    public void onMenuOpen() {

    }

    /**
     * 菜单关闭回调
     */

    @Override
    public void onMenuClose() {

    }

    /**
     * @param value
     * @param bouncing
     */
    @Override
    public void onProgressUpdate(float value, boolean bouncing) {

    }

    @OnClick(R.id.relative1)
    public void onRelative1Clicked() {
        startActivity(Act_SetUpc.class);
    }

    @OnClick(R.id.relative2)
    public void onRelative2Clicked() {

    }

    @OnClick(R.id.relative3)
    public void onRelative3Clicked() {
        startActivity(Act_WeeklyReport.class);
    }

    @OnClick(R.id.relative4)
    public void onRelative4Clicked() {
    }
}
