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
import com.jinkun.care.util.NoticeUtils;

import razerdp.basepopup.BasePopupWindow;


/**
 * @Created by coderwjq on 2017/8/17 15:56.
 */
public class SingleInputPopup extends BasePopupWindow implements View.OnClickListener {
    private final TextView mTvTitle;
    private Button mCancelButton;
    private Button mCompeleteButton;
    private EditText mInputEdittext;
    private OnDiagnoseInputClickListener mOnDiagnoseInputClickListener;
    private Context mContext;

    public SingleInputPopup(Activity context, String title, String hint) {
        super(context);
        mContext = context;
        mCancelButton = (Button) findViewById(R.id.btn_cancel);
        mCompeleteButton = (Button) findViewById(R.id.btn_complete);
        mInputEdittext = (EditText) findViewById(R.id.et_input);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText(title);
        mInputEdittext.setHint(hint);

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
    public EditText getInputView() {
        return mInputEdittext;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.popup_input_diagnose_time, null);
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
                String result = mInputEdittext.getText().toString();
                if (TextUtils.isEmpty(result)) {
                    NoticeUtils.showUserDefineToast(mContext, "请输入完整的数据信息");
                } else {
                    mOnDiagnoseInputClickListener.onCompleteButtonClick(result);
                    dismiss();
                }
                break;
            default:
                break;
        }

    }

    public interface OnDiagnoseInputClickListener {
        void onCompleteButtonClick(String diagnoseTime);

        void onCancelButtonClick();
    }
}
