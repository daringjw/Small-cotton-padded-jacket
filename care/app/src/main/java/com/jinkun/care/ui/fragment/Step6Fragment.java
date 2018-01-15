package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
 * @Created by coderwjq on 2017/8/21 9:20.
 * @Desc
 */

public class Step6Fragment extends BaseDocFragment {
    private static final String TAG = "Step6Fragment";

    private static Step6Fragment mInstance;
    @BindView(R.id.et_other_medical_server)
    EditText mEtOtherMedicalServer;
    @BindView(R.id.et_other_security)
    EditText mEtOtherSecurity;
    Unbinder unbinder;
    private List<String> data_live_care_type;
    private List<String> data_medical_server_type;
    private List<String> data_security_type;
    private MultiLineChooseLayout view_live_care_type;
    private MultiLineChooseLayout view_medical_server_type;
    private MultiLineChooseLayout view_security_type;

    public static Step6Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step6Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step6Fragment();
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
        data_live_care_type = Arrays.asList(getResources().getStringArray(R.array.live_care_type));
        data_medical_server_type = Arrays.asList(getResources().getStringArray(R.array.medical_server_type));
        data_security_type = Arrays.asList(getResources().getStringArray(R.array.security_type));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_6, container, false);

        initLiveCare(view);
        initMedicalServer(view);
        initSecurity(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initSecurity(View view) {
        view_security_type = view.findViewById(R.id.ml_security);
        view_security_type.setList(data_security_type);
    }

    private void initMedicalServer(View view) {
        view_medical_server_type = view.findViewById(R.id.ml_medical_server);
        view_medical_server_type.setList(data_medical_server_type);
    }

    private void initLiveCare(View view) {
        view_live_care_type = view.findViewById(R.id.ml_live_care);
        view_live_care_type.setList(data_live_care_type);
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

        mOldPeopleInfo.setShenghuo(getTextString(view_live_care_type, null, ""));
        mOldPeopleInfo.setYiliao(getTextString(view_medical_server_type, mEtOtherMedicalServer, "-其他-"));
        mOldPeopleInfo.setAnquan(getTextString(view_security_type, mEtOtherSecurity, "-其他-"));

        logElderJsonInfo(TAG);
    }

    private String getTextString(MultiLineChooseLayout ml, EditText et, @NonNull String appendText) {
        String result = "";
        StringBuilder sb = new StringBuilder();

        ArrayList<String> selectedItems = ml.getAllItemSelectedTextWithListArray();
        for (String selectedItem : selectedItems) {
            sb.append(selectedItem + ";");
        }

        if (et != null) {
            if (!TextUtils.isEmpty(et.getText().toString())) {
                result = sb.append(appendText + et.getText().toString()).toString();
            } else {
                result = getSubLastLetter(sb);
            }
        } else {
            result = getSubLastLetter(sb);
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
