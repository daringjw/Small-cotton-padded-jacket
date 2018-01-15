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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jinkun.care.R.id.ml_defecation;
import static com.jinkun.care.R.id.ml_food_allergy;
import static com.jinkun.care.R.id.ml_non_staple_food;
import static com.jinkun.care.R.id.ml_qiye;
import static com.jinkun.care.R.id.ml_sleep_quality;
import static com.jinkun.care.R.id.ml_staple_food;
import static com.jinkun.care.R.id.ml_wash;

/**
 * @Created by coderwjq on 2017/8/21 9:20.
 * @Desc
 */

public class Step4Fragment extends BaseDocFragment {
    private static final String TAG = "Step4Fragment";

    private static Step4Fragment mInstance;
    @BindView(R.id.et_other_distribution)
    EditText mEtOtherDistribution;
    @BindView(R.id.et_other_staple_food)
    EditText mEtOtherStapleFood;
    @BindView(R.id.et_other_non_staple_food)
    EditText mEtOtherNonStapleFood;
    @BindView(R.id.et_other_food_allergy)
    EditText mEtOtherFoodAllergy;
    @BindView(R.id.et_defecation_frequency)
    EditText mEtDefecationFrequency;
    @BindView(R.id.et_qiye_frequency)
    EditText mEtQiyeFrequency;
    @BindView(R.id.et_wash_frequency)
    EditText mEtWashFrequency;
    @BindView(R.id.et_other_situation)
    EditText mEtOtherSituation;
    Unbinder unbinder;
    private MultiLineChooseLayout mMlHouseworkDistribution;
    private List<String> mHouseworkDistributionDatasource;
    private MultiLineChooseLayout mMlWashClothes;
    private List<String> mWashClothesDatasource;
    private List<String> staple_food_data;
    private MultiLineChooseLayout mMl_staple_food_view;
    private MultiLineChooseLayout ml_non_staple_food_view;
    private List<String> non_staple_food_data;
    private List<String> food_allergy_data;
    private MultiLineChooseLayout view_ml_food_allergy;
    private List<String> data_yes_or_no;
    private MultiLineChooseLayout view_ml_defecation;
    private MultiLineChooseLayout view_ml_qiye;
    private EditText et_qiye_frequency;
    private EditText et_defecation_frequency;
    private List<String> data_ml_sleep_quality;
    private MultiLineChooseLayout view_ml_sleep_quality;
    private List<String> data_wash_frequency;
    private MultiLineChooseLayout view_ml_wash;
    private EditText et_wash_frequency;
    private EditText et_other_situation;
    private RadioGroup mView_rg_physical_exercise;
    private LinearLayout mLlExecContainer;
    private MultiLineChooseLayout mMlExecType;
    private List<String> data_exec_type;
    private RadioGroup mView_rg_exercise_freq;
    private EditText mView_et_exec_per_week;
    private RadioGroup mView_rg_exercise_time_long;
    private EditText mView_et_exec_consist;
    private EditText mView_et_other_exec_type;

