package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.apiorglifedetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 组织生活详情
 */
public class Act_OrganizingLifeDeatle extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.studyDate)
    TextView studyDate;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.studyDays)
    TextView studyDays;
    @BindView(R.id.hostPerson)
    TextView hostPerson;
    @BindView(R.id.partyMembershipCount)
    TextView partyMembershipCount;
    @BindView(R.id.participants)
    TextView participants;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.studyForm)
    TextView studyForm;
    @BindView(R.id.studyResult)
    TextView studyResult;

    @Override
    protected int getLayoutId() {
        return R.layout.act_organizinglifedeatle;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        ButterKnife.bind(this);
    }

    private String id;

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        apiorglifedetail(id);
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
    public void apiorglifedetail(String id) {
        HttpHelper.apiorglifedetail(id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                apiorglifedetailBean entity = gson.fromJson(succeed, apiorglifedetailBean.class);
                if (entity.getCode() == 0) {
                    title.setText(entity.getData().getTitle());
                    author.setText(entity.getData().getAuthor() + " " + entity.getData().getReleaseDate());
                    studyDate.setText(entity.getData().getStudyDate());
                    address.setText(entity.getData().getAddress() + "");
                    studyDays.setText(entity.getData().getStudyDays() + "");
                    hostPerson.setText(entity.getData().getHostPerson() + "");
                    partyMembershipCount.setText(entity.getData().getPartyMembershipCount()+"");
                    participants.setText(entity.getData().getParticipants() + "");
                    comment.setText(Html.fromHtml(entity.getData().getComment()));
                    studyForm.setText(entity.getData().getStudyForm() + "");
                    studyResult.setText(entity.getData().getStudyResult() + "");
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
