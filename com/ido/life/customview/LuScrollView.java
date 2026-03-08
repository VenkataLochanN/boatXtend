package com.ido.life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class LuScrollView extends ScrollView {
    int mLastMotionY;
    private int mTouchSlop;

    public LuScrollView(Context context) {
        super(context);
        init(context);
    }

    public LuScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public LuScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * 2;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > 1) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}