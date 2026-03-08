package com.google.android.gms.maps.model;

/* JADX INFO: loaded from: classes2.dex */
public final class Gap extends PatternItem {
    public final float length;

    public Gap(float f2) {
        super(2, Float.valueOf(Math.max(f2, 0.0f)));
        this.length = Math.max(f2, 0.0f);
    }

    @Override // com.google.android.gms.maps.model.PatternItem
    public final String toString() {
        float f2 = this.length;
        StringBuilder sb = new StringBuilder(29);
        sb.append("[Gap: length=");
        sb.append(f2);
        sb.append("]");
        return sb.toString();
    }
}