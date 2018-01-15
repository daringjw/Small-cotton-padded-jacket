package com.jinkun.care.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class DisplayUtils {

    protected static final int HEIGHT_STATUS_BAR = 19;
    public static int SMALL_SCREEN_WIDTH = 720;
    public static int MEDIUM_SCREEN_WIDTH = 1080;
    public static int LARGE__SCREEN_WIDTH = 1600;
    private static Resources mResources = Resources.getSystem();
    private static DisplayMetrics metrics = mResources.getDisplayMetrics();
    private static float mDensity = metrics.density;
    private static float mScaledDensity = metrics.scaledDensity;
    private static int mScreenWidth = metrics.widthPixels;
    private static int mScreenHeight = metrics.heightPixels - getStatusBarHeight();
    private static int mScreenHeightWithoutStatusBar = metrics.heightPixels - getStatusBarHeight();
    private static Point point = null;

    public static int getScreenWidthAsDp() {
        return px2dp(mScreenWidth);
    }

    public static int getScreenWidthAsPx() {
        return mScreenWidth;
    }

    public static int getScreenHeightAsDp() {
        return px2dp(mScreenHeight);
    }

    public static int getScreenHeightAsPx() {
        return mScreenHeight;
    }

    public static int getScreenHeightAsDpWithoutStatusBar() {
        return px2dp(mScreenHeightWithoutStatusBar);
    }

    public static int getScreenHeightAsPxWithoutStatusBar() {
        return mScreenHeightWithoutStatusBar;
    }

    public static float getDensity() {
        return mDensity;
    }

    public static int dp2px(float dp) {
        return Math.round(dp * mDensity);
    }

    public static int px2dp(int px) {
        return Math.round(px / mDensity);
    }

    public static int sp2px(float sp) {
        return Math.round(sp * mScaledDensity);
    }

    public static int px2sp(int px) {
        return Math.round(px / mScaledDensity);
    }

    /**
     * @return the status bar height(px) for current device.
     */
    public static int getStatusBarHeight() {
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = dp2px(HEIGHT_STATUS_BAR);
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = mResources.getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 文本复制功能
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager
                cmb =
                (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    public static boolean checkClipborad(Context context) {
        ClipboardManager
                cmb =
                (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.hasText();
    }

    /**
     * 实现粘贴功能
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager
                cmb =
                (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    public static Point getDisplayWidthPixels(Context context) {
        if (point != null) {
            return point;
        }
        WindowManager wm = ((Activity) context).getWindowManager();
        point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 计算两点之间的距离
     */
    public static double lineSpace(double x1, double y1, double x2, double y2) {
        double lineLength = 0;
        double x, y;
        x = x1 - x2;
        y = y1 - y2;
        lineLength = Math.sqrt(x * x + y * y);
        return lineLength;
    }

    public static double calculateTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = lineSpace(x1, y1, x2, y2);
        double b = lineSpace(x2, y2, x3, y3);
        double c = lineSpace(x3, y3, x1, y1);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
