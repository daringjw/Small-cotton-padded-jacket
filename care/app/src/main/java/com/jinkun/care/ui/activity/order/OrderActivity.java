package com.jinkun.care.ui.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jinkun.care.R;
import com.jinkun.care.helper.DialogHelper;
import com.jinkun.care.helper.UploadOrderInfoHelper;
import com.jinkun.care.service.UploadOrderInfoService;
import com.jinkun.care.ui.activity.order.adapter.OrderSpinnerAdapter;
import com.jinkun.care.ui.activity.order.model.DetailOrderBean;
import com.jinkun.care.ui.activity.order.model.OrderSpinnerBean;
import com.jinkun.care.ui.activity.order.model.ServiceProviderBean;
import com.jinkun.care.ui.activity.order.model.ServiceTypeBean;
import com.jinkun.care.ui.activity.order.model.ServiceWaiterBean;
import com.jinkun.care.ui.activity.order.model.VillageInfoBean;
import com.jinkun.care.util.NetworkUtils;
import com.jinkun.care.util.NoticeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 工单处理主界面
 */
public class OrderActivity extends AppCompatActivity implements OrderContract.IOrderView {
    private static final String TAG = "OrderActivity";

    @BindView(R.id.tv_cancle)
    TextView mTvCancle;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.tv_order_id)
    TextView mTvOrderId;
    @BindView(R.id.sp_service_waiter)
    Spinner mSpServiceWaiter;
    @BindView(R.id.et_elder_name)
    EditText mEtElderName;
    @BindView(R.id.et_profession)
    EditText mEtProfession;
    @BindView(R.id.et_elder_number)
    EditText mEtElderNumber;
    @BindView(R.id.sp_village_info)
    Spinner mSpVillageInfo;
    @BindView(R.id.et_detail_address)
    EditText mEtDetailAddress;
    @BindView(R.id.sp_service_type)
    Spinner mSpServiceType;
    @BindView(R.id.sp_service_method)
    Spinner mSpServiceMethod;
    @BindView(R.id.sp_service_provider)
    Spinner mSpServiceProvider;
    @BindView(R.id.et_remarks)
    EditText mEtRemarks;
    private OrderPresenter mPresenter;
    private DialogHelper mDialogHelper;
    private DetailOrderBean mDetailOrderBean;
    private String mOrderId;
    private OrderSpinnerAdapter mWaiterAdapter;

    public static void invoke(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        mPresenter = new OrderPresenter(this, this);
        mDialogHelper = new DialogHelper(this);
        mDetailOrderBean = new DetailOrderBean();

        initView();
    }

    @Override
    protected void onDestroy() {
        overridePendingTransition(0, 0);

        super.onDestroy();
    }

    private void initView() {
        mOrderId = "FW-" + System.currentTimeMillis();
        mTvOrderId.setText(mOrderId);

        mPresenter.getServiceStationInfo();

        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogHelper.showWarnDialog("提示", "确定退出?", "确定", "取消", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mDialogHelper.dismiss();
                        finish();
                    }
                });
            }
        });

        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!orderFilledCompleted()) {
                    NoticeUtils.showUserDefineToast(OrderActivity.this, "数据填写不完整");
                    return;
                }

                mDialogHelper.showTitle("确定保存并退出?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mDialogHelper.dismiss();

                        UploadOrderInfoHelper.getInstance(OrderActivity.this).addOrderInfoToCache(mDetailOrderBean);
                        if (NetworkUtils.isNetworkAvailable(OrderActivity.this)) {
                            startService(new Intent(OrderActivity.this, UploadOrderInfoService.class));
                        } else {
                            NoticeUtils.showUserDefineToast(OrderActivity.this, "已保存至离线数据");
                        }
                        finish();
                    }
                });
            }
        });
    }

    private boolean orderFilledCompleted() {
        // 设置工单编号
        mDetailOrderBean.setOrderId(mOrderId);

        // 设置服务人员数据
        OrderSpinnerBean waiterBean = mWaiterAdapter.getItem(mSpServiceWaiter.getSelectedItemPosition());
        Log.d(TAG, "waiterBean: " + waiterBean.toString());
        mDetailOrderBean.setStationId(waiterBean.getId());
        mDetailOrderBean.setStaffName(waiterBean.getValue());

        return false;
    }

    @Override
    public void addServiceWaiterData(ServiceWaiterBean bean) {
        Log.d(TAG, "addServiceStationData() called with: bean = [" + bean + "]");
        if (bean.getCode().equals("success")) {
            List<ServiceWaiterBean.DataBean> data = bean.getData();
            ArrayList<OrderSpinnerBean> spinnerData = new ArrayList<>();

            if (data == null || data.size() == 0) {
                spinnerData.add(new OrderSpinnerBean(-1, "服务人员数据为空"));
            } else {
                spinnerData.add(new OrderSpinnerBean(-1, "请选择"));
                for (ServiceWaiterBean.DataBean dataBean : data) {
                    spinnerData.add(new OrderSpinnerBean(dataBean.getId(), dataBean.getName()));
                }
            }

            mWaiterAdapter = new OrderSpinnerAdapter(this, spinnerData);
            mSpServiceWaiter.setAdapter(mWaiterAdapter);
            mSpServiceWaiter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else {
            NoticeUtils.showErrorInfo(bean.getMsg());
        }
    }

    @Override
    public void addVillageData(VillageInfoBean data) {
        if (data.getCode().equals("success")) {
            List<VillageInfoBean.DataBean> villageData = data.getData();
            ArrayList<String> villages = new ArrayList<>();

            if (villageData == null || villageData.size() == 0) {
                villages.add("社区数据为空");
            } else {
                villages.add("请选择");
                for (VillageInfoBean.DataBean dataBean : villageData) {
                    villages.add(dataBean.getName());
                }
            }

            mSpVillageInfo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, villages));
        } else {
            NoticeUtils.showErrorInfo(data.getMsg());
        }
    }

    @Override
    public void addServiceType(ServiceTypeBean data) {
        if (data.getCode().equals("success")) {
            List<ServiceTypeBean.DataBean> providerData = data.getData();
            ArrayList<String> providers = new ArrayList<>();

            if (providerData == null || providerData.size() == 0) {
                providers.add("服务类型数据为空");
            } else {
                providers.add("请选择");
                for (ServiceTypeBean.DataBean dataBean : providerData) {
                    providers.add(dataBean.getTypeName());
                }
            }

            mSpServiceType.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, providers));
        } else {
            NoticeUtils.showErrorInfo(data.getMsg());
        }
    }

    @Override
    public void addServiceProvider(ServiceProviderBean bean) {
        if (bean.getCode().equals("success")) {
            List<ServiceProviderBean.DataBean> data = bean.getData();
            ArrayList<String> providers = new ArrayList<>();

            if (data == null || data.size() == 0) {
                providers.add("服务商数据为空");
            } else {
                providers.add("请选择");
                for (ServiceProviderBean.DataBean dataBean : data) {
                    providers.add(dataBean.getProviderName());
                }
            }

            mSpServiceProvider.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, providers));
        } else {
            NoticeUtils.showErrorInfo(bean.getMsg());
        }
    }

    @Override
    public void loadingStartView() {
        mDialogHelper.showLoadingDialog(getString(R.string.request_order_info));
    }

    @Override
    public void loadingStopView() {
        mDialogHelper.dismiss();
    }

    @Override
    public void loadingErrorView(String errorInfo) {
        NoticeUtils.showErrorInfo(errorInfo + "，使用离线数据");
    }

}
