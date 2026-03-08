package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.alexa.util.Util;

/* JADX INFO: loaded from: classes.dex */
public final class zzbb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbb> CREATOR = new zzbc();
    private final String name;
    private final String zzdz;
    private final zzcn zzih;

    zzbb(String str, String str2, IBinder iBinder) {
        this.name = str;
        this.zzdz = str2;
        this.zzih = zzco.zzi(iBinder);
    }

    public zzbb(String str, String str2, zzcn zzcnVar) {
        this.name = str;
        this.zzdz = str2;
        this.zzih = zzcnVar;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof zzbb) {
                zzbb zzbbVar = (zzbb) obj;
                if (Objects.equal(this.name, zzbbVar.name) && Objects.equal(this.zzdz, zzbbVar.zzdz)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, this.zzdz);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).add(Util.IDENTIFIER, this.zzdz).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzdz, false);
        zzcn zzcnVar = this.zzih;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}