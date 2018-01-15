package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.jinkun.care.Constant;
import com.jinkun.care.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Created by coderwjq on 2017/8/21 9:20.
 * @Desc
 */

public class Step5Fragment extends BaseDocFragment {
    private static final String TAG = "Step5Fragment";

    private static Step5Fragment mInstance;
    private List<String> data_aids_type;
    private List<String> data_family_care;
    private MultiLineChooseLayout view_ml_aids;
    private MultiLineChooseLayout view_ml_family_care;
    private RadioGroup mView_rg_use_aids;
    private RadioGroup mView_rg_limb_condition;
    private RadioGroup mView_rg_joint_deformity;
    private RadioGroup mView_rg_xiazhi_jingmai_quzhang;
    private RadioGroup mView_rg_xiazhi_shuizhong;
    private RadioGroup mView_rg_pingdixingzou;
    private RadioGroup mView_rg_shangxialouti;
    private RadioGroup mView_rg_shentizhuangkuang;
    private LinearLayout mLlFujuContainer;
    private EditText mView_et_other_care;

    public static Step5Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step5Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step5Fragment();
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
        data_aids_type = Arrays.asList(getResources().getStringArray(R.array.aids_type));
        data_family_care = Arrays.asList(getResources().getStringArray(R.array.family_care));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_5, container, false);

        initAids(view);
        initFamilyCare(view);

        mView_rg_use_aids = view.findViewById(R.id.rg_use_aids);
        mView_rg_limb_condition = view.findViewById(R.id.rg_limb_condition);
        mView_rg_joint_deformity = view.findViewById(R.id.rg_joint_deformity);
        mView_rg_xiazhi_jingmai_quzhang = view.findViewById(R.id.rg_xiazhi_jingmai_quzhang);
        mView_rg_xiazhi_shuizhong = view.findViewById(R.id.rg_xiazhi_shuizhong);
        mView_rg_pingdixingzou = view.findViewById(R.id.rg_pingdixingzou);
        mView_rg_shangxialouti = view.findViewById(R.id.rg_shangxialouti);
        mView_rg_shentizhuangkuang = view.findViewById(R.id.rg_shentizhuangkuang);
        mView_et_other_care = view.findViewById(R.id.et_other_care);

        mLlFujuContainer = view.findViewById(R.id.ll_fuju_container);

        mView_rg_use_aids.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_true:
                        mLlFujuContainer.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_false:
                        mLlFujuContainer.setVisibility(View.GONE);
                        break;
                }
            }
        });
        return view;
    }

    private void initFamilyCare(View view) {
        view_ml_family_care = view.findViewById(R.id.ml_family_care);
        view_ml_family_care.setList(data_family_care);
    }

    private void initAids(View view) {
        view_ml_aids = view.findViewById(R.id.ml_aids);
        view_ml_aids.setList(data_aids_type);
    }


    @Override
    public boolean canNextButtonClick() {
        if (Constant.DEBUG_MODE) {
            return true;
        }

        return checkWidget();
    }

    private boolean checkRadioGroup(RadioGroup... rgs) {
        for (RadioGroup rg : rgs) {
            if (rg.getCheckedRadioButtonId() == -1) {
                return false;
            }
        }

        return true;
    }

    private boolean checkWidget() {
        return checkRadioGroup(mView_rg_use_aids,
                mView_rg_limb_condition, mView_rg_joint_deformity,
                mView_rg_xiazhi_jingmai_quzhang,
                mView_rg_xiazhi_shuizhong,
                mView_rg_pingdixingzou,
                mView_rg_shangxialouti,
                mView_rg_shentizhuangkuang);
    }

    @Override
    public void onNextButtonClick() {
        if (!canNextButtonClick()) {
            return;
        }

        handleData();
        logElderJsonInfo(TAG);
    }

    private void handleData() {
        mOldPeopleInfo.setFuju(mView_rg_use_aids.getCheckedRadioButtonId() == R.id.rb_true ? "0" : "1");
        mOldPeopleInfo.setSizhi(mView_rg_limb_condition.getCheckedRadioButtonId() == R.id.rb_true ? "0" : "1");
        mOldPeopleInfo.setGuanjie(mView_rg_joint_deformity.getCheckedRadioButtonId() == R.id.rb_has_not ? "0" : "1");
        mOldPeopleInfo.setJingmaiquzhang(mView_rg_xiazhi_jingmai_quzhang.getCheckedRadioButtonId() == R.id.rb_has_not ? "0" : "1");
        mOldPeopleInfo.setShuizhong(mView_rg_xiazhi_shuizhong.getCheckedRadioButtonId() == R.id.rb_has_not ? "0" : "1");
        mOldPeopleInfo.setXingzou(getXingzouValue(mView_rg_pingdixingzou));
        mOldPeopleInfo.setLouti(getXingzouValue(mView_rg_shangxialouti));
        mOldPeopleInfo.setZhuangkuang(getZhuangkuangValue(mView_rg_shentizhuangkuang));

        mOldPeopleInfo.setFujuleixing(getFujuLeixing());

        mOldPeopleInfo.setZhaohu(getZhaohu());
    }

    private String getXingzouValue(RadioGroup rg) {
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb_with_no_help:
                return "0";
            case R.id.rb_with_some_help:
                return "1";
            case R.id.rb_with_great_help:
                return "2";
            default:
                return "-1";
        }
    }

    private String getZhuangkuangValue(RadioGroup rg) {
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb_full_care_self:
                return "0";
            case R.id.rb_half_care_self:
                return "1";
            case R.id.rb_none_care_self:
                return "2";
            default:
                return "-1";
        }
    }

    private String getZhaohu() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> selectedItems = view_ml_family_care.getAllItemSelectedIndex();
        for (Integer selectedItem : selectedItems) {
            sb.append(selectedItem + ";");
        }
        String result = "";

        if (!TextUtils.isEmpty(getTextString(mView_et_other_care))) {
            result = sb.append("-其他-" + getTextString(mView_et_other_care)).toString();
        } else {
            result = sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
        }

        return result;
    }

    public String getFujuLeixing() {
        if (mView_rg_use_aids.getCheckedRadioButtonId() == R.id.rb_true) {
            ArrayList<Integer> selectedItems = view_ml_aids.getAllItemSelectedIndex();
            StringBuilder sb = new StringBuilder();
            for (Integer selectedItem : selectedItems) {
                sb.append(selectedItem + ";");
            }
            return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
        } else {
            return "";
        }
    }
}
