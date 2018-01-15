package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinkun.care.Constant;
import com.jinkun.care.R;
import com.jinkun.care.adapter.BloodTransfusionAdapter;
import com.jinkun.care.adapter.DamageAdapter;
import com.jinkun.care.adapter.DetailDiseaseAdapter;
import com.jinkun.care.adapter.SurgeryAdapter;
import com.jinkun.care.helper.DataHelper;
import com.jinkun.care.model.entity.BloodTransfusionEntity;
import com.jinkun.care.model.entity.DamageEntity;
import com.jinkun.care.model.entity.DetailDiseaseEntity;
import com.jinkun.care.model.entity.SurgeryEntity;
import com.jinkun.care.ui.view.AddDiseaseInputPopup;
import com.jinkun.care.ui.view.AddRecordPopup;
import com.jinkun.care.ui.view.SingleInputPopup;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @Created by coderwjq on 2017/8/16 16:50.
 * @Desc
 */

public class Step2Fragment extends BaseDocFragment {
    private static final String TAG = "Step2Fragment";

    private static Step2Fragment mInstance;
    private RecyclerView mRvDetailDisease;
    private GridLayoutManager mGridLayoutManager;
    private DetailDiseaseAdapter mDetailDiseaseAdapter;
    private List<DetailDiseaseEntity> mDetailDiseaseEntities;
    private RecyclerView mRvSurgery;
    private GridLayoutManager mSurgeryGridLayoutManager;
    private SurgeryAdapter mSurgeryAdapter;
    private List<SurgeryEntity> mSurgeryEntities;
    private RecyclerView mRvDamageInfo;
    private GridLayoutManager mDamageGridLayoutManager;
    private DamageAdapter mDamageAdapter;
    private List<DamageEntity> mDamageEntities;
    private RecyclerView mRvBloodTransfusion;
    private GridLayoutManager mBloodTransGridLayoutManager;
    private BloodTransfusionAdapter mBloodtransAdapter;
    private List<BloodTransfusionEntity> mBloodTransRecordEntities;

