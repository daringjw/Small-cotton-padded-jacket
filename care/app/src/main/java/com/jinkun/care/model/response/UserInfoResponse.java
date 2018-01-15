package com.jinkun.care.model.response;

import com.jinkun.care.model.entity.UserEntity;

/**
 * @Created by coderwjq on 2017/8/15 17:47.
 * @Desc
 */

public class UserInfoResponse {
    private String code;
    private String msg;
    private String token;
    private UserEntity data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getData() {
        return data;
    }

    public void setData(UserEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
