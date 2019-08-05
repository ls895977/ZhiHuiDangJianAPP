package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apistudyopenClassdetailBean;
import com.lfc.zhihuidangjianapp.view.MyVideo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 林草大讲堂详情页
 */
public class Act_LectureDetails extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.myVideo)
    MyVideo myVideo;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_context)
    TextView tvContext;

    @Override
    protected int getLayoutId() {
        return R.layout.act_lecturedetails;
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

    }

    private String id = "";

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        apistudyopenClassdetail(id);
    }

    /**
     * 大讲堂详情
     */
    public void apistudyopenClassdetail(String id) {
        HttpHelper.apistudyopenClassdetail(id + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                apistudyopenClassdetailBean entity = gson.fromJson(succeed, apistudyopenClassdetailBean.class);
                if (entity.getCode() == 0) {
                    myVideo.setUp(entity.getData().getAttachmentUrl(), "");
                    Glide.with(getApplicationContext()).load(entity.getData().getThumbnailUrl()).into(myVideo.thumbImageView);
                    title.setText(entity.getData().getTitle());
                    tvContext.setText(Html.fromHtml(entity.getData().getComment()));
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
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
}
