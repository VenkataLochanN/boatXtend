package com.ido.life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes2.dex */
public class SportDetailScrollView extends LuScrollView {
    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        return false;
    }

    public SportDetailScrollView(Context context) {
        super(context);
    }

    public SportDetailScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SportDetailScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (childAt instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) childAt;
                if (viewGroup.getChildCount() > 1) {
                    viewGroup.getChildAt(1).getLocationOnScreen(new int[2]);
                    if (motionEvent.getY() < r2[1]) {
                        requestDisallowInterceptTouchEvent(true);
                    } else {
                        requestDisallowInterceptTouchEvent(false);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}