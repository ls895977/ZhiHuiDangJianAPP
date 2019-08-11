package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apistudyopenClassdetailBean;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 写心得
 */
public class Act_WritingExperience extends BaseActivity {

    @BindView(R.id.ed_title)
    EditText edTitle;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_context)
    EditText edContext;

    @Override
    protected int getLayoutId() {
        return R.layout.act_writingexperience;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imgBack, R.id.save_caogao, R.id.item_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.save_caogao://保存为草稿
                ifPass=2;

                break;
            case R.id.item_commit://提交
                ifPass=1;
                apistudystudydetail();
                break;
        }
    }
    private int ifPass=1;
    /**
     * 学习心得保存
     * token: //
     *    "studyStrongBureauId": 32, // 主键(编辑时候传主键，新增时候不传)
     *     "title": "发挥示范引领职能  以改革创新促发展", // 标题
     *     comment // 内容
     *     ifPass // 1正式 2草稿
     */
    public void apistudystudydetail() {
        if(TextUtils.isEmpty(edTitle.getText().toString())){
            ToastUtils.show("请输入标题！");
            return;
        }
        if(TextUtils.isEmpty(edContext.getText().toString())){
            ToastUtils.show("请输入内容！");
            return;
        }
        loding.show();
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("ifPass",ifPass+"");
        hashMap.put("title",edTitle.getText().toString());
        hashMap.put("comment",edContext.getText().toString());
        HttpHelper.apistudystudydetail(hashMap, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
    }

}
