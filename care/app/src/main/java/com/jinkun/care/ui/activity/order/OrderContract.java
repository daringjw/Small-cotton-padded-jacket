package com.jinkun.care.ui.activity.order;

import com.jinkun.care.ui.activity.order.model.ServiceProviderBean;
import com.jinkun.care.ui.activity.order.model.ServiceWaiterBean;
import com.jinkun.care.ui.activity.order.model.ServiceTypeBean;
import com.jinkun.care.ui.activity.order.model.VillageInfoBean;

/**
 * Created by coderwjq on 2017/9/6 7:22.
 */

public class OrderContract {
    public interface IOrderView {
        void addServiceWaiterData(ServiceWaiterBean bean);

        void addVillageData(VillageInfoBean bean);

        void addServiceType(ServiceTypeBean bean);

        void addServiceProvider(ServiceProviderBean bean);

        void loadingStartView();

        void loadingStopView();

        void loadingErrorView(String errorInfo);
    }

    public interface IOrderPresenter {
        void getServiceStationInfo();
        void getLocalInfo();
    }
}
