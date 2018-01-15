package com.jinkun.care.ui.activity.order;

import android.content.Context;
import android.util.Log;

import com.jinkun.care.helper.AccountHelper;
import com.jinkun.care.ui.activity.order.model.ServiceProviderBean;
import com.jinkun.care.ui.activity.order.model.ServiceTypeBean;
import com.jinkun.care.ui.activity.order.model.ServiceWaiterBean;
import com.jinkun.care.ui.activity.order.model.VillageInfoBean;
import com.jinkun.care.util.ErrorHanding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/9/6 7:22.
 */

public class OrderPresenter implements OrderContract.IOrderPresenter {
    private static final String TAG = "OrderPresenter";

    private final OrderManager mManager;
    private OrderContract.IOrderView mView;
    private Context mContext;
    private String mToken;
    private int mStationId;

    public OrderPresenter(Context context, OrderContract.IOrderView view) {
        mContext = context;
        mManager = new OrderManager();
        mView = view;

        getLocalInfo();
    }

    @Override
    public void getServiceStationInfo() {
        Log.d(TAG, "mToken: " + mToken);
        Log.d(TAG, "mStationId: " + mStationId);

        Observable.merge(mManager.getVillageInfo(mToken, String.valueOf(mStationId)),
                mManager.getServiceType(mToken, String.valueOf(mStationId)),
                mManager.getServiceProvider(mToken, String.valueOf(mStationId)),
                mManager.getServiceStation(mToken, String.valueOf(mStationId)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.loadingStartView();
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof ServiceWaiterBean) {
                            mView.addServiceWaiterData((ServiceWaiterBean) o);

                            OrderDataHelper.getInstance(mContext).saveWaiterInfo((ServiceWaiterBean) o);
                        }

                        if (o instanceof VillageInfoBean) {
                            mView.addVillageData((VillageInfoBean) o);

                            OrderDataHelper.getInstance(mContext).saveVillageInfo((VillageInfoBean) o);
                        }

                        if (o instanceof ServiceTypeBean) {
                            mView.addServiceType((ServiceTypeBean) o);

                            OrderDataHelper.getInstance(mContext).saveTypeInfo((ServiceTypeBean) o);
                        }

                        if (o instanceof ServiceProviderBean) {
                            mView.addServiceProvider((ServiceProviderBean) o);

                            OrderDataHelper.getInstance(mContext).saveProviderInfo((ServiceProviderBean) o);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + ErrorHanding.handleError(e), e);
                        mView.loadingStopView();
                        mView.loadingErrorView(ErrorHanding.handleError(e));

                        // 加载离线数据
                        mView.addServiceWaiterData(OrderDataHelper.getInstance(mContext).getWaiterInfo());
                        mView.addVillageData(OrderDataHelper.getInstance(mContext).getVillageInfo());
                        mView.addServiceType(OrderDataHelper.getInstance(mContext).getTypeInfo());
                        mView.addServiceProvider(OrderDataHelper.getInstance(mContext).getProviderInfo());
                    }

                    @Override
                    public void onComplete() {
                        mView.loadingStopView();
                    }
                });
    }

    @Override
    public void getLocalInfo() {
        mToken = AccountHelper.getInstance(mContext).getCurrentAccountToken();
        mStationId = AccountHelper.getInstance(mContext).getStationId();
    }
}
