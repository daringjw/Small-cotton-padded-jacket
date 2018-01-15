package com.jinkun.care.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jinkun.care.Constant;
import com.jinkun.care.R;
import com.jinkun.care.helper.AudioRecordHelper;
import com.jinkun.care.helper.DialogHelper;
import com.jinkun.care.helper.UploadElderDataHelper;
import com.jinkun.care.model.entity.OldPeopleInfo;
import com.jinkun.care.model.entity.PendingElderInfo;
import com.jinkun.care.service.UploadElderInfoService;
import com.jinkun.care.ui.fragment.BaseDocFragment;
import com.jinkun.care.ui.fragment.Step1Fragment;
import com.jinkun.care.ui.fragment.Step2Fragment;
import com.jinkun.care.ui.fragment.Step3Fragment;
import com.jinkun.care.ui.fragment.Step4Fragment;
import com.jinkun.care.ui.fragment.Step5Fragment;
import com.jinkun.care.ui.fragment.Step6Fragment;
import com.jinkun.care.ui.fragment.Step7Fragment;
import com.jinkun.care.ui.fragment.Step8Fragment;
import com.jinkun.care.ui.fragment.Step9Fragment;
import com.jinkun.care.util.FileUtils;
import com.jinkun.care.util.NetworkUtils;
import com.jinkun.care.util.NoticeUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 老人建档功能主界面，主Activity，里面包含有9个Fragment
 */
public class DocumentActivity extends AppCompatActivity {
    private static final String TAG = "DocumentActivity";

