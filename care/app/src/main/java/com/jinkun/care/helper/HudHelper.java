package com.jinkun.care.helper;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * @Created by coderwjq on 2017/8/16 9:11.
 * @Desc
 */

public class HudHelper {
    private Context mContext;
    private KProgressHUD mProgressHUD;

    public HudHelper(Context context) {
        mContext = context;
        mProgressHUD = new KProgressHUD(context);
    }

    public void showLoadingDialog(String title, String content) {
        if (mProgressHUD.isShowing()) {
            mProgressHUD.dismiss();
        }

        mProgressHUD.setLabel(title)
                .setDetailsLabel(content)
                .show();
    }

    public void dismiss() {
        if (mProgressHUD.isShowing()) {
            mProgressHUD.dismiss();
        }
    }
}
