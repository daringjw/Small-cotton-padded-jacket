package com.jinkun.care.ui.fragment;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jinkun.care.R;
import com.jinkun.care.callback.OnNextButtonClickListener;
import com.jinkun.care.helper.AccountHelper;
import com.jinkun.care.helper.TakePhotoHelper;
import com.jinkun.care.ui.activity.DocumentActivity;
import com.jinkun.care.util.FileUtils;
import com.jinkun.care.util.NoticeUtils;
import com.jph.takephoto.model.TResult;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.functions.Consumer;

/**
 * @Created by coderwjq on 2017/8/21 9:20.
 * @Desc
 */

public class Step9Fragment extends BaseDocFragment implements OnNextButtonClickListener {
    private static final String TAG = "Step9Fragment";

    private static Step9Fragment mInstance;
    private Button mBtnOpenCamera;
    private ImageView mIvElderPhoto;
    private RxPermissions mRxPermissions;
    private File mTmpPhotoFile;
    private TakePhotoHelper mTakePhotoHelper;
    private Bitmap mElderBitmap;
    private TResult mTResult;

    public static Step9Fragment getInstance() {
        if (mInstance == null) {
            synchronized (Step9Fragment.class) {
                if (mInstance == null) {
                    mInstance = new Step9Fragment();
                }
            }
        }
        return mInstance;
    }

    public TResult getTResult() {
        return mTResult;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRxPermissions = new RxPermissions(getActivity());
        mTakePhotoHelper = new TakePhotoHelper(getTakePhoto());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_9, container, false);

        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        mBtnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRxPermissions.request(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    openCamera();
                                } else {
                                    NoticeUtils.showUserDefineToast(getActivity(), "未获取到照相机权限,功能无法使用");
                                }
                            }
                        });
            }
        });
    }

    private void openCamera() {
        mTmpPhotoFile = FileUtils.createTmpPhotoFile(getActivity());
        mTakePhotoHelper.takePhoto(mTmpPhotoFile);
    }

    private void initView(View view) {
        mBtnOpenCamera = view.findViewById(R.id.btn_open_camera);
        mIvElderPhoto = view.findViewById(R.id.iv_elder_photo);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();

        NoticeUtils.showUserDefineToast(getActivity(), "已取消拍照");

        mTResult = null;
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);

        NoticeUtils.showUserDefineToast(getActivity(), msg);

        mTResult = null;
    }

    @Override
    public void takeSuccess(final TResult result) {
        super.takeSuccess(result);

        mElderBitmap = BitmapFactory.decodeFile(result.getImage().getOriginalPath());
        mTResult = result;
        Log.e(TAG, "takeSuccess: " + mTResult.getImage().getOriginalPath());

        ((DocumentActivity) getActivity()).getPendingElderInfo().setImagePath(mTResult.getImage().getOriginalPath());
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mElderBitmap != null) {
            mIvElderPhoto.setImageBitmap(mElderBitmap);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mElderBitmap != null) {
            mElderBitmap.recycle();
            mElderBitmap = null;
        }
    }


    @Override
    public boolean canNextButtonClick() {
        String token = AccountHelper.getInstance(getActivity()).getCurrentAccountToken();
        if (token == null) {
            NoticeUtils.showUserDefineToast(getActivity(), "登陆数据异常,需要重新登陆");
            return false;
        } else {
            if (mTResult == null) {
                NoticeUtils.showUserDefineToast(getActivity(), "照片数据为空");
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

        Log.e(TAG, "userId: " + AccountHelper.getInstance(getActivity()).getCurrentUserId());
        Log.e(TAG, "mOldPeopleInfo: " + mOldPeopleInfo);

        mOldPeopleInfo.setUserId(AccountHelper.getInstance(getActivity()).getCurrentUserId());
        mOldPeopleInfo.setStationId(AccountHelper.getInstance(getActivity()).getStationId());
        logElderJsonInfo(TAG);
    }
}