    @BindView(R.id.tv_prev)
    TextView mTvPrev;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_next)
    TextView mTvNext;

    private int mCurrentFragmentPosition = 0;
    private List<Fragment> mFragments = new LinkedList<>();
    private String[] mFragmentTitles;
    private DialogHelper mDialogHelper;
    private String recorderFilePath = FileUtils.generateFileName() + ".wav";
    private PendingElderInfo mPendingElderInfo;
    private long mLastTime;

    public static void invoke(Activity srcActivity, boolean finishSelf) {
        Intent intent = new Intent();
        intent.setClass(srcActivity, DocumentActivity.class);
        srcActivity.startActivity(intent);

        if (finishSelf) {
            srcActivity.finish();
        }
    }

    public PendingElderInfo getPendingElderInfo() {
        return mPendingElderInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        ButterKnife.bind(this);

        mLastTime = System.currentTimeMillis();
        Log.d(TAG, "DocumentActivity onCreate() called with: time = [" + mLastTime + "]");

        performTest();

        initView();
        initRecorder();
        initFragments();

        initElderData();
        initAdapter();

        performTest();
    }

    private void initElderData() {
        if (mPendingElderInfo == null) {
            mPendingElderInfo = new PendingElderInfo();
            mPendingElderInfo.setOldPeopleInfo(new OldPeopleInfo());
        }
    }

    /**
     * 初始化录音工具
     */
    private void initRecorder() {
        AudioRecordHelper.getInstance(DocumentActivity.this).init(Constant.DEFAULT_SAVE_AUDIO_PATH + recorderFilePath);
        AudioRecordHelper.getInstance(DocumentActivity.this).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void performTest() {
        Log.d(TAG, "DocumentActivity onStart() called with: time = [" + (System.currentTimeMillis() - mLastTime) + "]");
        mLastTime = System.currentTimeMillis();
    }

    private void initAdapter() {
    }

    private void initView() {
        mDialogHelper = new DialogHelper(this);
    }

    private void initFragments() {
        mDialogHelper.showLoadingDialog("正在进行初始化...");

        mFragments.add(Step1Fragment.getInstance());
        mFragments.add(Step2Fragment.getInstance());
        mFragments.add(Step3Fragment.getInstance());
        mFragments.add(Step4Fragment.getInstance());
        mFragments.add(Step5Fragment.getInstance());
        mFragments.add(Step6Fragment.getInstance());
        mFragments.add(Step7Fragment.getInstance());
        mFragments.add(Step8Fragment.getInstance());
        mFragments.add(Step9Fragment.getInstance());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        for (Fragment fragment : mFragments) {
            transaction.add(R.id.fl_container, fragment);
        }
        transaction.commit();

        mFragmentTitles = getResources().getStringArray(R.array.create_doc_step);

        handleStatusBar(false);

        mDialogHelper.dismiss();
    }

    private void handleStatusBar(boolean isNextButtonClick) {
        if (mCurrentFragmentPosition < 0) {
            mCurrentFragmentPosition = 0;
            mDialogHelper.showWarnDialog("提示", "确定退出?", "确定", "取消", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    mDialogHelper.dismiss();
                    finish();
                }
            });
            return;
        }

        // 点击完成时，执行的逻辑
        // 对应老人建档过程中的最后一步时的处理逻辑
        if (mCurrentFragmentPosition > mFragments.size() - 1) {
            mCurrentFragmentPosition = mFragments.size() - 1;
            mDialogHelper.showTitle("确定保存并退出?", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    mDialogHelper.dismiss();
                    handleAudioRecorder(true);

                    // 将老人信息添加至本地缓存
                    UploadElderDataHelper.getInstance(DocumentActivity.this).addElderInfoToCache(mPendingElderInfo);
                    // 判断当前网络是否可用
                    if (NetworkUtils.isNetworkAvailable(DocumentActivity.this)) {
                        // 网络可用
                        startService(new Intent(DocumentActivity.this, UploadElderInfoService.class));
                    } else {
                        // 网络不可用
                        NoticeUtils.showUserDefineToast(DocumentActivity.this, "已保存至离线数据");
                    }
                    finish();
                }
            });
            return;
        }

        if (mCurrentFragmentPosition == 0) {
            mTvPrev.setText("返回");
            mTvNext.setText("下一步");
        } else if (mCurrentFragmentPosition == mFragments.size() - 1) {
            mTvPrev.setText("上一步");
            mTvNext.setText("完成");
        } else {
            mTvPrev.setText("上一步");
            mTvNext.setText("下一步");
        }

        mTvTitle.setText(mFragmentTitles[mCurrentFragmentPosition]);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment current = mFragments.get(i);

            if (i == mCurrentFragmentPosition) {
                transaction.show(current);
            } else {
                transaction.hide(current);
            }
        }
        transaction.commit();
    }

    @OnClick({R.id.tv_prev, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_prev:
                mCurrentFragmentPosition--;
                handleStatusBar(false);
                break;
            case R.id.tv_next:
                BaseDocFragment currentFragment = (BaseDocFragment) mFragments.get(mCurrentFragmentPosition);
                if (currentFragment.canNextButtonClick()) {
                    currentFragment.onNextButtonClick();
                    mCurrentFragmentPosition++;
                    handleStatusBar(true);
                } else {
                    NoticeUtils.showUserDefineToast(DocumentActivity.this, "信息填写不完整");
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        mDialogHelper.showWarnDialog("提示", "确定退出?", "确定", "取消", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialogHelper.dismiss();
                finish();
                handleAudioRecorder(false);
            }
        });
    }

    private void handleAudioRecorder(boolean states) {
        AudioRecordHelper.getInstance(DocumentActivity.this).stop();

        File recordFile = new File(Constant.DEFAULT_SAVE_AUDIO_PATH + recorderFilePath);
        if (!recordFile.exists()) {
            NoticeUtils.showUserDefineToast(this, "录音文件不存在");
            return;
        }

        if (!states) {
            // 如果中途退出，则需要删除录音文件
            boolean deleteResult = recordFile.delete();
            NoticeUtils.showSuccessInfo("文件删除结果:" + deleteResult);
        } else {
            // 所有信息填写完毕，才会上传录音文件
            mPendingElderInfo.setAudioPath(recordFile.getPath());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (AudioRecordHelper.getInstance(this).isRecording()) {
            AudioRecordHelper.getInstance(DocumentActivity.this).stop();
        }
    }
}
