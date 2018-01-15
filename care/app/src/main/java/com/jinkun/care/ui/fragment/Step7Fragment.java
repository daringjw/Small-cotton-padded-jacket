package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.jinkun.care.Constant;
import com.jinkun.care.R;
import com.jinkun.care.adapter.HospitalAdapter;
import com.jinkun.care.adapter.MedicineAdapter;
import com.jinkun.care.model.entity.HospitalEntity;
import com.jinkun.care.model.entity.MedicineUseEntity;
import com.jinkun.care.ui.view.MedicineInputPopup;
import com.jinkun.care.ui.view.SingleInputPopup;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @Created by coderwjq on 2017/8/21 9:20.
 * @Desc
 */

public class Step7Fragment extends BaseDocFragment {
    private static final String TAG = "Step7Fragment";

    private static Step7Fragment mInstance;
    private RecyclerView mRvHospitalName;
    private GridLayoutManager mHospitalLayoutManager;
    private HospitalAdapter mHospitalAdapter;
    private ArrayList<HospitalEntity> mHospitalEntities;
    private RecyclerView mRvMedicineUseDetail;
    private LinearLayoutManager mMedicineUseLayoutManager;
    private MedicineAdapter mMedicineAdapter;
    private ArrayList<MedicineUseEntity> mMedicineUseEntities;
    private RadioGroup mRgUseMedicine;
    private LinearLayout mLlTakeByMonth;
    private RadioGroup mRgTakeMedicine;
    private EditText mEtByDay;
    private LinearLayout mLlTakeByDay;
    private EditText mEtByMonth;

