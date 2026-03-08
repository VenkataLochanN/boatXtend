package com.ido.life.bean;

import android.graphics.PointF;

/* JADX INFO: loaded from: classes2.dex */
public class BaseCharBean extends PointF {
    public static final int COLOR_DEFAULT = -1;
    private float mActualValue;
    private boolean mHasEffect;
    private int mIndex;

    public BaseCharBean() {
        this.mHasEffect = false;
    }

    public BaseCharBean(float f2, float f3, boolean z) {
        super(f2, f3);
        this.mHasEffect = false;
        this.mHasEffect = z;
    }

    public BaseCharBean(int i, float f2, float f3) {
        super(f2, f3);
        this.mHasEffect = false;
        this.mIndex = i;
        this.mActualValue = f3;
    }

    public BaseCharBean(float f2, float f3, int i, float f4) {
        super(f2, f3);
        this.mHasEffect = false;
        this.mIndex = i;
        this.mActualValue = f4;
        this.mHasEffect = true;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int i) {
        this.mIndex = i;
    }

    public float getActualValue() {
        return this.mActualValue;
    }

    public void setActualValue(float f2) {
        this.mActualValue = f2;
        this.mHasEffect = true;
    }

    public boolean hasEffect() {
        return this.mHasEffect;
    }

    public void setHasEffect(boolean z) {
        this.mHasEffect = z;
    }
}