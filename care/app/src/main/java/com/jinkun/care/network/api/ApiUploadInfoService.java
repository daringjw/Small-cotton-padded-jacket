package com.jinkun.care.network.api;

import com.jinkun.care.model.response.OrderInfoResponse;
import com.jinkun.care.model.response.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by coderwjq on 2017/8/24 17:17.
 */

public interface ApiUploadInfoService {
    /**
     * 老人信息上传url
     * @param token
     * @param orderJson
     * @return
     */
    @FormUrlEncoded
    @POST("pad/saveOlder.do")
    Call<UserInfoResponse> upload(@Field("token") String token,
                                  @Field("orderJson") String orderJson);

    /**
     * 工单信息上传url
     * @param token
     * @param orderJson
     * @return
     */
    @FormUrlEncoded
    @POST("pad/saveOrder.do")
    Call<OrderInfoResponse> uploadOrder(@Field("token") String token,
                                        @Field("orderJson") String orderJson);
}
