package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class CircleOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
    private int fillColor;
    private int strokeColor;
    private LatLng zzco;
    private double zzcp;
    private float zzcq;
    private float zzcr;
    private boolean zzcs;
    private boolean zzct;
    private List<PatternItem> zzcu;

    public CircleOptions() {
        this.zzco = null;
        this.zzcp = 0.0d;
        this.zzcq = 10.0f;
        this.strokeColor = ViewCompat.MEASURED_STATE_MASK;
        this.fillColor = 0;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzct = false;
        this.zzcu = null;
    }

    CircleOptions(LatLng latLng, double d2, float f2, int i, int i2, float f3, boolean z, boolean z2, List<PatternItem> list) {
        this.zzco = null;
        this.zzcp = 0.0d;
        this.zzcq = 10.0f;
        this.strokeColor = ViewCompat.MEASURED_STATE_MASK;
        this.fillColor = 0;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzct = false;
        this.zzcu = null;
        this.zzco = latLng;
        this.zzcp = d2;
        this.zzcq = f2;
        this.strokeColor = i;
        this.fillColor = i2;
        this.zzcr = f3;
        this.zzcs = z;
        this.zzct = z2;
        this.zzcu = list;
    }

    public final CircleOptions center(LatLng latLng) {
        this.zzco = latLng;
        return this;
    }

    public final CircleOptions clickable(boolean z) {
        this.zzct = z;
        return this;
    }

    public final CircleOptions fillColor(int i) {
        this.fillColor = i;
        return this;
    }

    public final LatLng getCenter() {
        return this.zzco;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final double getRadius() {
        return this.zzcp;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final List<PatternItem> getStrokePattern() {
        return this.zzcu;
    }

    public final float getStrokeWidth() {
        return this.zzcq;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final boolean isClickable() {
        return this.zzct;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final CircleOptions radius(double d2) {
        this.zzcp = d2;
        return this;
    }

    public final CircleOptions strokeColor(int i) {
        this.strokeColor = i;
        return this;
    }

    public final CircleOptions strokePattern(List<PatternItem> list) {
        this.zzcu = list;
        return this;
    }

    public final CircleOptions strokeWidth(float f2) {
        this.zzcq = f2;
        return this;
    }

    public final CircleOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getCenter(), i, false);
        SafeParcelWriter.writeDouble(parcel, 3, getRadius());
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isClickable());
        SafeParcelWriter.writeTypedList(parcel, 10, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final CircleOptions zIndex(float f2) {
        this.zzcr = f2;
        return this;
    }
}