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

import com.jinkun.care.R;
import com.jinkun.care.util.NoticeUtils;

import razerdp.basepopup.BasePopupWindow;


/**
 * @Created by coderwjq on 2017/8/17 15:56.
 */
public class AddDiseaseInputPopup extends BasePopupWindow implements View.OnClickListener {
    private Button mCancelButton;
    private Button mCompeleteButton;
    private EditText mEtInputName;
    private EditText mEtInputTime;
    private OnDiagnoseInputClickListener mOnDiagnoseInputClickListener;
    private Context mContext;

    public AddDiseaseInputPopup(Activity context) {
        super(context);
        mContext = context;
        mCancelButton = (Button) findViewById(R.id.btn_cancel);
        mCompeleteButton = (Button) findViewById(R.id.btn_complete);
        mEtInputName = (EditText) findViewById(R.id.et_input_name);
        mEtInputTime = (EditText) findViewById(R.id.et_input_time);

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
        return LayoutInflater.from(getContext()).inflate(R.layout.popup_input_new_disease, null);
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
                String name = mEtInputName.getText().toString();
                String time = mEtInputTime.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(time)) {
                    NoticeUtils.showUserDefineToast(mContext, "请输入完整的信息");
                } else {
                    mOnDiagnoseInputClickListener.onCompleteButtonClick(name, time);
                    dismiss();
                }
                break;
            default:
                break;
        }

    }

    public interface OnDiagnoseInputClickListener {
        void onCompleteButtonClick(String name, String time);

        void onCancelButtonClick();
    }
}
