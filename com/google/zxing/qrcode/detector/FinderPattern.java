package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* JADX INFO: loaded from: classes2.dex */
public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;

    FinderPattern(float f2, float f3, float f4) {
        this(f2, f3, f4, 1);
    }

    private FinderPattern(float f2, float f3, float f4, int i) {
        super(f2, f3);
        this.estimatedModuleSize = f4;
        this.count = i;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }

    int getCount() {
        return this.count;
    }

    boolean aboutEquals(float f2, float f3, float f4) {
        if (Math.abs(f3 - getY()) > f2 || Math.abs(f4 - getX()) > f2) {
            return false;
        }
        float fAbs = Math.abs(f2 - this.estimatedModuleSize);
        return fAbs <= 1.0f || fAbs <= this.estimatedModuleSize;
    }

    FinderPattern combineEstimate(float f2, float f3, float f4) {
        int i = this.count;
        int i2 = i + 1;
        float x = (i * getX()) + f3;
        float f5 = i2;
        return new FinderPattern(x / f5, ((this.count * getY()) + f2) / f5, ((this.count * this.estimatedModuleSize) + f4) / f5, i2);
    }
}