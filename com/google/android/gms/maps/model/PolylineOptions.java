package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class PolylineOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();
    private int color;
    private float width;
    private float zzcr;
    private boolean zzcs;
    private boolean zzct;
    private final List<LatLng> zzdw;
    private boolean zzdy;
    private Cap zzeb;
    private Cap zzec;
    private int zzed;
    private List<PatternItem> zzee;

    public PolylineOptions() {
        this.width = 10.0f;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzeb = new ButtCap();
        this.zzec = new ButtCap();
        this.zzed = 0;
        this.zzee = null;
        this.zzdw = new ArrayList();
    }

    PolylineOptions(List list, float f2, int i, float f3, boolean z, boolean z2, boolean z3, Cap cap, Cap cap2, int i2, List<PatternItem> list2) {
        this.width = 10.0f;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.zzcr = 0.0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzeb = new ButtCap();
        this.zzec = new ButtCap();
        this.zzed = 0;
        this.zzee = null;
        this.zzdw = list;
        this.width = f2;
        this.color = i;
        this.zzcr = f3;
        this.zzcs = z;
        this.zzdy = z2;
        this.zzct = z3;
        if (cap != null) {
            this.zzeb = cap;
        }
        if (cap2 != null) {
            this.zzec = cap2;
        }
        this.zzed = i2;
        this.zzee = list2;
    }

    public final PolylineOptions add(LatLng latLng) {
        this.zzdw.add(latLng);
        return this;
    }

    public final PolylineOptions add(LatLng... latLngArr) {
        this.zzdw.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolylineOptions addAll(Iterable<LatLng> iterable) {
        Iterator<LatLng> it = iterable.iterator();
        while (it.hasNext()) {
            this.zzdw.add(it.next());
        }
        return this;
    }

    public final PolylineOptions clickable(boolean z) {
        this.zzct = z;
        return this;
    }

    public final PolylineOptions color(int i) {
        this.color = i;
        return this;
    }

    public final PolylineOptions endCap(Cap cap) {
        this.zzec = (Cap) Preconditions.checkNotNull(cap, "endCap must not be null");
        return this;
    }

    public final PolylineOptions geodesic(boolean z) {
        this.zzdy = z;
        return this;
    }

    public final int getColor() {
        return this.color;
    }

    public final Cap getEndCap() {
        return this.zzec;
    }

    public final int getJointType() {
        return this.zzed;
    }

    public final List<PatternItem> getPattern() {
        return this.zzee;
    }

    public final List<LatLng> getPoints() {
        return this.zzdw;
    }

    public final Cap getStartCap() {
        return this.zzeb;
    }

    public final float getWidth() {
        return this.width;
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

    public final PolylineOptions jointType(int i) {
        this.zzed = i;
        return this;
    }

    public final PolylineOptions pattern(List<PatternItem> list) {
        this.zzee = list;
        return this;
    }

    public final PolylineOptions startCap(Cap cap) {
        this.zzeb = (Cap) Preconditions.checkNotNull(cap, "startCap must not be null");
        return this;
    }

    public final PolylineOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    public final PolylineOptions width(float f2) {
        this.width = f2;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeFloat(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getColor());
        SafeParcelWriter.writeFloat(parcel, 5, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 6, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 7, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 8, isClickable());
        SafeParcelWriter.writeParcelable(parcel, 9, getStartCap(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, getEndCap(), i, false);
        SafeParcelWriter.writeInt(parcel, 11, getJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getPattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final PolylineOptions zIndex(float f2) {
        this.zzcr = f2;
        return this;
    }
}