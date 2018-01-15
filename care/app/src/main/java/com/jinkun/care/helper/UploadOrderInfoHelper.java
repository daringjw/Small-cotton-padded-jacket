package com.jinkun.care.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.jinkun.care.Constant;
import com.jinkun.care.ui.activity.order.OrderDataHelper;
import com.jinkun.care.ui.activity.order.model.DetailOrderBean;
import com.jinkun.care.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderwjq on 2017/9/6 15:46.
 */

public class UploadOrderInfoHelper {
    private static final String TAG = "UploadOrderInfoHelper";

    private static UploadOrderInfoHelper mInstance;
    private final SharedPreferences mSharedPreferences;
    private Context mContext;

    private UploadOrderInfoHelper(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(Constant.SP_FILE_NAME_PENDING_ORDER, Context.MODE_PRIVATE);
    }

    public static UploadOrderInfoHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OrderDataHelper.class) {
                if (mInstance == null) {
                    mInstance = new UploadOrderInfoHelper(context);
                }
            }
        }

        return mInstance;
    }

    public List<DetailOrderBean> getCacheOrderInfoList() {
        String cacheInfo = getPendingOrderData();

        if (cacheInfo == null) {
            return new ArrayList<>();
        } else {
            return JsonUtils.json2ObjectArrayList(cacheInfo, DetailOrderBean.class);
        }
    }

    /**
     * 获取缓存队列中的第一个工单信息,并将剩余的数据保存
     *
     * @return
     */
    public DetailOrderBean getFirstCacheOrderInfo() {
        List<DetailOrderBean> cacheList = getCacheOrderInfoList();

        if (cacheList.size() > 0) {
            DetailOrderBean bean = cacheList.get(0);
            cacheList.remove(0);
            updateCacheElderInfoList(cacheList);

            return bean;
        } else {
            return null;
        }
    }

    /**
     * 将工单数据保存至本地缓存
     * @param order
     */
    public void addOrderInfoToCache(DetailOrderBean order) {
        List<DetailOrderBean> orderList = getCacheOrderInfoList();
        orderList.add(order);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constant.SP_KEY_PENDING_ORDER, JsonUtils.list2Json(orderList));
        editor.commit();
    }

    public void updateCacheElderInfoList(List<DetailOrderBean> list) {
        // 清除原始数据
        mSharedPreferences.edit().clear().commit();
        // 保存新数据
        mSharedPreferences.edit().putString(Constant.SP_KEY_PENDING_ORDER, JsonUtils.list2Json(list)).commit();
    }

    private String getPendingOrderData() {
        return mSharedPreferences.getString(Constant.SP_KEY_PENDING_ORDER, null);
    }
}
