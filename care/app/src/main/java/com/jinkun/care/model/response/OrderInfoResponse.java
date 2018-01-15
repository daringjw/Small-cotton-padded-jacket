package com.jinkun.care.model.response;

/**
 * Created by coderwjq on 2017/9/6 16:15.
 */

public class OrderInfoResponse {
    /**
     * code : success
     * msg : 工单添加成功！
     */

    private String code;
    private String msg;

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
}
