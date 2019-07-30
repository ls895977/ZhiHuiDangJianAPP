package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.view.HtmlFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 条目详情
 */
public class Act_TitleDetails extends BaseActivity {
    @BindView(R.id.TitleDetailsBack)
    ImageView TitleDetailsBack;
    @BindView(R.id.TitleDetail_title)
    TextView TitleDetail_title;
    @BindView(R.id.titleDetaile_context)
    WebView titleDetaile_context;
    @BindView(R.id.timeZuozhe)
    TextView timeZuozhe;
    private String id = "";

    @Override
    protected int getLayoutId() {
        return R.layout.act_titledetails;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    private String title, context;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.TitleDetailsBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getStringExtra("title") != null) {
            title = getIntent().getStringExtra("title");
            context = getIntent().getStringExtra("context");
            timeZuozhe.setText(getIntent().getStringExtra("zuozhe"));
            TitleDetail_title.setText(title);

            titleDetaile_context.loadDataWithBaseURL(null, HtmlFormat.getNewContent(context),
                    "text/html", "utf-8", null);
        }
    }

    @Override
    protected void initData() {

    }
//    /**
//     * 党支部详情
//     */
//    public void queryPartyDynamicDetail() {
//        HttpHelper.queryDeptDetail(id, new HttpHelper.HttpUtilsCallBack<String>() {
//            @Override
//            public void onFailure(String failure) {
//            }
//
//            @Override
//            public void onSucceed(String succeed) {
//                loding.dismiss();
//                Gson gson = new Gson();
//                QueryPartyDynamicDetailBean entity = gson.fromJson(succeed, QueryPartyDynamicDetailBean.class);
//                if (entity.getCode() == 0) {
//                    TitleDetail_title.setText(entity.getData().getPartyDynamic().getTitle());
//                    titleDetaile_context.loadDataWithBaseURL(null, entity.getData().getPartyDynamic().getComment(), "text/html", "utf-8", null);
//                }
//            }
//
//            @Override
//            public void onError(String error) {
//                loding.dismiss();
//                ToastUtils.show(error);
//            }
//        });
//    }
}
