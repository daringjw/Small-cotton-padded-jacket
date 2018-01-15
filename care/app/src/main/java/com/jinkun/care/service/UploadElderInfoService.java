package com.jinkun.care.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.jinkun.care.Constant;
import com.jinkun.care.helper.AccountHelper;
import com.jinkun.care.helper.UploadElderDataHelper;
import com.jinkun.care.model.entity.PendingElderInfo;
import com.jinkun.care.model.response.UserInfoResponse;
import com.jinkun.care.network.ApiServiceManager;
import com.jinkun.care.network.api.ApiUploadInfoService;
import com.jinkun.care.util.FileUploader;
import com.jinkun.care.util.JsonUtils;
import com.jinkun.care.util.NoticeUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 老人信息离线上传服务
 * onHandleIntent：打开IntentService后会执行的方法
 * 当这个方法执行完毕以后，这个IntentService就会自动停止
 */
public class UploadElderInfoService extends IntentService {
    private static final String TAG = "UploadElderInfoService";
    private PendingElderInfo mCurrentPendingElderInfo;
    private String mToken;
    private boolean offlineUploadFinish = false;

    public UploadElderInfoService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "onHandleIntent() called with: intent = [" + intent + "]");
        mToken = AccountHelper.getInstance(this).getCurrentAccountToken();
        checkAndUploadPendingFeed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy() called");
    }

    /**
     * 从本地获取需要上传的老人信息
     */
    private void checkAndUploadPendingFeed() {
        Log.e(TAG, "未上传老人数据个数: " + UploadElderDataHelper.getInstance(this).getCacheElderInfoList().size());
        PendingElderInfo firstCacheElderInfoList = UploadElderDataHelper.getInstance(this).getFirstCacheElderInfoList();
        if (firstCacheElderInfoList == null) {
            Log.e(TAG, "checkAndUploadPendingFeed: 全部老人信息上传完毕");

            if (offlineUploadFinish) {
                NoticeUtils.showSuccessInfo("离线老人信息上传完毕");
            }

            return;
        } else {
            offlineUploadFinish = true;
            mCurrentPendingElderInfo = firstCacheElderInfoList;
            // 依次上传老人照片、建档录音文件、老人信息
            uploadImageFile();
        }
    }

    /**
     * 第一步，上传老人
     */
    private void uploadImageFile() {
        String headImg = mCurrentPendingElderInfo.getOldPeopleInfo().getHeadImg();
        if (TextUtils.isEmpty(headImg) || headImg == null) {
            Log.e(TAG, "uploadImageFile: 上传老人图片");
            Log.e(TAG, "mToken: " + mToken);
            Log.e(TAG, "path: " + mCurrentPendingElderInfo.getImagePath());
            FileUploader.getInstance().uploadFile(mToken, mCurrentPendingElderInfo.getImagePath(), new FileUploader.FileUploadCallback() {
                @Override
                public void onSuccess(String filePath, String url) {
                    Log.e(TAG, "filePath: " + filePath + " " + url);
                    mCurrentPendingElderInfo.getOldPeopleInfo().setHeadImg(url);

                    uploadAudioFile();
                }

                @Override
                public void onFail(String errorInfo) {
                    Log.e(TAG, "uploadImageFile onFail: " + errorInfo);

                    restoreElderInfo();
                }

                @Override
                public void onProgressChanged(float progress) {

                }
            });
        } else {
            uploadAudioFile();
        }
    }

    /**
     * 第二步，上传录音文件
     */
    private void uploadAudioFile() {
        String recording = mCurrentPendingElderInfo.getOldPeopleInfo().getRecording();
        if (TextUtils.isEmpty(recording) || recording == null) {
            Log.e(TAG, "uploadAudioFile: 上传建档录音");
            FileUploader.getInstance().uploadFile(mToken, mCurrentPendingElderInfo.getAudioPath(), new FileUploader.FileUploadCallback() {
                @Override
                public void onSuccess(String filePath, String url) {
                    Log.e(TAG, "filePath: " + filePath + " " + url);

                    mCurrentPendingElderInfo.getOldPeopleInfo().setRecording(url);
                    uploadElderInfo();
                }

                @Override
                public void onFail(String errorInfo) {
                    Log.e(TAG, "uploadAudioFile onFail: " + errorInfo);

                    if (Constant.DEBUG_MODE) {
                        uploadElderInfo();
                    } else {
                        restoreElderInfo();
                    }
                }

                @Override
                public void onProgressChanged(float progress) {

                }
            });
        } else {
            uploadElderInfo();
        }
    }

    private void uploadElderInfo() {
        Log.e(TAG, "uploadElderInfo: 上传老人信息 " + JsonUtils.bean2Json(mCurrentPendingElderInfo.getOldPeopleInfo()));
        ApiServiceManager.getInstance().getApiService(ApiUploadInfoService.class)
                .upload(mToken, JsonUtils.bean2Json(mCurrentPendingElderInfo.getOldPeopleInfo()))
                .enqueue(new Callback<UserInfoResponse>() {
                    @Override
                    public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                        Log.e(TAG, "uploadElderInfo: 上传老人信息成功" + JsonUtils.bean2Json(mCurrentPendingElderInfo.getOldPeopleInfo()));
                        checkAndUploadPendingFeed();
                    }

                    @Override
                    public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t);
                        // 如果上传失败，将老人信息恢复到缓存中，等待下一次上传
                        restoreElderInfo();
                    }
                });
    }

    private void restoreElderInfo() {
        UploadElderDataHelper.getInstance(this).addElderInfoToCache(mCurrentPendingElderInfo);
    }
}
