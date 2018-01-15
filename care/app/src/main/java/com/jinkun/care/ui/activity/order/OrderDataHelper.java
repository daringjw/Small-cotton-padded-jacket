package com.jinkun.care.ui.activity.order;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.jinkun.care.Constant;
import com.jinkun.care.ui.activity.order.model.ServiceProviderBean;
import com.jinkun.care.ui.activity.order.model.ServiceTypeBean;
import com.jinkun.care.ui.activity.order.model.ServiceWaiterBean;
import com.jinkun.care.ui.activity.order.model.VillageInfoBean;
import com.jinkun.care.util.JsonUtils;

/**
 * 工单处理界面对应的数据提供类
 * Created by coderwjq on 2017/9/6 14:55.
 */

public class OrderDataHelper {
    public static final String KEY_VILLAGE = "key_village";
    public static final String KEY_TYPE = "key_type";
    public static final String KEY_PROVIDER = "key_provider";
    private static OrderDataHelper mInstance;
    private final SharedPreferences mSharedPreferences;
    private Context mContext;

    private OrderDataHelper(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(Constant.SP_FILE_NAME_ORDER_PROVIDER_INFO, Context.MODE_PRIVATE);
    }

    public static OrderDataHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OrderDataHelper.class) {
                if (mInstance == null) {
                    mInstance = new OrderDataHelper(context);
                }
            }
        }

        return mInstance;
    }

    public void saveVillageInfo(VillageInfoBean villageInfoBean) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(Constant.SP_KEY_VILLAGE_INFO, JsonUtils.bean2Json(villageInfoBean));

        editor.commit();
    }

    public void saveTypeInfo(ServiceTypeBean serviceTypeBean) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(Constant.SP_KEY_TYPE_INFO, JsonUtils.bean2Json(serviceTypeBean));

        editor.commit();
    }

    public void saveProviderInfo(ServiceProviderBean serviceProviderBean) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(Constant.SP_KEY_PROVIDER_INFO, JsonUtils.bean2Json(serviceProviderBean));

        editor.commit();
    }

    public void saveWaiterInfo(ServiceWaiterBean serviceStationBean) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(Constant.SP_KEY_WAITER_INFO, JsonUtils.bean2Json(serviceStationBean));

        editor.commit();
    }

    public VillageInfoBean getVillageInfo() {
        String info = mSharedPreferences.getString(Constant.SP_KEY_VILLAGE_INFO, "");

        if (TextUtils.isEmpty(info)) {
            return null;
        } else {
            return JsonUtils.json2Object(info, VillageInfoBean.class);
        }
    }

    public ServiceTypeBean getTypeInfo() {
        String info = mSharedPreferences.getString(Constant.SP_KEY_TYPE_INFO, "");

        if (TextUtils.isEmpty(info)) {
            return null;
        } else {
            return JsonUtils.json2Object(info, ServiceTypeBean.class);
        }
    }

    public ServiceProviderBean getProviderInfo() {
        String info = mSharedPreferences.getString(Constant.SP_KEY_PROVIDER_INFO, "");

        if (TextUtils.isEmpty(info)) {
            return null;
        } else {
            return JsonUtils.json2Object(info, ServiceProviderBean.class);
        }
    }

    public ServiceWaiterBean getWaiterInfo() {
        String info = mSharedPreferences.getString(Constant.SP_KEY_WAITER_INFO, "");

        if (TextUtils.isEmpty(info)) {
            return null;
        } else {
            return JsonUtils.json2Object(info, ServiceWaiterBean.class);
        }
    }

    public void clearOfflineData() {
        mSharedPreferences.edit().clear().commit();
    }
}
