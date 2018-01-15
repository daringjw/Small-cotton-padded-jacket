package com.jinkun.care.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jinkun.care.Constant;
import com.jinkun.care.R;
import com.jinkun.care.helper.AccountHelper;
import com.jinkun.care.helper.DialogHelper;
import com.jinkun.care.receiver.NetworkStateChangedReceiver;
import com.jinkun.care.ui.activity.order.OrderActivity;
import com.jinkun.care.util.NoticeUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

/**
 * 登陆成功后进入的界面，相当于主界面
 * 1.打开老人建档模块，handleCreateDocument();
 * 2.打开工单处理模块,handleOrderTask
 * 3.退出登录，handleLogoutTask
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @BindView(R.id.btn_create_doc)
    Button mBtnCreateDoc;
    @BindView(R.id.btn_order_handle)
    Button mBtnOrderHandle;
    @BindView(R.id.btn_logout)
    Button mBtnLogout;
    @BindView(R.id.tv_current_user)
    TextView mTvCurrentUser;
    private DialogHelper mDialogHelper;
    private RxPermissions mRxPermissions;
    private NetworkStateChangedReceiver mNetworkStateChangedReceiver;

    public static void invoke(Activity srcActivity, boolean finishSelf) {
        Intent intent = new Intent();
        intent.setClass(srcActivity, HomeActivity.class);
        srcActivity.startActivity(intent);

        if (finishSelf) {
            srcActivity.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mRxPermissions = new RxPermissions(this);

        initView();

        mTvCurrentUser.setText("你好，" + AccountHelper.getInstance(this).getCurrentUser().getLinkName());

        registNetworkChangeReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegistNetworkChangeReceiver();
    }

    /**
     * 注册监听网络状态变化，在有网条件下，启动IntentService进行离线数据上传
     * 交由NetworkStateChangedReceiver处理
     */
    private void registNetworkChangeReceiver() {
        mNetworkStateChangedReceiver = new NetworkStateChangedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkStateChangedReceiver, filter);
    }

    /**
     * 关闭activity的时候取消注册网络状态变化的监听
     */
    private void unRegistNetworkChangeReceiver() {
        unregisterReceiver(mNetworkStateChangedReceiver);
    }

    private void initView() {
        mDialogHelper = new DialogHelper(this);

        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            initFileDir();
                        } else {
                            NoticeUtils.showUserDefineToast(HomeActivity.this, "权限获取失败,可能无法正常使用");
                        }
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFileDir() {
        File audioDirPath = new File(Constant.DEFAULT_SAVE_AUDIO_PATH);
        if (!audioDirPath.exists()) {
            audioDirPath.mkdirs();
        }
    }

    @OnClick({R.id.btn_create_doc, R.id.btn_order_handle, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_doc:
                // 权限判断
                mRxPermissions.request(Manifest.permission.RECORD_AUDIO)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    handleCreateDocument();
                                } else {
                                    NoticeUtils.showUserDefineToast(HomeActivity.this, "需要获取录音权限");
                                }
                            }
                        });
                break;
            case R.id.btn_order_handle:
                handleOrderTask();
                break;
            case R.id.btn_logout:
                handleLogoutTask();
                break;
        }
    }

    private void handleOrderTask() {
        OrderActivity.invoke(this);
    }

    private void handleCreateDocument() {
        if (Constant.DEBUG_MODE) {
            // 调试模式下，直接启动老人建档界面
            DocumentActivity.invoke(HomeActivity.this, false);
            return;
        }
        // 非调试模式下，需要提示用户录音
        mDialogHelper.showWarnDialog("提示", "建档过程中会进行录音，请知悉", "确定", null, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialogHelper.dismiss();
                DocumentActivity.invoke(HomeActivity.this, false);
            }
        });
    }

    /**
     * 对应于注销登陆按钮的点击事件
     */
    private void handleLogoutTask() {
        AccountHelper.getInstance(HomeActivity.this).onUserLogout();
        LoginActivity.invoke(HomeActivity.this, true);
    }
}
