package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class PolygonOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzk();
    private int fillColor;
    private int strokeColor;
    private float zzcq;
    private float zzcr;
    private boolean zzcs;
    private boolean zzct;
    private List<PatternItem> zzcu;
    private final List<LatLng> zzdw;
    private final List<List<LatLng>> zzdx;
    private boolean zzdy;
    private int zzdz;

    public PolygonOptions() {
        this.zzcq = 10.0f;
        this.strokeColor = ViewCompat.MEASURED_STATE_MASK;
        this.fillColor = 0;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzdz = 0;
        this.zzcu = null;
        this.zzdw = new ArrayList();
        this.zzdx = new ArrayList();
    }

    PolygonOptions(List<LatLng> list, List list2, float f2, int i, int i2, float f3, boolean z, boolean z2, boolean z3, int i3, List<PatternItem> list3) {
        this.zzcq = 10.0f;
        this.strokeColor = ViewCompat.MEASURED_STATE_MASK;
        this.fillColor = 0;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzdz = 0;
        this.zzcu = null;
        this.zzdw = list;
        this.zzdx = list2;
        this.zzcq = f2;
        this.strokeColor = i;
        this.fillColor = i2;
        this.zzcr = f3;
        this.zzcs = z;
        this.zzdy = z2;
        this.zzct = z3;
        this.zzdz = i3;
        this.zzcu = list3;
    }

    public final PolygonOptions add(LatLng latLng) {
        this.zzdw.add(latLng);
        return this;
    }

    public final PolygonOptions add(LatLng... latLngArr) {
        this.zzdw.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolygonOptions addAll(Iterable<LatLng> iterable) {
        Iterator<LatLng> it = iterable.iterator();
        while (it.hasNext()) {
            this.zzdw.add(it.next());
        }
        return this;
    }

    public final PolygonOptions addHole(Iterable<LatLng> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        this.zzdx.add(arrayList);
        return this;
    }

    public final PolygonOptions clickable(boolean z) {
        this.zzct = z;
        return this;
    }

    public final PolygonOptions fillColor(int i) {
        this.fillColor = i;
        return this;
    }

    public final PolygonOptions geodesic(boolean z) {
        this.zzdy = z;
        return this;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final List<List<LatLng>> getHoles() {
        return this.zzdx;
    }

    public final List<LatLng> getPoints() {
        return this.zzdw;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final int getStrokeJointType() {
        return this.zzdz;
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

    public final boolean isGeodesic() {
        return this.zzdy;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final PolygonOptions strokeColor(int i) {
        this.strokeColor = i;
        return this;
    }

    public final PolygonOptions strokeJointType(int i) {
        this.zzdz = i;
        return this;
    }

    public final PolygonOptions strokePattern(List<PatternItem> list) {
        this.zzcu = list;
        return this;
    }

    public final PolygonOptions strokeWidth(float f2) {
        this.zzcq = f2;
        return this;
    }

    public final PolygonOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzdx, false);
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 10, isClickable());
        SafeParcelWriter.writeInt(parcel, 11, getStrokeJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final PolygonOptions zIndex(float f2) {
        this.zzcr = f2;
        return this;
    }
}