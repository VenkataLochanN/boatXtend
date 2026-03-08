package com.ido.life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes2.dex */
public class CustomChartDetailViewPager extends ViewPager {
    private boolean mScrollAble;

    public CustomChartDetailViewPager(Context context) {
        super(context);
        this.mScrollAble = false;
    }

    public CustomChartDetailViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScrollAble = false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollAble) {
            return false;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollAble) {
            return false;
        }
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean isScrollAble() {
        return this.mScrollAble;
    }

    public void setScrollAble(boolean z) {
        this.mScrollAble = z;
    }
}