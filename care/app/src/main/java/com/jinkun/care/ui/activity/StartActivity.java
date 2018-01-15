package com.jinkun.care.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.jinkun.care.Constant;
import com.jinkun.care.R;
import com.jinkun.care.helper.AccountHelper;

/**
 *  开始界面  love
 */
public class StartActivity extends AppCompatActivity {
    private static final String TAG = "StartActivity";
    private static final int MSG_TO_LOGIN = 0;


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case MSG_TO_LOGIN:
                    handleWithLoginState();
                    break;
            }
            return false;
        }
    });

    private void handleWithLoginState() {
        if (AccountHelper.getInstance(StartActivity.this).getCurrentUser() == null) {
            LoginActivity.invoke(StartActivity.this, true);
        } else {
            HomeActivity.invoke(StartActivity.this, true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (Constant.DEBUG_MODE) {
            handleWithLoginState();
        } else {
            mHandler.sendEmptyMessageDelayed(MSG_TO_LOGIN, 1000);
        }

    }

    private byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
    }
}
