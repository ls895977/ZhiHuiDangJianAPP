package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apistyledetailBean;
import com.lfc.zhihuidangjianapp.utlis.Debug;
import com.lfc.zhihuidangjianapp.view.HtmlFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 详情页
 */
public class Act_StyleOfForestAreaDetails extends BaseActivity {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.deptName)
    TextView deptName;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.myWebView)
    WebView myWebView;
    private String id = "";

    @Override
    protected int getLayoutId() {
        return R.layout.act_styleofforestareadetails;
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
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void initData() {
        apistyledetail();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();
    }


    /**
     * 查看组织生活详情信息
     */
    public void apistyledetail() {
        HttpHelper.apistyledetail(id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                apistyledetailBean entity = gson.fromJson(succeed, apistyledetailBean.class);
                if (entity.getCode() == 0) {
                    deptName.setText(entity.getData().getDeptName());
                    remark.setText(entity.getData().getRemark());
                    myWebView.loadDataWithBaseURL(null, HtmlFormat.getNewContent(entity.getData().getComment()),
                            "text/html", "utf-8", null);

                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
    }
}
