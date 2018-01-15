package com.jinkun.care;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @Created by coderwjq on 2017/8/15 16:38.
 * @Desc 应用程序入口
 */

public class CareApplication extends Application {
    private static final String TAG = "CareApplication";
    private static CareApplication mInstance;

    public static CareApplication getInstance() {
        if (mInstance == null) {
            synchronized (CareApplication.class) {
                if (mInstance == null) {
                    mInstance = new CareApplication();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Log.d(TAG, "memory:" + am.getMemoryClass());

    }

}
