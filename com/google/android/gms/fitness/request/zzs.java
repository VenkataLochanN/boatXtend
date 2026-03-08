package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

/* JADX INFO: loaded from: classes.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    private final String name;
    private final com.google.android.gms.internal.fitness.zzbn zzhf;

    zzs(String str, IBinder iBinder) {
        this.name = str;
        this.zzhf = com.google.android.gms.internal.fitness.zzbo.zze(iBinder);
    }

    public zzs(String str, com.google.android.gms.internal.fitness.zzbn zzbnVar) {
        this.name = str;
        this.zzhf = zzbnVar;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzs) && Objects.equal(this.name, ((zzs) obj).name);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhf.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}