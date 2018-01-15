package com.jinkun.care.helper;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jinkun.care.R;

/**
 * @Created by coderwjq on 2017/8/17 15:16.
 * @Desc
 */

public class ToastHelper {
    private Context mContext;
    private Toast mToastUserDefine;
    private TextView mTvTip;

    public ToastHelper(Context context) {
        mContext = context;

        initToast();
    }

    private void initToast() {
        mToastUserDefine = new Toast(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View userDefinedToastView = inflater.inflate(R.layout.toast_user_difine_tip, null);
        mTvTip = (TextView) userDefinedToastView.findViewById(R.id.tv_toast_tip);

        mToastUserDefine.setGravity(Gravity.CENTER, 0, 0);
        mToastUserDefine.setDuration(Toast.LENGTH_SHORT);
        mToastUserDefine.setView(userDefinedToastView);
    }

    public void showToast(String tip) {
        mTvTip.setText(tip);
        mToastUserDefine.show();
    }
}
