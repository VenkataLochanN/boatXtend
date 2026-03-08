package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zziw implements Runnable {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ zzis zzb;

    zziw(zzis zzisVar, zzm zzmVar) {
        this.zzb = zzisVar;
        this.zza = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar = this.zzb.zzb;
        if (zzetVar == null) {
            this.zzb.zzr().zzf().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            zzetVar.zzd(this.zza);
        } catch (RemoteException e2) {
            this.zzb.zzr().zzf().zza("Failed to reset data on the service: remote exception", e2);
        }
        this.zzb.zzaj();
    }
}