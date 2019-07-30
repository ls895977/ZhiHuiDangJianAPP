package com.lfc.zhihuidangjianapp.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.utlis.ACache;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class Act_Loding extends BaseActivity {
    private ACache aCache;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (aCache.getAsString("loginStatus") != null && aCache.getAsString("loginStatus").equals("yes")) {
                startActivity(new Intent(getContext(), Act_Login.class));
                finish();
            } else {
                startActivity(new Intent(getContext(), Act_WelcomePage.class));
                finish();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.act_loding;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        initImmersionBar(0);
        ButterKnife.bind(this);
        aCache = ACache.get(this);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message message = new Message();
                    message.arg1 = 88;
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void initData() {
    }
}
