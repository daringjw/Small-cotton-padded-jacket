package com.jinkun.care.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.jinkun.care.helper.AccountHelper;
import com.jinkun.care.helper.UploadOrderInfoHelper;
import com.jinkun.care.model.response.OrderInfoResponse;
import com.jinkun.care.network.ApiServiceManager;
import com.jinkun.care.network.api.ApiUploadInfoService;
import com.jinkun.care.ui.activity.order.model.DetailOrderBean;
import com.jinkun.care.util.JsonUtils;
import com.jinkun.care.util.NoticeUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 工单信息上传
 */
public class UploadOrderInfoService extends IntentService {
    private static final String TAG = "UploadOrderInfoService";
    private boolean offlineUploadFinish = false;
    private String mToken;

    public UploadOrderInfoService() {
        super("UploadOrderInfoService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mToken = AccountHelper.getInstance(this).getCurrentAccountToken();

        checkAndUpdatePendingOrder();
    }

    private void checkAndUpdatePendingOrder() {
        DetailOrderBean firstCacheOrderInfo = UploadOrderInfoHelper.getInstance(this).getFirstCacheOrderInfo();

        if (firstCacheOrderInfo == null) {
            // 离线工单数据上传成功

            if (offlineUploadFinish) {
                NoticeUtils.showSuccessInfo("离线工单信息上传完毕");
            }
        } else {
            offlineUploadFinish = false;

            uploadORderInfo(firstCacheOrderInfo);
        }
    }

    private void uploadORderInfo(final DetailOrderBean orderInfo) {
        ApiServiceManager.getInstance()
                .getApiService(ApiUploadInfoService.class)
                .uploadOrder(mToken, JsonUtils.bean2Json(orderInfo))
                .enqueue(new Callback<OrderInfoResponse>() {
                    @Override
                    public void onResponse(Call<OrderInfoResponse> call, Response<OrderInfoResponse> response) {
                        OrderInfoResponse orderInfoResponse = response.body();

                        if (orderInfoResponse == null) {
                            restoreOrderInfo(orderInfo);
                        } else {
                            if (orderInfoResponse.getCode().equals("success")) {
                                checkAndUpdatePendingOrder();
                            } else {
                                restoreOrderInfo(orderInfo);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderInfoResponse> call, Throwable t) {
                        restoreOrderInfo(orderInfo);
                    }
                });
    }

    private void restoreOrderInfo(DetailOrderBean orderInfo) {
        UploadOrderInfoHelper.getInstance(this).addOrderInfoToCache(orderInfo);
    }
}
