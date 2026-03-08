package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* JADX INFO: loaded from: classes.dex */
public final class zzbj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbj> CREATOR = new zzbk();
    private final zzcq zzgj;
    private Subscription zzik;
    private final boolean zzil;

    zzbj(Subscription subscription, boolean z, IBinder iBinder) {
        this.zzik = subscription;
        this.zzil = z;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbj(Subscription subscription, boolean z, zzcq zzcqVar) {
        this.zzik = subscription;
        this.zzil = false;
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("subscription", this.zzik).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzik, i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzil);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}