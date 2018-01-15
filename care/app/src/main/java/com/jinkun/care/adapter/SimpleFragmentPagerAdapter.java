package com.jinkun.care.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jinkun.care.ui.fragment.BaseDocFragment;

import java.util.List;

/**
 * Created by coderwjq on 15/6/12.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mFm;
    private List<BaseDocFragment> mTabFragments;
    private String[] mTitles;

    public SimpleFragmentPagerAdapter(FragmentManager fm, List<BaseDocFragment> fragments, String[] titles) {
        super(fm);
        mFm = fm;
        mTabFragments = fragments;
        mTitles = titles;
    }

    public void setTabFragments(List<BaseDocFragment> mTabFragments) {
        this.mTabFragments = mTabFragments;
    }

    public void setTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    @Override
    public BaseDocFragment getItem(int position) {
        return mTabFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabFragments != null ? mTabFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
