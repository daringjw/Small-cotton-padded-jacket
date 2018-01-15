package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.jinkun.care.Constant;
import com.jinkun.care.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Created by coderwjq on 2017/8/16 16:50.
 * @Desc
 */

public class Step3Fragment extends BaseDocFragment {
    private static final String TAG = "Step3Fragment";

    private static Step3Fragment mInstance;
    @BindView(R.id.et_other_by_father)
    EditText mEtOtherByFather;
    @BindView(R.id.et_other_by_mother)
    EditText mEtOtherByMother;
    @BindView(R.id.et_other_by_brother)
    EditText mEtOtherByBrother;
    @BindView(R.id.et_other_by_children)
    EditText mEtOtherByChildren;
    @BindView(R.id.et_inherited_disease)
    EditText mEtInheritedDisease;
    @BindView(R.id.et_disablity_id)
    EditText mEtDisablityId;
    Unbinder unbinder;
    private MultiLineChooseLayout mMlDiseaseFather;
    private List<String> mDiseaseListDatas;
    private MultiLineChooseLayout mMlDiseaseMother;
    private MultiLineChooseLayout mMlDiseaseChildren;
    private MultiLineChooseLayout mMlDiseaseBrother;
    private MultiLineChooseLayout mMlDisablity;
    private List<String> mDisablityListDatas;

    public static Step3Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step3Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step3Fragment();
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
        mDiseaseListDatas = Arrays.asList(getResources().getStringArray(R.array.family_disease_array));

        mDisablityListDatas = Arrays.asList(getResources().getStringArray(R.array.disablity_array));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_3, container, false);

        initFatherDisease(view);
        initMotherDisease(view);
        initBrotherDisease(view);
        initChildrenDisease(view);
        initDisablityView(view);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initDisablityView(View view) {
        mMlDisablity = view.findViewById(R.id.ml_physical_disablity);
        mMlDisablity.setList(mDisablityListDatas);
    }

    private void initChildrenDisease(View view) {
        mMlDiseaseChildren = view.findViewById(R.id.ml_disease_children);
        mMlDiseaseChildren.setList(mDiseaseListDatas);
    }

    private void initBrotherDisease(View view) {
        mMlDiseaseBrother = view.findViewById(R.id.ml_disease_brother);
        mMlDiseaseBrother.setList(mDiseaseListDatas);
    }

    private void initMotherDisease(View view) {
        mMlDiseaseMother = view.findViewById(R.id.ml_disease_mother);
        mMlDiseaseMother.setList(mDiseaseListDatas);
    }

    private void initFatherDisease(View view) {
        mMlDiseaseFather = view.findViewById(R.id.ml_disease_father);
        mMlDiseaseFather.setList(mDiseaseListDatas);
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

        handleFatherDisease();
        handleMotherDisease();
        handleBrotherDisease();
        handleChildrenDisease();
        handleInheritedDisease();
        handlePhysicalDisablity();

        logElderJsonInfo(TAG);
    }

    private void handlePhysicalDisablity() {
        mOldPeopleInfo.setCanji(getDiseaseString(mMlDisablity, mEtDisablityId));
    }

    private void handleInheritedDisease() {
        mOldPeopleInfo.setYichuan(mEtInheritedDisease.getText().toString());
    }

    private void handleChildrenDisease() {
        mOldPeopleInfo.setSonSister(getDiseaseString(mMlDiseaseChildren, mEtOtherByChildren));
    }

    private void handleBrotherDisease() {
        mOldPeopleInfo.setBrother(getDiseaseString(mMlDiseaseBrother, mEtOtherByBrother));
    }

    private void handleMotherDisease() {
        mOldPeopleInfo.setMother(getDiseaseString(mMlDiseaseMother, mEtOtherByMother));
    }

    private void handleFatherDisease() {
        mOldPeopleInfo.setFather(getDiseaseString(mMlDiseaseFather, mEtOtherByFather));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected String getDiseaseString(MultiLineChooseLayout ml, EditText et) {
        ArrayList<String> selectedDisease = ml.getAllItemSelectedTextWithListArray();
        StringBuilder sb = new StringBuilder();
        for (String disease : selectedDisease) {
            sb.append(disease);
            sb.append(";");
        }
        String result = sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();

        if (!TextUtils.isEmpty(getTextString(et))) {
            result += "&" + getTextString(et);
        }

        return result;
    }
}