    public static Step4Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step4Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step4Fragment();
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
        mHouseworkDistributionDatasource = Arrays.asList(getResources().getStringArray(R.array.housework_distribution));
        mWashClothesDatasource = Arrays.asList(getResources().getStringArray(R.array.wash_clothes));
        staple_food_data = Arrays.asList(getResources().getStringArray(R.array.staple_food));
        non_staple_food_data = Arrays.asList(getResources().getStringArray(R.array.non_staple_food));
        food_allergy_data = Arrays.asList(getResources().getStringArray(R.array.food_allergy));
        data_yes_or_no = Arrays.asList(getResources().getStringArray(R.array.yes_or_no));
        data_ml_sleep_quality = Arrays.asList(getResources().getStringArray(R.array.sleep_quality));
        data_wash_frequency = Arrays.asList(getResources().getStringArray(R.array.wash_frequency));
        data_exec_type = Arrays.asList(getResources().getStringArray(R.array.exec_type));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_4, container, false);

        initHouseDistribution(view);
        initWashClothes(view);
        initStapleFood(view);
        initNonStapleFood(view);
        initAllergyFood(view);
        initPhysicalExercise(view);
        initDefecation(view);
        initQiye(view);
        initSleepQuality(view);
        initWashSituaition(view);
        initOtherSituation(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initOtherSituation(View view) {
        et_other_situation = view.findViewById(R.id.et_other_situation);
    }

    private void initWashSituaition(View view) {
        view_ml_wash = view.findViewById(ml_wash);
        et_wash_frequency = view.findViewById(R.id.et_wash_frequency);
        view_ml_wash.setList(data_wash_frequency);
    }

    private void initSleepQuality(View view) {
        view_ml_sleep_quality = view.findViewById(ml_sleep_quality);
        view_ml_sleep_quality.setList(data_ml_sleep_quality);
    }

    private void initQiye(View view) {
        view_ml_qiye = view.findViewById(ml_qiye);
        et_qiye_frequency = view.findViewById(R.id.et_qiye_frequency);
        view_ml_qiye.setList(data_yes_or_no);
    }

    private void initDefecation(View view) {
        view_ml_defecation = view.findViewById(ml_defecation);
        et_defecation_frequency = view.findViewById(R.id.et_defecation_frequency);
        view_ml_defecation.setList(data_yes_or_no);
    }

    private void initPhysicalExercise(View view) {
        mView_rg_physical_exercise = view.findViewById(R.id.rg_physical_exercise);
        mLlExecContainer = view.findViewById(R.id.ll_exec_container);
        mView_rg_physical_exercise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                clearAllFocus();
                switch (i) {
                    case R.id.rb_has_not:
                        mLlExecContainer.setVisibility(View.GONE);
                        break;
                    case R.id.rb_has:
                        mLlExecContainer.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        mMlExecType = view.findViewById(R.id.ml_exec_type);
        mMlExecType.setList(data_exec_type);

        mView_rg_exercise_freq = view.findViewById(R.id.rg_exercise_freq);
        mView_et_exec_per_week = view.findViewById(R.id.et_exec_per_week);
        mView_rg_exercise_time_long = view.findViewById(R.id.rg_exercise_time_long);
        mView_et_exec_consist = view.findViewById(R.id.et_exec_consist);
        mView_et_other_exec_type = view.findViewById(R.id.et_other_exec_type);

    }

    private void clearAllFocus() {
        mEtOtherDistribution.clearFocus();
        mEtDefecationFrequency.clearFocus();
        mEtOtherFoodAllergy.clearFocus();
        mEtOtherNonStapleFood.clearFocus();
        mEtOtherSituation.clearFocus();
        mEtOtherStapleFood.clearFocus();
        mEtQiyeFrequency.clearFocus();
        mEtWashFrequency.clearFocus();
    }

    private void initAllergyFood(View view) {
        view_ml_food_allergy = view.findViewById(ml_food_allergy);
        view_ml_food_allergy.setList(food_allergy_data);
    }

    private void initNonStapleFood(View view) {
        ml_non_staple_food_view = view.findViewById(ml_non_staple_food);
        ml_non_staple_food_view.setList(non_staple_food_data);
    }

    private void initStapleFood(View view) {
        mMl_staple_food_view = view.findViewById(ml_staple_food);
        mMl_staple_food_view.setList(staple_food_data);
    }

    private void initWashClothes(View view) {
        mMlWashClothes = view.findViewById(R.id.ml_wash_clothes);
        mMlWashClothes.setList(mWashClothesDatasource);
    }

    private void initHouseDistribution(View view) {
        mMlHouseworkDistribution = view.findViewById(R.id.ml_housework_distribution);
        mMlHouseworkDistribution.setList(mHouseworkDistributionDatasource);
    }

    @Override
    public boolean canNextButtonClick() {
        if (Constant.DEBUG_MODE) {
            return true;
        }

        boolean result = checkMultiChoose(mMlHouseworkDistribution, mMlWashClothes,
                mMl_staple_food_view, ml_non_staple_food_view,
                view_ml_food_allergy, view_ml_defecation,
                view_ml_qiye, view_ml_sleep_quality, view_ml_wash);

        result = result && checkRadioGroup();

        return result;
    }

    private boolean checkRadioGroup() {
        int checkedId = mView_rg_physical_exercise.getCheckedRadioButtonId();

        if (checkedId == -1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkEditText(EditText... widgets) {
        for (EditText widget : widgets) {
            if (TextUtils.isEmpty(widget.getText().toString())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMultiChoose(MultiLineChooseLayout... multiWidgets) {
        for (MultiLineChooseLayout multiWidget : multiWidgets) {
            if (multiWidget.getSelectedIndex() == -1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onNextButtonClick() {
        if (!canNextButtonClick()) {
            return;
        }

        handleHouseWorkDistribution();
        handleWashClothes();
        handleStapleFood();
        handleNonStapleFood();
        handleFoodAllergy();
        handleExecData();
        handlePaibian();
        handleQiye();
        handleShuimian();
        handleWashFreq();
        handleOtherInfo();

        logElderJsonInfo(TAG);
    }

    private void handleOtherInfo() {
        mOldPeopleInfo.setOtherInfo(mEtOtherSituation.getText().toString());
    }

    private void handleWashFreq() {
        StringBuilder sb = new StringBuilder();
        int selectedIndex = view_ml_wash.getSelectedIndex();

        sb.append(selectedIndex);

        if (!TextUtils.isEmpty(getTextString(mEtWashFrequency))) {
            sb.append("@" + getTextString(mEtWashFrequency));
        }
        mOldPeopleInfo.setXiyu(sb.toString());
    }

    private void handleShuimian() {
        // 不选时默认为-1
        mOldPeopleInfo.setShuimian(view_ml_sleep_quality.getSelectedIndex() + "");
    }

    private void handleQiye() {
        StringBuilder sb = new StringBuilder();
        int selectedIndex = view_ml_qiye.getSelectedIndex();

        sb.append(selectedIndex);

        if (!TextUtils.isEmpty(getTextString(mEtQiyeFrequency))) {
            sb.append("@" + getTextString(mEtQiyeFrequency));
        }
        mOldPeopleInfo.setQiye(sb.toString());
    }

    private void handlePaibian() {
        StringBuilder sb = new StringBuilder();
        int selectedIndex = view_ml_defecation.getSelectedIndex();

        sb.append(selectedIndex);

        if (!TextUtils.isEmpty(getTextString(mEtDefecationFrequency))) {
            sb.append("@" + getTextString(mEtDefecationFrequency));
        }
        mOldPeopleInfo.setPaibian(sb.toString());
    }

    private void handleExecData() {
        // 这里假设所有group都有选中值
        StringBuilder sb = new StringBuilder();
        String result = "";

        int hasExec = mView_rg_physical_exercise.getCheckedRadioButtonId();

        if (hasExec != R.id.rb_has) {
            sb.append("0");
        } else {
            int execFreq = mView_rg_exercise_freq.getCheckedRadioButtonId();
            if (execFreq == R.id.rb_day) {
                sb.append("0;");
            } else {
                sb.append(mView_et_exec_per_week.getText().toString());
                sb.append(";");
            }

            int timeLong = mView_rg_exercise_time_long.getCheckedRadioButtonId();
            switch (timeLong) {
                case R.id.rb_value_1:
                    sb.append("0;");
                    break;
                case R.id.rb_value_2:
                    sb.append("1;");
                    break;
                case R.id.rb_value_3:
                    sb.append("2;");
                    break;
            }

            sb.append(mView_et_exec_consist.getText().toString() + ";");

            ArrayList<String> selectedItems = mMlExecType.getAllItemSelectedTextWithListArray();

            for (String selectedItem : selectedItems) {
                sb.append(selectedItem + "&");
            }

            if (!TextUtils.isEmpty(getTextString(mView_et_other_exec_type))) {
                result = sb.append(getTextString(mView_et_other_exec_type)).toString();
            } else {
                result = sb.substring(0, sb.length() - 1);
            }
        }

        mOldPeopleInfo.setDuanlian(result);
    }

    private void handleFoodAllergy() {
        mOldPeopleInfo.setShiwuguomin(getIndexString(view_ml_food_allergy, mEtOtherFoodAllergy, ""));
    }

    private void handleNonStapleFood() {
        mOldPeopleInfo.setFushi(getIndexString(ml_non_staple_food_view, mEtOtherNonStapleFood, ""));
    }

    private void handleStapleFood() {
        mOldPeopleInfo.setZhushi(getIndexString(mMl_staple_food_view, mEtOtherStapleFood, ""));
    }

    private void handleWashClothes() {
        mOldPeopleInfo.setXiyi(getIndexString(mMlWashClothes, null, "&"));
    }

    private void handleHouseWorkDistribution() {
        mOldPeopleInfo.setJiawu(getIndexString(mMlHouseworkDistribution, mEtOtherDistribution, "&"));
    }

    protected String getIndexString(MultiLineChooseLayout ml, EditText et, String split) {
        ArrayList<Integer> selectedIndex = ml.getAllItemSelectedIndex();
        StringBuilder sb = new StringBuilder();
        for (Integer index : selectedIndex) {
            sb.append(index);
            sb.append(";");
        }
        String result = sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();

        if (et != null && !TextUtils.isEmpty(getTextString(et))) {
            result += split + getTextString(et);
        }

        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
