package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjb implements Runnable {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ zzis zzb;

    zzjb(zzis zzisVar, zzm zzmVar) {
        this.zzb = zzisVar;
        this.zza = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar = this.zzb.zzb;
        if (zzetVar == null) {
            this.zzb.zzr().zzf().zza("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzetVar.zzb(this.zza);
            this.zzb.zzaj();
        } catch (RemoteException e2) {
            this.zzb.zzr().zzf().zza("Failed to send measurementEnabled to the service", e2);
        }
    }
}