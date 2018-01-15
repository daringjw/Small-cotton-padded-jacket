package com.jinkun.care.network.api;

import com.jinkun.care.model.response.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Created by coderwjq on 2017/8/15 17:44.
 * @Desc
 */

public interface ApiUserService {
    /**
     * 登陆请求的接口
     * @param account 用户名
     * @param pwd 密码
     * @param userType 用户类型，pad默认为51
     * @return
     */
    @FormUrlEncoded
    @POST("mobileUser/login.do")
    Call<UserInfoResponse> login(@Field("account") String account,
                                 @Field("pwd") String pwd,
                                 @Field("userType") String userType);
}
