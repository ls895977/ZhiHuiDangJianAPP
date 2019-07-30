package com.lfc.zhihuidangjianapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Debug;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.DemoApplication;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.ui.LoginActivity;
import com.hyphenate.chatuidemo.ui.MainActivity;
import com.hyphenate.exceptions.HyphenateException;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.app.MyApplication;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.bean.LoginBean;
import com.lfc.zhihuidangjianapp.net.http.HttpHelper;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_Mail_list;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.partyaffairs.act.Act_Meeting;
import com.lfc.zhihuidangjianapp.utlis.ACache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_Login extends BaseActivity {

    @BindView(R.id.editAccountNumber)
    EditText editAccountNumber;
    @BindView(R.id.editPassword)
    EditText editPassword;
    private TextToSpeech textToSpeech = null;//创建自带语音对象
    private ACache aCache;
    private static final String TAG = "LoginActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
//        initTTS();
//        if (aCache.getAsString("data") != null) {
//            String stData = aCache.getAsString("data");
//            Gson gson = new Gson();
//            LoginBean bean = gson.fromJson(stData, LoginBean.class);
//            MyApplication.setLoginBean(bean.getData());
//            startActivity(Act_Main.class);
//        }
//        loginCaptcha();
    }

    private void initTTS() {
        //实例化自带语音对象
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    // Toast.makeText(MainActivity.this,"成功输出语音",
                    // Toast.LENGTH_SHORT).show();
                    // Locale loc1=new Locale("us");
                    // Locale loc2=new Locale("china");
                    textToSpeech.setPitch(1.0f);//方法用来控制音调
                    textToSpeech.setSpeechRate(1.0f);//用来控制语速
                    //判断是否支持下面两种语言
                    int result1 = textToSpeech.setLanguage(Locale.US);
                    int result2 = textToSpeech.setLanguage(Locale.
                            SIMPLIFIED_CHINESE);
                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);

                    Log.i("zhh_tts", "US支持否？--》" + a +
                            "\nzh-CN支持否》--》" + b);

                } else {
                    Toast.makeText(Act_Login.this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void startAuto(String data) {
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
        textToSpeech.setPitch(2.0f);
        // 设置语速
        textToSpeech.setSpeechRate(0.4f);
        textToSpeech.speak(data,//输入中文，若不支持的设备则不会读出来
                TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (textToSpeech != null) {
            textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
            textToSpeech.shutdown(); // 关闭，释放资源
        }
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.btnLoginCommit)
    public void onBtnLoginCommitClicked() {
//        startAuto("您有一笔QQ订单,请注意收款情况");
        if (editAccountNumber.getText().toString().trim().equals("")) {
            ToastUtils.show("请输入您的账号");
            return;
        }
        if (editPassword.getText().toString().trim().equals("")) {
            ToastUtils.show("请输入密码");
            return;
        }
        loding.show();
        HttpHelper.login(editAccountNumber.getText().toString().trim(), editPassword.getText().toString().trim(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                LoginBean bean = gson.fromJson(succeed, LoginBean.class);
                if (bean.getCode() == 0) {
                    aCache.put("data", succeed);
                    MyApplication.setLoginBean(bean.getData());
                    login();//环信登录
                } else {
                    ToastUtils.show(bean.getMsg());
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                ToastUtils.show(error);
            }
        });
    }

    /**
     * 注册
     */
    private void regist() {
        new Thread(() -> {
            try {
                // call method in SDK

                LoginBean.DataBean loginBean = MyApplication.getLoginBean();
                String currentUsername = loginBean.getLoginName();
                String currentPassword = loginBean.getImPwd();
                EMClient.getInstance().createAccount(currentUsername, currentPassword);
                runOnUiThread(() -> {
                    DemoHelper.getInstance().setCurrentUserName(currentUsername);
                    Toast.makeText(getApplicationContext(), getResources().getString(com.hyphenate.chatuidemo.R.string.Registered_successfully), Toast.LENGTH_SHORT).show();

                    login();

                });
            } catch (final HyphenateException e) {
                runOnUiThread(() -> {
                    int errorCode = e.getErrorCode();
                    if (errorCode == EMError.NETWORK_ERROR) {
                        Toast.makeText(getApplicationContext(), getResources().getString(
                                com.hyphenate.chatuidemo.R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                    } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                        Toast.makeText(getApplicationContext(), getResources().getString(
                                com.hyphenate.chatuidemo.R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                    } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {
                        Toast.makeText(getApplicationContext(), getResources().getString(
                                com.hyphenate.chatuidemo.R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                    } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {
                        Toast.makeText(getApplicationContext(), getResources().getString(
                                com.hyphenate.chatuidemo.R.string.illegal_user_name), Toast.LENGTH_SHORT).show();
                    } else if (errorCode == EMError.EXCEED_SERVICE_LIMIT) {
                        Toast.makeText(getApplicationContext(), getResources().getString(
                                com.hyphenate.chatuidemo.R.string.register_exceed_service_limit), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(
                                com.hyphenate.chatuidemo.R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    /**
     * 登录
     */
    private void login() {
        LoginBean.DataBean loginBean = MyApplication.getLoginBean();
        String currentUsername = loginBean.getLoginName();
        String currentPassword = loginBean.getImPwd();
        String currentUser = EMClient.getInstance().getCurrentUser();
        if (TextUtils.equals(loginBean.getLoginName(), currentUser)) {
            // 登录成功
            startActivity(Act_Main.class);
            ToastUtils.show("登录成功");
            return;
        } else if (!TextUtils.isEmpty(currentUser)) {
            logout();
            return;
        }
        EMClient.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "login: onSuccess");

                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                // update current user's display name for APNs
                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
                        DemoApplication.currentUserNick.trim());
                if (!updatenick) {
                    Log.e("LoginActivity", "update current user nick fail");
                }
                // get user's info (this should be get from App's server or 3rd party service)
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                // 登录成功
                startActivity(Act_Main.class);
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.d(TAG, "login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                Log.d(TAG, "login: onError: " + code);
                Log.e("aa", "-----------环信----onError===" + message + "------------" + code);
                runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), getString(com.hyphenate.chatuidemo.R.string.Login_failed) + message,
                            Toast.LENGTH_SHORT).show();

                    regist();
                });
            }
        });
    }

    /**
     * 退出
     */
    void logout() {
        DemoHelper.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(() -> {
                    // 本地生成环信
                    login();
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "unbind devicetokens failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    String cruee;

    /**
     * 获取验证码
     */
    private void loginCaptcha() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        try {
            cruee = dateToStamp(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("aa", "---------------------" + cruee);
        HttpHelper.loginCaptcha(cruee, new HttpHelper.HttpUtilsCallBack<String>() {
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

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
