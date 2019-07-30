package com.lfc.zhihuidangjianapp.ui.activity.fgt;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseFragment;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_PartyBuilding;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_PartyBuildingMatrix;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.Act_Party_membershipDues;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_LearningExperience;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_Mail_list;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_Meeting;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_OrganizingLife;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_PartyMembership;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_StyleOfForestArea;

/**
 * 党务工作
 */
public class Fgt_PartyAffairs extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_party_affairs;
    }

    @Override
    protected void initView(View rootView) {
        rootView.findViewById(R.id.Meeting).setOnClickListener(this);
        rootView.findViewById(R.id.Mail_list).setOnClickListener(this);
        rootView.findViewById(R.id.ll_juzhen).setOnClickListener(this);
        rootView.findViewById(R.id.ll_jiaofei).setOnClickListener(this);
        rootView.findViewById(R.id.ll_dangyuanguanxi).setOnClickListener(this);
        rootView.findViewById(R.id.ll_zuzhishenhuo).setOnClickListener(this);
        rootView.findViewById(R.id.ll_zhoubao).setOnClickListener(this);
        rootView.findViewById(R.id.ll_jicendongtai).setOnClickListener(this);
        rootView.findViewById(R.id.ll_qungtuan).setOnClickListener(this);
        rootView.findViewById(R.id.ll_lianzhenjianshe).setOnClickListener(this);
        rootView.findViewById(R.id.ll_dangweishifan).setOnClickListener(this);
        rootView.findViewById(R.id.ll_zhibuyingling).setOnClickListener(this);
        rootView.findViewById(R.id.ll_dangyuanshijian).setOnClickListener(this);
        rootView.findViewById(R.id.ll_lingcaogognkaike).setOnClickListener(this);
        rootView.findViewById(R.id.ll_gongjianpeiyang).setOnClickListener(this);
        rootView.findViewById(R.id.ll_xuexixingde).setOnClickListener(this);
        rootView.findViewById(R.id.ll_xianjingjiceng).setOnClickListener(this);
        rootView.findViewById(R.id.ll_dangwugognzuo).setOnClickListener(this);
        rootView.findViewById(R.id.ll_gongchandangyuan).setOnClickListener(this);
        rootView.findViewById(R.id.ll_dangjianlianxiyuan).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_juzhen://党建矩阵
                startActivity(new Intent(getContext(), Act_PartyBuildingMatrix.class));
                break;
            case R.id.ll_jiaofei://党费缴纳
                startActivity(new Intent(getContext(), Act_Party_membershipDues.class));
                break;
            case R.id.ll_dangyuanguanxi://党员关系转移
                startActivity(new Intent(getContext(), Act_PartyMembership.class));
                break;
            case R.id.ll_zuzhishenhuo://组织生活
                startActivity(new Intent(getContext(), Act_OrganizingLife.class));
                break;
            case R.id.ll_zhoubao://工作周报


                break;
            case R.id.ll_jicendongtai://基层动态
                startActivity(new Intent(getContext(), Act_PartyBuilding.class));
                break;
            case R.id.ll_qungtuan://群团统战
                startActivity(new Intent(getContext(), Act_PartyBuilding.class));
                break;
            case R.id.ll_lianzhenjianshe://廉政建设
                startActivity(new Intent(getContext(), Act_PartyBuilding.class));
                break;
            case R.id.ll_dangweishifan://党委示范


                break;
            case R.id.ll_zhibuyingling://支部引领

                break;
            case R.id.ll_dangyuanshijian://党员实践
                break;
            case R.id.ll_lingcaogognkaike://林草公开课
                break;
            case R.id.ll_gongjianpeiyang://工匠培养

                break;
            case R.id.ll_xuexixingde://学习心得
                startActivity(new Intent(getContext(), Act_LearningExperience.class));
                break;
            case R.id.ll_xianjingjiceng://先进基层党
                startActivity(new Intent(getContext(), Act_StyleOfForestArea.class));
                break;
            case R.id.ll_dangwugognzuo://优秀党务工作者
                startActivity(new Intent(getContext(), Act_StyleOfForestArea.class));
                break;
            case R.id.ll_gongchandangyuan://工产党员
                startActivity(new Intent(getContext(), Act_StyleOfForestArea.class));
                break;
            case R.id.ll_dangjianlianxiyuan://党建联系员
                startActivity(new Intent(getContext(), Act_StyleOfForestArea.class));
                break;
            case R.id.Meeting://会议
                startActivity(new Intent(getContext(), Act_Meeting.class));
                break;
            case R.id.Mail_list://通讯录
                startActivity(new Intent(getContext(), Act_Mail_list.class));
                break;
        }
    }
}
