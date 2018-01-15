package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.jinkun.care.Constant;
import com.jinkun.care.R;

/**
 * @Created by coderwjq on 2017/8/21 9:20.
 * @Desc
 */

public class Step8Fragment extends BaseDocFragment {
    private static final String TAG = "Step8Fragment";

    private static Step8Fragment mInstance;
    private EditText mEtXiyangshijian;
    private RadioGroup mRgLiuganyimiao;
    private RadioGroup mRgFeiyan;
    private EditText mEtOtherVaccine;

    public static Step8Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step8Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step8Fragment();
                }
            }
        }
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_8, container, false);
        mEtXiyangshijian = view.findViewById(R.id.et_xiyang_shijian);
        mRgLiuganyimiao = view.findViewById(R.id.rg_liuganyimiao);
        mRgFeiyan = view.findViewById(R.id.rg_feiyan);
        mEtOtherVaccine = view.findViewById(R.id.et_other_vaccine);
        return view;
    }

    @Override
    public boolean canNextButtonClick() {
        if (Constant.DEBUG_MODE) {
            return true;
        }

        boolean result = checkEditText();
        result = result && checkRadioGroup();
        return result;
    }

    private boolean checkRadioGroup() {
        if (mRgFeiyan.getCheckedRadioButtonId() != -1 && mRgLiuganyimiao.getCheckedRadioButtonId() != -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkEditText() {
        return !TextUtils.isEmpty(getTextString(mEtXiyangshijian));
    }

    @Override
    public void onNextButtonClick() {
        if (!canNextButtonClick()) {
            return;
        }

        mOldPeopleInfo.setXiyang(getTextString(mEtXiyangshijian));
        mOldPeopleInfo.setLiugan(getLiuganValue());
        mOldPeopleInfo.setFeiyan(getFeiyanValue());
        mOldPeopleInfo.setOtherVaccine(getOtherVaccine());

        logElderJsonInfo(TAG);
    }

    private String getOtherVaccine() {
        return mEtOtherVaccine.getText().toString();
    }

    private String getFeiyanValue() {
        int checkedRadioButtonId = mRgFeiyan.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.rb_value_1:
                return "0";
            case R.id.rb_value_2:
                return "1";
            case R.id.rb_value_3:
                return "2";
            default:
                return "-1";
        }
    }

    private String getLiuganValue() {
        int checkedRadioButtonId = mRgLiuganyimiao.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.rb_value_1:
                return "0";
            case R.id.rb_value_2:
                return "1";
            case R.id.rb_value_3:
                return "2";
            default:
                return "-1";
        }
    }
}