    public static Step7Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step7Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step7Fragment();
                }
            }
        }
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_7, container, false);

        initHospitalView(view);
        initMedicineDataView(view);

        initOtherWidget(view);
        return view;
    }

    private void initOtherWidget(View view) {
        mRgUseMedicine = view.findViewById(R.id.rg_use_medicine);
        mRgTakeMedicine = view.findViewById(R.id.rg_take_medicine);
        mLlTakeByMonth = view.findViewById(R.id.ll_take_by_month);
        mLlTakeByDay = view.findViewById(R.id.ll_take_by_day);
        mRgTakeMedicine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_value_1:
                        mLlTakeByDay.setVisibility(View.GONE);
                        mLlTakeByMonth.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_value_2:
                        mLlTakeByDay.setVisibility(View.VISIBLE);
                        mLlTakeByMonth.setVisibility(View.GONE);
                        break;
                    case R.id.rb_value_3:
                        mLlTakeByDay.setVisibility(View.GONE);
                        mLlTakeByMonth.setVisibility(View.GONE);
                        break;
                }
            }
        });

        mEtByDay = view.findViewById(R.id.et_by_day);
        mEtByMonth = view.findViewById(R.id.et_by_month);
    }

    private void initMedicineDataView(View view) {
        mRvMedicineUseDetail = view.findViewById(R.id.rv_medicine_use_detail);
        mMedicineUseLayoutManager = new LinearLayoutManager(getActivity());
        mRvMedicineUseDetail.setLayoutManager(mMedicineUseLayoutManager);
        mMedicineAdapter = new MedicineAdapter(getActivity());
        mRvMedicineUseDetail.setAdapter(mMedicineAdapter);
        mMedicineUseEntities = new ArrayList<>();
        mMedicineAdapter.setDatas(mMedicineUseEntities);
        mMedicineAdapter.setOnItemClickListener(new MedicineAdapter.OnItemClickListener() {
            @Override
            public void onDeleteButtonClick(final int position) {
                mDialogHelper.showTitle("确认删除?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mMedicineUseEntities.remove(position);
                        mMedicineAdapter.notifyDataSetChanged();
                        sweetAlertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onAddButtonClick() {
                final MedicineInputPopup diagnoseInputPopup = new MedicineInputPopup(getActivity());
                diagnoseInputPopup.setOnDiagnoseInputClickListener(new MedicineInputPopup.OnDiagnoseInputClickListener() {
                    @Override
                    public void onCompleteButtonClick(MedicineUseEntity entity) {
                        mMedicineUseEntities.add(entity);
                        mMedicineAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelButtonClick() {
                        diagnoseInputPopup.dismiss();
                    }
                });
                diagnoseInputPopup.showPopupWindow();
            }
        });
    }

    private void initHospitalView(View view) {
        mRvHospitalName = view.findViewById(R.id.rv_hospital_name);
        mHospitalLayoutManager = new GridLayoutManager(getActivity(), 4);
        mRvHospitalName.setLayoutManager(mHospitalLayoutManager);
        mHospitalAdapter = new HospitalAdapter(getActivity());
        mRvHospitalName.setAdapter(mHospitalAdapter);
        mHospitalEntities = new ArrayList<>();
        mHospitalAdapter.setDatas(mHospitalEntities);
        mHospitalAdapter.setOnItemClickListener(new HospitalAdapter.OnItemClickListener() {
            @Override
            public void onDeleteButtonClick(final int position) {
                mDialogHelper.showTitle("确认删除?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mHospitalEntities.remove(position);
                        mHospitalAdapter.notifyDataSetChanged();
                        sweetAlertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onAddButtonClick() {
                final SingleInputPopup diagnoseInputPopup = new SingleInputPopup(getActivity(), "添加医院数据", "请输入医院名称");
                diagnoseInputPopup.setOnDiagnoseInputClickListener(new SingleInputPopup.OnDiagnoseInputClickListener() {
                    @Override
                    public void onCompleteButtonClick(String hospitalName) {
                        mEtByDay.clearFocus();
                        mEtByMonth.clearFocus();

                        HospitalEntity entity = new HospitalEntity(hospitalName);
                        mHospitalEntities.add(entity);
                        mHospitalAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelButtonClick() {
                        mEtByDay.clearFocus();
                        mEtByMonth.clearFocus();

                        diagnoseInputPopup.dismiss();
                    }
                });
                diagnoseInputPopup.showPopupWindow();
            }
        });
    }


    @Override
    public boolean canNextButtonClick() {
        if (Constant.DEBUG_MODE) {
            return true;
        }

        boolean result = checkRadioGroup();
        result = result && checkEditText();
        return result;
    }

    private boolean checkEditText() {
        switch (mRgTakeMedicine.getCheckedRadioButtonId()) {
            case R.id.rb_value_1:
                if (TextUtils.isEmpty(getTextString(mEtByMonth))) {
                    return false;
                }
                break;
            case R.id.rb_value_2:
                if (TextUtils.isEmpty(getTextString(mEtByDay))) {
                    return false;
                }
                break;
        }

        return true;
    }

    private boolean checkRadioGroup() {
        int takeMedicineId = mRgTakeMedicine.getCheckedRadioButtonId();
        int useMedicineId = mRgUseMedicine.getCheckedRadioButtonId();

        if (takeMedicineId == -1 || useMedicineId == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onNextButtonClick() {
        if (!canNextButtonClick()) {
            return;
        }

        mOldPeopleInfo.setFuyao(getFuyaoValue());
        mOldPeopleInfo.setKaiyao(getKaiyaoValue());
        mOldPeopleInfo.setDrugStore(getDrugStore());
        mOldPeopleInfo.setDrugList(getDrugList());
        logElderJsonInfo(TAG);
    }

    private String getDrugList() {
        StringBuilder sb = new StringBuilder();
        for (MedicineUseEntity medicineUseEntity : mMedicineUseEntities) {
            sb.append(medicineUseEntity.getMedicineName());
            sb.append("&" + medicineUseEntity.getMorning());
            sb.append("&" + medicineUseEntity.getNoon());
            sb.append("&" + medicineUseEntity.getNight());
            sb.append("&" + medicineUseEntity.getBeforeSleep());
            sb.append("&" + medicineUseEntity.getRemarks() + "@");
        }

        return getSubLastLetter(sb);
    }

    private String getDrugStore() {
        StringBuilder sb = new StringBuilder();
        for (HospitalEntity hospitalEntity : mHospitalEntities) {
            sb.append(hospitalEntity.getHospitalName() + ";");
        }

        return getSubLastLetter(sb);
    }

    private String getKaiyaoValue() {
        int checkedRadioButtonId = mRgTakeMedicine.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.rb_value_1:
                return "0;" + mEtByMonth.getText().toString();
            case R.id.rb_value_2:
                return "1;" + mEtByDay.getText().toString();
            case R.id.rb_value_3:
                return "2";
            default:
                return "-1";
        }
    }

    private String getFuyaoValue() {
        int checkUseMedicineID = mRgUseMedicine.getCheckedRadioButtonId();
        switch (checkUseMedicineID) {
            case R.id.rb_value_1:
                return "0";
            case R.id.rb_value_2:
                return "1";
            case R.id.rb_value_3:
                return "2";
            case R.id.rb_value_4:
                return "3";
            default:
                return "-1";
        }
    }
}
