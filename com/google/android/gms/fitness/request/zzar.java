package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* JADX INFO: loaded from: classes.dex */
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    private final zzcq zzgj;
    private final PendingIntent zzhg;
    private final com.google.android.gms.fitness.data.zzt zzhp;

    zzar(IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2) {
        this.zzhp = iBinder == null ? null : com.google.android.gms.fitness.data.zzu.zza(iBinder);
        this.zzhg = pendingIntent;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public zzar(com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent, zzcq zzcqVar) {
        this.zzhp = zztVar;
        this.zzhg = pendingIntent;
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return String.format("SensorUnregistrationRequest{%s}", this.zzhp);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        com.google.android.gms.fitness.data.zzt zztVar = this.zzhp;
        SafeParcelWriter.writeIBinder(parcel, 1, zztVar == null ? null : zztVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhg, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcqVar != null ? zzcqVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}