    public static Step2Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step2Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step2Fragment();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        String[] diseaseArrarData = getResources().getStringArray(R.array.disease_list);
        mDetailDiseaseEntities = DataHelper.diseaseArrayTransToList(diseaseArrarData);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_2, container, false);

        initDiseaseView(view);
        initSurgeryView(view);
        initDamageInfoView(view);
        initBloodTransfusionView(view);

        return view;
    }

    private void initBloodTransfusionView(View view) {
        mRvBloodTransfusion = view.findViewById(R.id.rv_blood_transfusion);
        mBloodTransGridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mRvBloodTransfusion.setLayoutManager(mBloodTransGridLayoutManager);
        mBloodtransAdapter = new BloodTransfusionAdapter(getActivity());
        mRvBloodTransfusion.setAdapter(mBloodtransAdapter);
        mBloodTransRecordEntities = new ArrayList<>();
        mBloodtransAdapter.setRecordEntities(mBloodTransRecordEntities);
        mBloodtransAdapter.setOnItemClickListener(new BloodTransfusionAdapter.OnItemClickListener() {
            @Override
            public void onDeleteButtonClick(final int position) {
                mDialogHelper.showTitle("确认删除?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mBloodTransRecordEntities.remove(position);
                        mBloodtransAdapter.notifyDataSetChanged();
                        sweetAlertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onAddButtonClick() {
                final AddRecordPopup addRecordPopup = new AddRecordPopup(getActivity(), getString(R.string.title_add_trans_record), getString(R.string.hint_input_trans_reason), getString(R.string.hint_input_trans_time));
                addRecordPopup.setOnDiagnoseInputClickListener(new AddRecordPopup.OnDiagnoseInputClickListener() {
                    @Override
                    public void onCompleteButtonClick(String name, String time) {
                        BloodTransfusionEntity bloodTransfusionEntity = new BloodTransfusionEntity(name, time);
                        mBloodTransRecordEntities.add(bloodTransfusionEntity);
                        mBloodtransAdapter.notifyDataSetChanged();
                        addRecordPopup.dismiss();
                    }

                    @Override
                    public void onCancelButtonClick() {
                        addRecordPopup.dismiss();
                    }
                });
                addRecordPopup.showPopupWindow();
            }
        });
    }

    private void initDamageInfoView(View view) {
        mRvDamageInfo = view.findViewById(R.id.rv_damage);
        mDamageGridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mRvDamageInfo.setLayoutManager(mDamageGridLayoutManager);
        mDamageAdapter = new DamageAdapter(getActivity());
        mRvDamageInfo.setAdapter(mDamageAdapter);
        mDamageEntities = new ArrayList<>();
        mDamageAdapter.setDamageEntities(mDamageEntities);
        mDamageAdapter.setOnItemClickListener(new DamageAdapter.OnItemClickListener() {
            @Override
            public void onDeleteButtonClick(final int position) {
                mDialogHelper.showTitle("确认删除?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mDamageEntities.remove(position);
                        mDamageAdapter.notifyDataSetChanged();
                        sweetAlertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onAddButtonClick() {
                final AddRecordPopup addRecordPopup = new AddRecordPopup(getActivity(), getString(R.string.title_add_damage_info), getString(R.string.hint_input_damage_name), getString(R.string.hint_input_damage_time));
                addRecordPopup.setOnDiagnoseInputClickListener(new AddRecordPopup.OnDiagnoseInputClickListener() {
                    @Override
                    public void onCompleteButtonClick(String name, String time) {
                        DamageEntity damageEntity = new DamageEntity(name, time);
                        mDamageEntities.add(damageEntity);
                        mDamageAdapter.notifyDataSetChanged();
                        addRecordPopup.dismiss();
                    }

                    @Override
                    public void onCancelButtonClick() {
                        addRecordPopup.dismiss();
                    }
                });
                addRecordPopup.showPopupWindow();
            }
        });
    }

    private void initSurgeryView(View view) {
        mRvSurgery = view.findViewById(R.id.rv_surgery);
        mSurgeryGridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mRvSurgery.setLayoutManager(mSurgeryGridLayoutManager);
        mSurgeryAdapter = new SurgeryAdapter(getActivity());
        mRvSurgery.setAdapter(mSurgeryAdapter);
        mSurgeryEntities = new ArrayList<>();
        mSurgeryAdapter.setSurgeryEntities(mSurgeryEntities);
        mSurgeryAdapter.setOnItemClickListener(new SurgeryAdapter.OnItemClickListener() {
            @Override
            public void onDeleteButtonClick(final int position) {
                mDialogHelper.showTitle("确认删除?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSurgeryEntities.remove(position);
                        mSurgeryAdapter.notifyDataSetChanged();
                        sweetAlertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onAddButtonClick() {
                final AddRecordPopup addRecordPopup = new AddRecordPopup(getActivity(), getString(R.string.title_add_surgery_info), getString(R.string.hint_input_surgery_name), getString(R.string.hint_input_surgery_time));
                addRecordPopup.setOnDiagnoseInputClickListener(new AddRecordPopup.OnDiagnoseInputClickListener() {
                    @Override
                    public void onCompleteButtonClick(String name, String time) {
                        SurgeryEntity surgeryEntity = new SurgeryEntity(name, time);
                        mSurgeryEntities.add(surgeryEntity);
                        mSurgeryAdapter.notifyDataSetChanged();
                        addRecordPopup.dismiss();
                    }

                    @Override
                    public void onCancelButtonClick() {
                        addRecordPopup.dismiss();
                    }
                });
                addRecordPopup.showPopupWindow();
            }
        });
    }

    private void initDiseaseView(View view) {
        mRvDetailDisease = view.findViewById(R.id.rv_detail_disease);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mRvDetailDisease.setLayoutManager(mGridLayoutManager);
        mDetailDiseaseAdapter = new DetailDiseaseAdapter(getActivity());
        mRvDetailDisease.setAdapter(mDetailDiseaseAdapter);
        mDetailDiseaseAdapter.setDiseaseEntities(mDetailDiseaseEntities);
        mDetailDiseaseAdapter.setOnAddButtonClickListener(new DetailDiseaseAdapter.OnItemClickListener() {
            @Override
            public void onNormalButtonClick(int position, View view) {
                final DetailDiseaseEntity detailDiseaseEntity = mDetailDiseaseEntities.get(position);

                if (detailDiseaseEntity.isChecked()) {
                    mDialogHelper.showTitle("确认删除?", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            detailDiseaseEntity.setDiagnoseTime(null);
                            detailDiseaseEntity.setChecked(false);
                            mDetailDiseaseAdapter.notifyDataSetChanged();
                            sweetAlertDialog.dismiss();
                        }
                    });

                    return;
                }

                final SingleInputPopup diagnoseInputPopup = new SingleInputPopup(getActivity(), detailDiseaseEntity.getDiseaseName(), "请输入确诊时间(格式:2017-01-01)");
                diagnoseInputPopup.setOnDiagnoseInputClickListener(new SingleInputPopup.OnDiagnoseInputClickListener() {
                    @Override
                    public void onCompleteButtonClick(String diagnoseTime) {
                        boolean newState = !detailDiseaseEntity.isChecked();
                        detailDiseaseEntity.setChecked(newState);
                        detailDiseaseEntity.setDiagnoseTime(diagnoseTime);
                        mDetailDiseaseAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelButtonClick() {
                        diagnoseInputPopup.dismiss();
                    }
                });
                diagnoseInputPopup.showPopupWindow();
            }

            @Override
            public void onAddButtonClick() {
                final AddDiseaseInputPopup addDiseaseInputPopup = new AddDiseaseInputPopup(getActivity());
                addDiseaseInputPopup.setOnDiagnoseInputClickListener(new AddDiseaseInputPopup.OnDiagnoseInputClickListener() {
                    @Override
                    public void onCompleteButtonClick(String name, String time) {
                        DetailDiseaseEntity detailDiseaseEntity = new DetailDiseaseEntity(name, time, true);
                        mDetailDiseaseEntities.add(detailDiseaseEntity);
                        mDetailDiseaseAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelButtonClick() {
                        addDiseaseInputPopup.dismiss();
                    }
                });
                addDiseaseInputPopup.showPopupWindow();
            }
        });
    }

    @Override
    public boolean canNextButtonClick() {
        if (Constant.DEBUG_MODE) {
            return true;
        }

        return true;
    }

    @Override
    public void onNextButtonClick() {
        if (!canNextButtonClick()) {
            return;
        }

        handleDiseaseData();
        handleSurgeryData();
        handleDamageData();
        handleBloodTransData();

        logElderJsonInfo(TAG);
    }

    private void handleBloodTransData() {
        StringBuilder sb = new StringBuilder();
        for (BloodTransfusionEntity bloodTransRecordEntity : mBloodTransRecordEntities) {
            sb.append(bloodTransRecordEntity.getTransfusionReason());
            sb.append("&");
            sb.append(bloodTransRecordEntity.getTransfusionTime());
            sb.append(";");
        }

        mOldPeopleInfo.setShuxue(sb.length() > 0 ? (sb.substring(0, sb.length() - 1)) : sb.toString());
    }

    private void handleDamageData() {
        StringBuilder sb = new StringBuilder();
        for (DamageEntity damageEntity : mDamageEntities) {
            sb.append(damageEntity.getDamageName());
            sb.append("&");
            sb.append(damageEntity.getDamageTime());
            sb.append(";");
        }

        mOldPeopleInfo.setWaishang(sb.length() > 0 ? (sb.substring(0, sb.length() - 1)) : sb.toString());
    }

    private void handleSurgeryData() {
        StringBuilder sb = new StringBuilder();
        for (SurgeryEntity surgeryEntity : mSurgeryEntities) {
            sb.append(surgeryEntity.getSurgeryName());
            sb.append("&");
            sb.append(surgeryEntity.getSurgeryTime());
            sb.append(";");
        }

        mOldPeopleInfo.setShoushu(sb.length() > 0 ? (sb.substring(0, sb.length() - 1)) : sb.toString());
    }

    private void handleDiseaseData() {
        StringBuilder sb = new StringBuilder();
        for (DetailDiseaseEntity detailDiseaseEntity : mDetailDiseaseEntities) {
            if (detailDiseaseEntity.isChecked()) {
                sb.append(detailDiseaseEntity.getDiseaseName());
                sb.append("&");
                sb.append(detailDiseaseEntity.getDiagnoseTime());
                sb.append(";");
            }
        }

        if (sb.length() > 0) {
            sb.substring(0, sb.length() - 2);
        }

        mOldPeopleInfo.setJibing(sb.length() > 0 ? (sb.substring(0, sb.length() - 1)) : sb.toString());
    }
}
