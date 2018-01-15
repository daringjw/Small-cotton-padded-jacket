package com.jinkun.care.network.api;

import com.jinkun.care.ui.activity.order.model.ServiceProviderBean;
import com.jinkun.care.ui.activity.order.model.ServiceTypeBean;
import com.jinkun.care.ui.activity.order.model.ServiceWaiterBean;
import com.jinkun.care.ui.activity.order.model.VillageInfoBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by coderwjq on 2017/9/6 8:55.
 */

public interface ApiStationService {
    @FormUrlEncoded
    @POST("pad/getAllPensionPeople.do")
    Observable<ServiceWaiterBean> getServiceStation(@Field("token") String token,
                                                    @Field("stationId") String stationId);

    @FormUrlEncoded
    @POST("pad/getAllCommunity.do")
    Observable<VillageInfoBean> getVillageInfo(@Field("token") String token,
                                               @Field("stationId") String stationId);

    @FormUrlEncoded
    @POST("pad/getAllFWProviderType.do")
    Observable<ServiceTypeBean> getServiceType(@Field("token") String token,
                                               @Field("stationId") String stationId);

    @FormUrlEncoded
    @POST("pad/getAllFWProvider.do")
    Observable<ServiceProviderBean> getServiceProvider(@Field("token") String token,
                                                       @Field("stationId") String stationId);

    @FormUrlEncoded
    @POST("pad/getAllServiceItems.do")
    Observable<ServiceProviderBean> getServiceProvider(@Field("providerId") String stationId);
}
