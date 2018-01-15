package com.jinkun.care.ui.view;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.jinkun.care.R;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * @Created by coderwjq on 2017/8/18 9:27.
 * @Desc
 */

public class ElderDiseaseChoosePopup extends BasePopupWindow implements MultiLineChooseLayout.onItemClickListener {
    private MultiLineChooseLayout mMlDiseaseList;
    private List<String> mDiseaseListData;

    public ElderDiseaseChoosePopup(Activity context) {
        super(context);
        setPopupWindowFullScreen(true);

        initView();
        initListener();
    }

    public void setDiseaseListData(List<String> diseaseListData) {
        mDiseaseListData = diseaseListData;
        mMlDiseaseList.setList(mDiseaseListData);
    }

    private void initListener() {
        mMlDiseaseList.setOnItemClickListener(this);
    }

    private void initView() {
        mMlDiseaseList = (MultiLineChooseLayout) findViewById(R.id.ml_disease_list);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popop_elder_disease_choose);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.ll_container);
    }

    @Override
    protected Animator initExitAnimator() {
        return null;
    }

    @Override
    public Animator initShowAnimator() {
        return null;
    }

    @Override
    public void onItemClick(int position, String text) {

    }
}
