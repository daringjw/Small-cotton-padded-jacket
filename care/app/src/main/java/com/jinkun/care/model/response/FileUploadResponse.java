package com.jinkun.care.model.response;

/**
 * Created by coderwjq on 2017/8/24 9:21.
 */

public class FileUploadResponse {

    /**
     * code : success
     * oldfileName : 微信截图_20170823121349.png
     * url : File/padFile/
     * NewfileName : 6f31103465f60bf91546a6cde20a75dd.png
     * msg : 上传成功！
     */

    private String code;
    private String oldfileName;
    private String url;
    private String NewfileName;
    private String msg;

    @Override
    public String toString() {
        return "FileUploadResponse{" +
                "code='" + code + '\'' +
                ", oldfileName='" + oldfileName + '\'' +
                ", url='" + url + '\'' +
                ", NewfileName='" + NewfileName + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldfileName() {
        return oldfileName;
    }

    public void setOldfileName(String oldfileName) {
        this.oldfileName = oldfileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNewfileName() {
        return NewfileName;
    }

    public void setNewfileName(String NewfileName) {
        this.NewfileName = NewfileName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
