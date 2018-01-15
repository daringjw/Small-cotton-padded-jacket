package com.jinkun.care.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jinkun.care.CareApplication;
import com.jinkun.care.R;


/**
 * Created by coderwjq on 15/12/28.
 */
public class NoticeUtils {

    private static Toast mToast;

    /**
     * 显示 toast 提示
     *
     * @param text     文本
     * @param duration 持续时间
     */
    public static void showToast(CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(CareApplication.getInstance(), text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    /**
     * 显示错误信息的提示
     *
     * @param errorInfo 错误信息
     */
    public static void showErrorInfo(String errorInfo) {
        showToast(errorInfo, Toast.LENGTH_SHORT);
    }

    /**
     * 显示错误信息的提示
     *
     * @param errResId 错误信息的字符串ID
     */
    public static void showErrorInfo(int errResId) {
        showToast(CareApplication.getInstance().getString(errResId), Toast.LENGTH_SHORT);
    }

    /**
     * 显示成功信息的提示
     *
     * @param successInfo 成功信息
     */
    public static void showSuccessInfo(String successInfo) {
        showToast(successInfo, Toast.LENGTH_SHORT);
    }

    /**
     * 显示成功信息的提示
     *
     * @param successResId 成功信息的字符串ID
     */
    public static void showSuccessInfo(int successResId) {
        showToast(CareApplication.getInstance().getString(successResId), Toast.LENGTH_SHORT);
    }

    public static void showUserDefineToast(Context context, String tip) {
        if (context != null) {
            Toast mToastUserDefine = new Toast(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View userDefinedToastView = inflater.inflate(R.layout.toast_user_difine_tip, null);
            TextView mTvTip = (TextView) userDefinedToastView.findViewById(R.id.tv_toast_tip);

            mToastUserDefine.setGravity(Gravity.CENTER, 0, 0);
            mToastUserDefine.setDuration(Toast.LENGTH_SHORT);
            mToastUserDefine.setView(userDefinedToastView);
            mTvTip.setText(tip);
            mToastUserDefine.show();
        }
    }

}
