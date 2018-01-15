package com.jinkun.care.helper;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @Created by coderwjq on 2017/8/15 20:14.
 * @Desc
 */

public class DialogHelper {
    private Context mContext;
    private SweetAlertDialog mDialog;

    public DialogHelper(Context context) {
        mContext = context;
    }

    /**
     * 只显示标题
     *
     * @param title
     */
    public void showTitle(String title, SweetAlertDialog.OnSweetClickListener confirmListener) {
        mDialog = new SweetAlertDialog(mContext)
                .setTitleText(title)
                .setConfirmText("确认")
                .setCancelText("取消");
        mDialog.setConfirmClickListener(confirmListener);
        mDialog.show();
    }

    public void showTitleWithContent(String title, String content, SweetAlertDialog.OnSweetClickListener listener) {
        mDialog = new SweetAlertDialog(mContext)
                .setTitleText(title)
                .setContentText(content);
        mDialog.setConfirmClickListener(listener);
        mDialog.show();
    }

    public void showExceptionDialog(String title, String content) {
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(content);
        mDialog.show();
    }

    public void showWarnDialog(String title, String content, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener listener) {
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirmText);
        if (cancelText != null || !TextUtils.isEmpty(cancelText)) {
            mDialog.setCancelText(cancelText);
        }
        mDialog.setConfirmClickListener(listener);
        mDialog.show();
    }

    public void showSuccessDialog(String title, String content) {
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(content);
        mDialog.show();
    }

    public void showLoadingDialog(String title) {
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mDialog.setTitleText(title).setCancelable(false);
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

}
