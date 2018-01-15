package com.jinkun.care.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by coderwjq on 16/4/22.
 */
public class CareViewPager extends ViewPager {

    /**
     * 设置viewpager是否可以滑动切换
     */
    private boolean mCanScroll = true;

    public CareViewPager(Context context) {
        super(context);
    }

    public CareViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean canScroll) {
        mCanScroll = canScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mCanScroll && super.onTouchEvent(ev);
    }
}
