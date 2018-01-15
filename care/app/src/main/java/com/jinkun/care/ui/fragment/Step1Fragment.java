package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.jinkun.care.Constant;
import com.jinkun.care.R;
import com.jinkun.care.helper.DataTransHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Created by coderwjq on 2017/8/16 16:50.
 * @Desc
 */

public class Step1Fragment extends BaseDocFragment {
    private static final String TAG = "Step1Fragment";

    private static Step1Fragment mInstance;
    @BindView(R.id.et_elder_name)
    EditText mEtElderName;
    @BindView(R.id.et_nationality)
    EditText mEtNationality;
    @BindView(R.id.et_family_phone)
    EditText mEtFamilyPhone;
    @BindView(R.id.et_profession)
    EditText mEtProfession;
    @BindView(R.id.sp_profession_state)
    Spinner mSpProfessionState;
    @BindView(R.id.et_elder_idcard)
    EditText mEtElderIdcard;
    @BindView(R.id.sp_elder_type)
    Spinner mSpElderType;
    Unbinder unbinder;
    @BindView(R.id.sp_elder_sex)
    Spinner mSpElderSex;
    @BindView(R.id.sp_education_degree)
    Spinner mSpEducationDegree;
    @BindView(R.id.et_income)
    EditText mEtIncome;
    @BindView(R.id.sp_village)
    Spinner mSpVillage;
    @BindView(R.id.sp_marriage_state)
    Spinner mSpMarriageState;
    @BindView(R.id.et_live_address)
    EditText mEtLiveAddress;
    @BindView(R.id.sp_live_type)
    Spinner mSpLiveType;
    @BindView(R.id.et_household_address)
    EditText mEtHouseholdAddress;
    @BindView(R.id.sp_family_state)
    Spinner mSpFamilyState;
    @BindView(R.id.sp_medical_pay_type)
    Spinner mSpMedicalPayType;
    @BindView(R.id.et_emergency_name)
    EditText mEtEmergencyName;
    @BindView(R.id.et_emergency_phone)
    EditText mEtEmergencyPhone;
    @BindView(R.id.sp_blood_type)
    Spinner mSpBloodType;
    @BindView(R.id.sp_exposure_history)
    Spinner mSpExposureHistory;
    @BindView(R.id.et_medicine_allergy_history)
    EditText mEtMedicineAllergyHistory;
    private String mStrElderName;
    private String mStrNationality;
    private String mStrFamilyPhone;
    private String mStrProfession;
    private String mStrProfessionState;
    private String mStrElderIdCard;
    private String mStrElderType;
    private String mStrElderSex;
    private String mStrEducationDegree;
    private String mStrIncome;
    private String mStrVillage;
    private String mStrMarriageState;
    private String mStrLiveAddress;
    private String mStrLiveType;
    private String mStrHouseHoldAddress;
    private String mStrFamilyState;
    private String mStrMedicalPayType;
    private String mStrEmergencyName;
    private String mStrEmergencyPhone;
    private String mStrBloodType;
    private String mStrExposureHistory;
    private String mStrMedicineAllergyHistory;


    public static Step1Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step1Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step1Fragment();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_1, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public boolean canNextButtonClick() {
        mStrElderName = getTextString(mEtElderName);
        mStrElderSex = getTextString(mSpElderSex);
        mStrNationality = getTextString(mEtNationality);
        mStrFamilyPhone = getTextString(mEtFamilyPhone);
        mStrProfession = getTextString(mEtProfession);
        mStrProfessionState = getTextString(mSpProfessionState);
        mStrElderIdCard = getTextString(mEtElderIdcard);
        mStrElderType = getTextString(mSpElderType);
        mStrEducationDegree = getTextString(mSpEducationDegree);
        mStrIncome = getTextString(mEtIncome);
        mStrVillage = getTextString(mSpVillage);
        mStrMarriageState = getTextString(mSpMarriageState);
        mStrLiveAddress = getTextString(mEtLiveAddress);
        mStrLiveType = getTextString(mSpLiveType);
        mStrHouseHoldAddress = getTextString(mEtHouseholdAddress);
        mStrFamilyState = getTextString(mSpFamilyState);
        mStrMedicalPayType = getTextString(mSpMedicalPayType);
        mStrEmergencyName = getTextString(mEtEmergencyName);
        mStrEmergencyPhone = getTextString(mEtEmergencyPhone);
        mStrBloodType = getTextString(mSpBloodType);
        mStrExposureHistory = getTextString(mSpExposureHistory);
        mStrMedicineAllergyHistory = getTextString(mEtMedicineAllergyHistory);

        if (Constant.DEBUG_MODE) {
            return true;
        } else {
            return checkData(mStrElderName, mStrElderSex, mStrNationality, mStrFamilyPhone,
                    mStrProfession, mStrProfessionState, mStrElderIdCard, mStrElderType,
                    mStrEducationDegree, mStrIncome, mStrVillage, mStrMarriageState, mStrLiveAddress,
                    mStrLiveType, mStrHouseHoldAddress, mStrFamilyState, mStrMedicalPayType,
                    mStrEmergencyName, mStrEmergencyPhone, mStrBloodType, mStrExposureHistory,
                    mStrMedicineAllergyHistory);
        }
    }

    @Override
    public void onNextButtonClick() {
        if (!canNextButtonClick()) {
            return;
        }

        mOldPeopleInfo.setName(mStrElderName);
        mOldPeopleInfo.setSex(DataTransHelper.getElderSex(mStrElderSex));
        mOldPeopleInfo.setNation(mStrNationality);
        mOldPeopleInfo.setIdCard(mStrElderIdCard);
        mOldPeopleInfo.setPhone(mStrFamilyPhone);
        mOldPeopleInfo.setWorkInfo(mStrProfession);
        mOldPeopleInfo.setWorkState(mStrProfessionState);
        mOldPeopleInfo.setOldType(mStrElderType);
        mOldPeopleInfo.setEducationBack(mStrEducationDegree);
        mOldPeopleInfo.setIncome(mStrIncome);
        mOldPeopleInfo.setCommunityName(mStrVillage);
        mOldPeopleInfo.setMaritalStatus(mStrMarriageState);
        mOldPeopleInfo.setAddress(mStrLiveAddress);
        mOldPeopleInfo.setIsCensus(DataTransHelper.getSensus(mStrLiveType));
        mOldPeopleInfo.setCencusAddr(mStrHouseHoldAddress);
        mOldPeopleInfo.setYanglao(mStrFamilyState);
        mOldPeopleInfo.setPayType(mStrMedicalPayType);
        mOldPeopleInfo.setLinkName(mStrEmergencyName);
        mOldPeopleInfo.setLinkPhone(mStrEmergencyPhone);
        mOldPeopleInfo.setBloodType(mStrBloodType);
        mOldPeopleInfo.setExpose(mStrExposureHistory);
        mOldPeopleInfo.setAllergy(mStrMedicineAllergyHistory);

        logElderJsonInfo(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
