package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.dlg;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.MyBaseDialog;

/**
 * 拍照选择
 */
public class Dilog_Xinde extends MyBaseDialog {
    public OnBackCenter onBackTime;
    public Dilog_Xinde(Context context, OnBackCenter onBackTime1) {
        super(context);
        this.onBackTime = onBackTime1;
    }
    @Override
    protected int initLayoutId() {
        return R.layout.dlg_xingde;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.new_xinde);
        setOnClickListener(R.id.new_xindecaogao);
        setOnClickListener(R.id.xinde_cancel);
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.xinde_cancel:
                dismiss();
                break;
            case R.id.new_xinde://新增学习心得
                dismiss();
                onBackTime.onNewXingDe();
                break;
            case R.id.new_xindecaogao://草稿箱
                dismiss();
                onBackTime.onXingDeCaoGao();
                break;
        }
    }

    public interface OnBackCenter {
        void onNewXingDe();

        void onXingDeCaoGao();
    }
}
