package com.jinkun.care.ui.activity.order;

import com.jinkun.care.network.ApiServiceManager;
import com.jinkun.care.ui.activity.order.model.ServiceProviderBean;
import com.jinkun.care.ui.activity.order.model.ServiceWaiterBean;
import com.jinkun.care.ui.activity.order.model.ServiceTypeBean;
import com.jinkun.care.ui.activity.order.model.VillageInfoBean;

import io.reactivex.Observable;

/**
 * Created by coderwjq on 2017/9/6 7:17.
 */

public class OrderManager {

    public Observable<ServiceWaiterBean> getServiceStation(String token, String stationId) {
        return ApiServiceManager.getInstance()
                .apiStationService()
                .getServiceStation(token, stationId);
    }

    public Observable<VillageInfoBean> getVillageInfo(String token, String stationId) {
        return ApiServiceManager.getInstance()
                .apiStationService()
                .getVillageInfo(token, stationId);
    }

    public Observable<ServiceTypeBean> getServiceType(String token, String stationId) {
        return ApiServiceManager.getInstance()
                .apiStationService()
                .getServiceType(token, stationId);
    }

    public Observable<ServiceProviderBean> getServiceProvider(String token, String stationId) {
        return ApiServiceManager.getInstance()
                .apiStationService()
                .getServiceProvider(token, stationId);
    }
}
