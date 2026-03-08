package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzja implements Runnable {
    private final /* synthetic */ zzio zza;
    private final /* synthetic */ zzis zzb;

    zzja(zzis zzisVar, zzio zzioVar) {
        this.zzb = zzisVar;
        this.zza = zzioVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar = this.zzb.zzb;
        if (zzetVar == null) {
            this.zzb.zzr().zzf().zza("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zza == null) {
                zzetVar.zza(0L, (String) null, (String) null, this.zzb.zzn().getPackageName());
            } else {
                zzetVar.zza(this.zza.zzc, this.zza.zza, this.zza.zzb, this.zzb.zzn().getPackageName());
            }
            this.zzb.zzaj();
        } catch (RemoteException e2) {
            this.zzb.zzr().zzf().zza("Failed to send current screen to the service", e2);
        }
    }
}