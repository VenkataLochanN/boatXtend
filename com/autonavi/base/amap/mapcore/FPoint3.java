package com.autonavi.base.amap.mapcore;

/* JADX INFO: loaded from: classes.dex */
public class FPoint3 extends FPoint {
    public int colorIndex;

    public FPoint3() {
        this.colorIndex = -1;
    }

    public FPoint3(float f2, float f3, int i) {
        super(f2, f3);
        this.colorIndex = -1;
        this.colorIndex = i;
    }

    public void setColorIndex(int i) {
        this.colorIndex = i;
    }
}