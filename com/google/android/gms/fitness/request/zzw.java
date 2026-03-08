package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* JADX INFO: loaded from: classes.dex */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();
    private final zzcq zzgj;
    private final PendingIntent zzhg;

    public zzw(PendingIntent pendingIntent, IBinder iBinder) {
        this.zzhg = pendingIntent;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzw) && Objects.equal(this.zzhg, ((zzw) obj).zzhg);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzhg);
    }

    public final String toString() {
        return "DataUpdateListenerUnregistrationRequest";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzhg, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}