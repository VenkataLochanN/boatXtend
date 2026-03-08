package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(BitmapDescriptor bitmapDescriptor) {
        this(bitmapDescriptor, 10.0f);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CustomCap(BitmapDescriptor bitmapDescriptor, float f2) {
        BitmapDescriptor bitmapDescriptor2 = (BitmapDescriptor) Preconditions.checkNotNull(bitmapDescriptor, "bitmapDescriptor must not be null");
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("refWidth must be positive");
        }
        super(bitmapDescriptor2, f2);
        this.bitmapDescriptor = bitmapDescriptor;
        this.refWidth = f2;
    }

    @Override // com.google.android.gms.maps.model.Cap
    public final String toString() {
        String strValueOf = String.valueOf(this.bitmapDescriptor);
        float f2 = this.refWidth;
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 55);
        sb.append("[CustomCap: bitmapDescriptor=");
        sb.append(strValueOf);
        sb.append(" refWidth=");
        sb.append(f2);
        sb.append("]");
        return sb.toString();
    }
}