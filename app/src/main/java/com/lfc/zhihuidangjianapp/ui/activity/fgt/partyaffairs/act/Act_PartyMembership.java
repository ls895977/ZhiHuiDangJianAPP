package com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.appApiinsertTransferOrganizationalRelationsBean;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.bean.appApiqueryTransferOrganizationalRelationsDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 党员关系转移
 */
public class Act_PartyMembership extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.min_hader)
    CircleImageView minHader;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.nation)
    TextView nation;
    @BindView(R.id.birth)
    TextView birth;
    @BindView(R.id.educational)
    TextView educational;
    @BindView(R.id.party)
    TextView party;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.input_text)
    EditText inputText;

    @Override
    protected int getLayoutId() {
        return R.layout.act_partymembership;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    List<String> data = new ArrayList<>();

    @Override
    protected void initView() {
        initImmersionBar(0);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(Act_PartyMembership.this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
        commit.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * 选择条目Item会调用此方法
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyDeptName = entity.getData().getToDeptMap().get(position).getDeptId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                /**
                 * 这个一直没有触发，我也不知道什么时候被触发。
                 * 在官方的文档上说明，为back的时候触发，但是无效，可能需要特定的场景
                 */
            }
        });
    }

    SpinnerAdapter spinnerAdapter;

    @Override
    protected void initData() {
        appApiqueryTransferOrganizationalRelationsDetail();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.commit://提交
                switch (entity.getData().getStatus()) {
                    case 1:
                        appApiinsertTransferOrganizationalRelations();
                        break;
                    case 2:
                        ToastUtils.show("申请中");
                        break;
                    case 3:
                        ToastUtils.show("申请成功");
                        break;
                }
                break;
        }
    }

    appApiqueryTransferOrganizationalRelationsDetailBean entity;

    /**
     * 党员关系转移详情
     */
    public void appApiqueryTransferOrganizationalRelationsDetail() {
        HttpHelper.appApiqueryTransferOrganizationalRelationsDetail(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                entity = gson.fromJson(succeed, appApiqueryTransferOrganizationalRelationsDetailBean.class);
                if (entity.getCode() == 0) {
                    Glide.with(getContext()).load(entity.getData().getHeadImg()).into(minHader);
                    name.setText(entity.getData().getName());
                    if (entity.getData().getGender() == 1) {
                        gender.setText("男");
                    } else {
                        gender.setText("女");
                    }
                    birth.setText(entity.getData().getBirth());
                    educational.setText(entity.getData().getEducational());
                    party.setText(entity.getData().getParty());
                    for (int i = 0; i < entity.getData().getToDeptMap().size(); i++) {
                        data.add(entity.getData().getToDeptMap().get(i).getDeptName());
                    }
                    spinnerAdapter = new ArrayAdapter<String>(Act_PartyMembership.this,
                            R.layout.item_textview,
                            R.id.text1,
                            data);
                    // 设置数据方面，可以把Spinner下拉列表控件想象成ListView
                    spinner.setAdapter(spinnerAdapter);
                    switch (entity.getData().getStatus()) {
                        case 1:
                            commit.setText("确认申请");
                            break;
                        case 2:
                            commit.setText("申请中");
                            break;
                        case 3:
                            commit.setText("申请成功");
                            break;
                    }
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

    private String applyDeptName = "";

    /**
     * 新增党组织关系转移信息
     * name 名字-[必填]
     * nowDept 现所在组织-[必填]
     * applyDeptName 申请转入组织
     * 转移原因-[必填]
     * status 状态(0:待转出1:待转入2:已转出3:已转入)-[必填]
     */
    public void appApiinsertTransferOrganizationalRelations() {
        if (TextUtils.isEmpty(applyDeptName)) {
            ToastUtils.show("请选择您要转入的组织！");
            return;
        }
        String text = inputText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtils.show("请输入转入原因！");
            return;
        }
        loding.show();
        HttpHelper.appApiinsertTransferOrganizationalRelations(
                entity.getData().getName(),
                entity.getData().getParty(),
                applyDeptName,
                text,
                new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        loding.dismiss();
                        Gson gson = new Gson();
                        appApiinsertTransferOrganizationalRelationsBean entity = gson.fromJson(succeed, appApiinsertTransferOrganizationalRelationsBean.class);
                        if(entity.getCode()==0){
                            ToastUtils.show("提交申请成功！请耐心等待审核！");
                            finish();
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
