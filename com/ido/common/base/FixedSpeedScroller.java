package com.ido.common.base;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes2.dex */
public class FixedSpeedScroller extends Scroller {
    private int mDuration;

    public FixedSpeedScroller(Context context) {
        super(context);
        this.mDuration = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        this.mDuration = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.mDuration);
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.mDuration);
    }

    public void setmDuration(int i) {
        this.mDuration = i;
    }

    public int getmDuration() {
        return this.mDuration;
    }
}