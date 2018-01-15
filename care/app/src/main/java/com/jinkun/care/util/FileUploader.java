package com.jinkun.care.util;

import android.text.TextUtils;

import com.jinkun.care.Constant;
import com.jinkun.care.model.response.FileUploadResponse;
import com.jinkun.care.network.ApiServiceManager;
import com.jinkun.care.network.FileUploadRequestBody;
import com.jinkun.care.network.api.ApiFileUploadService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文件上传的工具类
 * Created by coderwjq on 2017/8/24 15:25.
 */

public class FileUploader {
    private static FileUploader mInstance;

    private FileUploader() {

    }

    public static FileUploader getInstance() {
        if (mInstance == null) {
            synchronized (FileUploader.class) {
                if (mInstance == null) {
                    mInstance = new FileUploader();
                }
            }
        }
        return mInstance;
    }

    public void uploadFile(String token, final String filePath, final FileUploadCallback callback) {
        if (filePath == null || TextUtils.isEmpty(filePath)) {
            callback.onFail("文件路径为空");
            return;
        }

        final File uploadFile = new File(filePath);

        if (!uploadFile.exists()) {
            callback.onFail("文件不存在");
            return;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), uploadFile);
        final FileUploadRequestBody fileUploadRequestBody = new FileUploadRequestBody(requestBody, new FileUploadRequestBody.UploadProgressListener() {
            @Override
            public void onProgressChanged(float progress, String tag) {
                callback.onProgressChanged(progress);
            }
        }, uploadFile.getName());

        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", uploadFile.getName(), fileUploadRequestBody);

        ApiServiceManager.getInstance()
                .getApiService(ApiFileUploadService.class)
                .uploadImage(token, body)
                .enqueue(new Callback<FileUploadResponse>() {
                    @Override
                    public void onResponse(Call<FileUploadResponse> call, Response<FileUploadResponse> response) {
                        FileUploadResponse fileUploadResponse = response.body();

                        if (fileUploadResponse != null) {
                            if (fileUploadResponse.getCode().equals(ApiServiceManager.RESPONSE_SUCCESS)) {
                                // 上传成功
                                callback.onSuccess(uploadFile.getPath(), Constant.PREFIX_FILE_PATH + fileUploadResponse.getNewfileName());
                            } else if (fileUploadResponse.getCode().equals(ApiServiceManager.RESPONSE_FAILED)) {
                                callback.onFail(fileUploadResponse.getMsg());
                            } else {
                                callback.onFail("上传失败,未知错误");
                            }
                        } else {
                            callback.onFail("上传失败,未知状态码");
                        }
                    }

                    @Override
                    public void onFailure(Call<FileUploadResponse> call, Throwable t) {
                        callback.onFail(t.getLocalizedMessage());
                    }
                });
    }

    public interface FileUploadCallback {
        void onSuccess(String filePath, String url);

        void onFail(String errorInfo);

        void onProgressChanged(float progress);
    }
}
