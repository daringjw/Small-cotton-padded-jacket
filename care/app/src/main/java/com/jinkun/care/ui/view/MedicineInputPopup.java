package com.jinkun.care.ui.view;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jinkun.care.R;
import com.jinkun.care.model.entity.MedicineUseEntity;
import com.jinkun.care.util.NoticeUtils;

import razerdp.basepopup.BasePopupWindow;


/**
 * @Created by coderwjq on 2017/8/17 15:56.
 */
public class MedicineInputPopup extends BasePopupWindow implements View.OnClickListener {
    private final TextView mTvTitle;
    private final EditText mEtMorning;
    private final EditText mEtNoon;
    private final EditText mEtNight;
    private final EditText mEtBeforeSleep;
    private final EditText mEtEtRemarks;
    private Button mCancelButton;
    private Button mCompeleteButton;
    private EditText mEtMedicineName;
    private OnDiagnoseInputClickListener mOnDiagnoseInputClickListener;
    private Context mContext;

    public MedicineInputPopup(Activity context) {
        super(context);
        mContext = context;
        mCancelButton = (Button) findViewById(R.id.btn_cancel);
        mCompeleteButton = (Button) findViewById(R.id.btn_complete);
        mEtMedicineName = (EditText) findViewById(R.id.et_medicine_name);
        mEtMorning = (EditText) findViewById(R.id.et_morning);
        mEtNoon = (EditText) findViewById(R.id.et_noon);
        mEtNight = (EditText) findViewById(R.id.et_night);
        mEtBeforeSleep = (EditText) findViewById(R.id.et_before_sleep);
        mEtEtRemarks = (EditText) findViewById(R.id.et_remarks);
        mTvTitle = (TextView) findViewById(R.id.tv_title);

//        setAutoShowInputMethod(true);
        bindEvent();
    }

    public void setOnDiagnoseInputClickListener(OnDiagnoseInputClickListener onDiagnoseInputClickListener) {
        mOnDiagnoseInputClickListener = onDiagnoseInputClickListener;
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    private void bindEvent() {
        mCancelButton.setOnClickListener(this);
        mCompeleteButton.setOnClickListener(this);
    }

    @Override
    public Animator initShowAnimator() {
        return getDefaultSlideFromBottomAnimationSet();
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.popup_input_medicine_use, null);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    @Override
    public Animator initExitAnimator() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                mOnDiagnoseInputClickListener.onCancelButtonClick();
                dismiss();
                break;
            case R.id.btn_complete:
                String medicineName = mEtMedicineName.getText().toString();
                String morning = mEtMorning.getText().toString();
                String noon = mEtNoon.getText().toString();
                String night = mEtNight.getText().toString();
                String beforeSleep = mEtBeforeSleep.getText().toString();
                String remarks = mEtEtRemarks.getText().toString();
                if (TextUtils.isEmpty(medicineName)) {
                    NoticeUtils.showUserDefineToast(mContext, "请输入药物名称");
                } else {
                    MedicineUseEntity entity = new MedicineUseEntity();
                    entity.setMedicineName(medicineName);
                    if (!TextUtils.isEmpty(morning)) {
                        entity.setMorning(morning);
                    } else {
                        entity.setMorning("0");
                    }

                    if (!TextUtils.isEmpty(noon)) {
                        entity.setNoon(noon);
                    } else {
                        entity.setNoon("0");
                    }

                    if (!TextUtils.isEmpty(night)) {
                        entity.setNight(night);
                    } else {
                        entity.setNight("0");
                    }

                    if (!TextUtils.isEmpty(beforeSleep)) {
                        entity.setBeforeSleep(beforeSleep);
                    } else {
                        entity.setBeforeSleep("0");
                    }

                    if (!TextUtils.isEmpty(remarks)) {
                        entity.setRemarks(remarks);
                    } else {
                        entity.setRemarks("无");
                    }
                    mOnDiagnoseInputClickListener.onCompleteButtonClick(entity);
                    dismiss();
                }
                break;
            default:
                break;
        }

    }

    public interface OnDiagnoseInputClickListener {
        void onCompleteButtonClick(MedicineUseEntity entity);

        void onCancelButtonClick();
    }
}
