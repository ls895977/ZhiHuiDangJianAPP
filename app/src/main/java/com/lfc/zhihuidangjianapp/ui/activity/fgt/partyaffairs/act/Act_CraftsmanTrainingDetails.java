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
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.CraftsmanTrainingDetailsBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apistyledetailBean;
import com.lfc.zhihuidangjianapp.view.HtmlFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工匠培养详情页
 */
public class Act_CraftsmanTrainingDetails extends BaseActivity {
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
        return R.layout.act_craftsmantrainingdetails;
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
        apistudycraftsmandetail();
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
    public void apistudycraftsmandetail() {
        HttpHelper.apistudycraftsmandetail(id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                  CraftsmanTrainingDetailsBean entity = gson.fromJson(succeed, CraftsmanTrainingDetailsBean.class);
                if (entity.getCode() == 0) {
                    deptName.setText(entity.getData().getTitle());
                    remark.setText(entity.getData().getAuthor());
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
