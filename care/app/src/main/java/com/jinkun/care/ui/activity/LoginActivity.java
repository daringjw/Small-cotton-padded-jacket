package com.jinkun.care.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinkun.care.Constant;
import com.jinkun.care.R;
import com.jinkun.care.helper.AccountHelper;
import com.jinkun.care.helper.DialogHelper;
import com.jinkun.care.model.response.UserInfoResponse;
import com.jinkun.care.network.ApiServiceManager;
import com.jinkun.care.network.api.ApiUserService;
import com.jinkun.care.util.NoticeUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录界面
 * 获取用户名与密码，发送登陆请求，核心方法handleLogin
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private ImageView mIvBtnBack;
    private TextView mTvTitle;
    private EditText mEtUserName;
    private EditText mEtPasswrod;
    private TextView mTvForgetPwd;
    private Button mBtnLogin;
    private DialogHelper mDialogHelper;

    public static void invoke(Activity srcActivity, boolean finishSelf) {
        Intent intent = new Intent();
        intent.setClass(srcActivity, LoginActivity.class);
        srcActivity.startActivity(intent);

        if (finishSelf) {
            srcActivity.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();
    }

    private void initEvent() {
        mIvBtnBack.setOnClickListener(this);
        mTvForgetPwd.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    private void initView() {
        mIvBtnBack = (ImageView) findViewById(R.id.btn_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPasswrod = (EditText) findViewById(R.id.et_password);
        mTvForgetPwd = (TextView) findViewById(R.id.tv_forget_passwrod);
        mBtnLogin = (Button) findViewById(R.id.btn_login);

        mDialogHelper = new DialogHelper(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_forget_passwrod:
                NoticeUtils.showUserDefineToast(LoginActivity.this, "修改密码功能暂未开放");
                break;
            case R.id.btn_login:
                // 登录按钮对应的点击事件
                handleLogin();
                break;
        }
    }

    /**
     * 登录按钮对应的点击事件，开始登陆请求
     */
    private void handleLogin() {
        if (!hasFilledComplete(mEtUserName.getText().toString(), mEtPasswrod.getText().toString())) {
            // 用户名或密码不能为空
            NoticeUtils.showUserDefineToast(LoginActivity.this, getString(R.string.login_username_or_password_empty));
            return;
        }
        // 显示正在登陆
        mDialogHelper.showLoadingDialog(getString(R.string.login_loading));
        mBtnLogin.setEnabled(false);

        // 开始进行登陆请求
        ApiServiceManager.getInstance().getApiService(ApiUserService.class)
                .login(mEtUserName.getText().toString(), mEtPasswrod.getText().toString(), Constant.PAD_USER_TYPE)
                .enqueue(new Callback<UserInfoResponse>() {
                    @Override
                    public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                        Log.e(TAG, "onResponse: " + call.toString());
                        mDialogHelper.dismiss();
                        mBtnLogin.setEnabled(true);
                        // 对应请求成功时调用的方法
                        handleNetworkSuccessResult(response);
                    }

                    @Override
                    public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + call.toString(), t);
                        mDialogHelper.dismiss();
                        mBtnLogin.setEnabled(true);
                        NoticeUtils.showUserDefineToast(LoginActivity.this, getString(R.string.login_network_exception));
                    }
                });
    }

    /**
     * 登录请求成功时调用的方法
     * @param response
     */
    private void handleNetworkSuccessResult(Response<UserInfoResponse> response) {
        // 根据响应内容，判断登陆用户名和密码是否正确
        // 如果相应数据为空，代表登陆失败
        // 如果相应数据的响应码code值为"success"代表登陆成功
        UserInfoResponse userInfoResponse = response.body();

        if (userInfoResponse == null || userInfoResponse.getCode() == null) {
            // 根据返回数据，判断是否登录成功
            NoticeUtils.showUserDefineToast(LoginActivity.this, "登录失败\n\n服务器数据异常");
            return;
        }

        if (userInfoResponse.getCode().equals(ApiServiceManager.RESPONSE_SUCCESS)) {
            // 登陆成功
            AccountHelper.getInstance(LoginActivity.this).onUserLogin(userInfoResponse.getData(), userInfoResponse.getToken());
            // 跳转至主界面
            HomeActivity.invoke(LoginActivity.this, true);
            return;
        } else {
            NoticeUtils.showUserDefineToast(LoginActivity.this, userInfoResponse.getMsg());
        }
    }

    private boolean hasFilledComplete(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (TextUtils.isEmpty(args[i])) {
                return false;
            }
        }
        return true;
    }
}
