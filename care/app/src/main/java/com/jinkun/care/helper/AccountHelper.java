package com.jinkun.care.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.jinkun.care.Constant;
import com.jinkun.care.model.entity.UserEntity;
import com.jinkun.care.network.ApiServiceManager;

/**
 * @Created by coderwjq on 2017/8/15 15:59.
 * @Desc 登陆用户信息管理类
 */

public class AccountHelper {
    private static final String TAG = "AccountHelper";
    private static AccountHelper mInstance;
    private Context mContext;
    private UserEntity mCurrentUser;

    private AccountHelper(Context context) {
        mContext = context;
    }

    public static AccountHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AccountHelper.class) {
                if (mInstance == null) {
                    mInstance = new AccountHelper(context);
                }
            }
        }
        return mInstance;
    }

    public UserEntity getCurrentUser() {
        if (mCurrentUser == null) {
            SharedPreferences sp = mContext.getSharedPreferences(Constant.SP_FILE_NAME_LOCAL_USER_INFO, Context.MODE_PRIVATE);
            String userInfo = sp.getString(Constant.SP_KEY_LOCAL_USER_INFO, null);
            String userToken = sp.getString(Constant.SP_KEY_LOCAL_USER_TOKEN, null);

            if (userInfo != null && userToken != null) {
                Gson gson = new Gson();
                mCurrentUser = gson.fromJson(userInfo, UserEntity.class);
                ApiServiceManager.getInstance().setAccessToken(userToken);
            }
        }

        return mCurrentUser;
    }

    /**
     * 用户登录成功时调用的方法
     * @param userEntity 用户实体对象
     * @param token 校验码
     */
    public void onUserLogin(UserEntity userEntity, String token) {
        mCurrentUser = userEntity;

        // 将用户数据保存至SP文件
        SharedPreferences sp = mContext.getSharedPreferences(Constant.SP_FILE_NAME_LOCAL_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();

        Gson gson = new Gson();
        Log.d(TAG, gson.toJson(userEntity));
        editor.putString(Constant.SP_KEY_LOCAL_USER_INFO, gson.toJson(userEntity));
        editor.putString(Constant.SP_KEY_LOCAL_USER_TOKEN, token);

        // 将token信息
        ApiServiceManager.getInstance().setAccessToken(token);

        // apply同步到内存,异步写入,commit是直接写入磁盘
        editor.commit();
    }

    public void onUserLogout() {
        mCurrentUser = null;

        SharedPreferences sp = mContext.getSharedPreferences(Constant.SP_FILE_NAME_LOCAL_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();

        ApiServiceManager.getInstance().setAccessToken(null);
    }

    public String getCurrentAccountToken() {
        SharedPreferences sp = mContext.getSharedPreferences(Constant.SP_FILE_NAME_LOCAL_USER_INFO, Context.MODE_PRIVATE);
        return sp.getString(Constant.SP_KEY_LOCAL_USER_TOKEN, null);
    }

    public String getCurrentUserId() {
        UserEntity currentUser = getCurrentUser();

        if (currentUser == null) {
            throw new NullPointerException("currentUser null");
        } else {
            return currentUser.getUserId();
        }
    }

    public int getStationId() {
        UserEntity currentUser = getCurrentUser();

        if (currentUser == null) {
            throw new NullPointerException("stationId is null");
        } else {
            return currentUser.getId();
        }
    }
}
