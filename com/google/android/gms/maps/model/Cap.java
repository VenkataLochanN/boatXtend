package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public class Cap extends AbstractSafeParcelable {
    private final BitmapDescriptor bitmapDescriptor;
    private final int type;
    private final Float zzcm;
    private static final String TAG = Cap.class.getSimpleName();
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();

    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    Cap(int i, IBinder iBinder, Float f2) {
        this(i, iBinder == null ? null : new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder)), f2);
    }

    private Cap(int i, BitmapDescriptor bitmapDescriptor, Float f2) {
        Preconditions.checkArgument(i != 3 || (bitmapDescriptor != null && (f2 != null && (f2.floatValue() > 0.0f ? 1 : (f2.floatValue() == 0.0f ? 0 : -1)) > 0)), String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", Integer.valueOf(i), bitmapDescriptor, f2));
        this.type = i;
        this.bitmapDescriptor = bitmapDescriptor;
        this.zzcm = f2;
    }

    protected Cap(BitmapDescriptor bitmapDescriptor, float f2) {
        this(3, bitmapDescriptor, Float.valueOf(f2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && Objects.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && Objects.equal(this.zzcm, cap.zzcm);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcm);
    }

    public String toString() {
        int i = this.type;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        BitmapDescriptor bitmapDescriptor = this.bitmapDescriptor;
        SafeParcelWriter.writeIBinder(parcel, 3, bitmapDescriptor == null ? null : bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzcm, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final Cap zzg() {
        int i = this.type;
        if (i == 0) {
            return new ButtCap();
        }
        if (i == 1) {
            return new SquareCap();
        }
        if (i == 2) {
            return new RoundCap();
        }
        if (i == 3) {
            return new CustomCap(this.bitmapDescriptor, this.zzcm.floatValue());
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder(29);
        sb.append("Unknown Cap type: ");
        sb.append(i);
        Log.w(str, sb.toString());
        return this;
    }
}