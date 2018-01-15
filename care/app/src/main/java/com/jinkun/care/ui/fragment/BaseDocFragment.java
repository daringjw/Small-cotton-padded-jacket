package com.jinkun.care.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import com.jinkun.care.callback.OnNextButtonClickListener;
import com.jinkun.care.helper.DialogHelper;
import com.jinkun.care.model.entity.OldPeopleInfo;
import com.jinkun.care.ui.activity.DocumentActivity;
import com.jinkun.care.util.JsonUtils;
import com.jph.takephoto.app.TakePhotoFragment;

/**
 * @Created by coderwjq on 2017/8/16 17:22.
 * @Desc 当点击下一步的时候，会执行onNextButtonClick方法，就是把当前Fragment输入的数据set到Activity创建的mOldPeopleInfo对象中
 */

public abstract class BaseDocFragment extends TakePhotoFragment implements OnNextButtonClickListener {
    protected DialogHelper mDialogHelper;
    protected DocumentActivity mActivity;
    protected OldPeopleInfo mOldPeopleInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogHelper = new DialogHelper(getActivity());

        mActivity = (DocumentActivity) getActivity();
        mOldPeopleInfo = mActivity.getPendingElderInfo().getOldPeopleInfo();
    }

    public abstract boolean canNextButtonClick();

    public abstract void onNextButtonClick();

    protected String getTextString(EditText content) {
        return content.getText().toString();
    }

    protected String getTextString(Spinner content) {
        return content.getSelectedItem().toString();
    }

    protected boolean checkData(String... args) {
        for (String arg : args) {
            if (TextUtils.isEmpty(arg)) {
                return false;
            }
        }
        return true;
    }

    protected void logElderJsonInfo(String tag) {
        Log.e(tag, "logElderJsonInfo: " + JsonUtils.bean2Json(mOldPeopleInfo));
    }

    protected String getSubLastLetter(StringBuilder sb) {
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }
}
