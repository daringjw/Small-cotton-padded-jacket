package com.jinkun.care.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.jinkun.care.Constant;
import com.jinkun.care.model.entity.PendingElderInfo;
import com.jinkun.care.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地老人信息相关的管理类
 * Created by coderwjq on 2017/8/24 16:09.
 */

public class UploadElderDataHelper {
    private static final String TAG = "UploadElderDataHelper";
    private static UploadElderDataHelper mInstance;
    private SharedPreferences mSharedPreferences;

    private UploadElderDataHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(Constant.SP_FILE_NAME_PENDING_FEEDS, Context.MODE_PRIVATE);
    }

    public static UploadElderDataHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UploadElderDataHelper.class) {
                if (mInstance == null) {
                    mInstance = new UploadElderDataHelper(context);
                }
            }
        }
        return mInstance;
    }

    public List<PendingElderInfo> getCacheElderInfoList() {
        String cacheInfo = getPendingFeedsData();

        if (cacheInfo == null) {
            return new ArrayList<>();
        } else {
            return JsonUtils.json2ObjectArrayList(cacheInfo, PendingElderInfo.class);
        }
    }

    /**
     * 获取缓存队列中的第一个老人数据信息,并将剩余的数据保存
     *
     * @return
     */
    public PendingElderInfo getFirstCacheElderInfoList() {
        List<PendingElderInfo> cacheElderInfoList = getCacheElderInfoList();

        if (cacheElderInfoList.size() > 0) {
            PendingElderInfo pendingElderInfo = cacheElderInfoList.get(0);
            cacheElderInfoList.remove(0);
            updateCacheElderInfoList(cacheElderInfoList);

            return pendingElderInfo;
        } else {
            return null;
        }
    }

    public void addElderInfoToCache(PendingElderInfo elderInfo) {
        List<PendingElderInfo> cacheElderInfoList = getCacheElderInfoList();
        cacheElderInfoList.add(elderInfo);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constant.SP_KEY_PENDING_FEEDS_DATA, JsonUtils.list2Json(cacheElderInfoList));
        editor.commit();
    }

    public void updateCacheElderInfoList(List<PendingElderInfo> elderInfoList) {
        // 清除原始数据
        mSharedPreferences.edit().clear().commit();
        // 保存新数据
        mSharedPreferences.edit().putString(Constant.SP_KEY_PENDING_FEEDS_DATA, JsonUtils.list2Json(elderInfoList)).commit();
    }

    private String getPendingFeedsData() {
        return mSharedPreferences.getString(Constant.SP_KEY_PENDING_FEEDS_DATA, null);
    }
}
