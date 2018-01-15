package com.jinkun.care.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Locale;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 通用工具类
 */
public class DeviceUtils {
    private static final String TAG = "DeviceUtils";

    public static int PERMISSION_REQUEST_READ_PHONE_STATE = 0;

    /**
     * 获取应用版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取应用版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取设备系统版本号
     */
    public static int getPhoneVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备型号
     */
    public static String getPhoneInfo() {
        return Build.MODEL;

    }

    /**
     * 设备系统版本信息
     */
    public static String getPhoneSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取设备品牌
     */
    public static String getPhoneBrand() {
        String brand = android.os.Build.BRAND;
        return TextUtils.isEmpty(brand) ? "未知" : brand;
    }

    /**
     * 设备使用语言
     */
    public static String getPhoneLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return "未知";
        }
        return language;
    }

    public static String getImei(Context context) {
        String imei;

        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return "";
        }
        return imei;
    }
}
