package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zziy implements Runnable {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzn zzb;
    private final /* synthetic */ zzis zzc;

    zziy(zzis zzisVar, zzm zzmVar, com.google.android.gms.internal.measurement.zzn zznVar) {
        this.zzc = zzisVar;
        this.zza = zzmVar;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            zzet zzetVar = this.zzc.zzb;
            if (zzetVar == null) {
                this.zzc.zzr().zzf().zza("Failed to get app instance id");
                return;
            }
            String strZzc = zzetVar.zzc(this.zza);
            if (strZzc != null) {
                this.zzc.zzf().zza(strZzc);
                this.zzc.zzs().zzj.zza(strZzc);
            }
            this.zzc.zzaj();
            this.zzc.zzp().zza(this.zzb, strZzc);
        } catch (RemoteException e2) {
            this.zzc.zzr().zzf().zza("Failed to get app instance id", e2);
        } finally {
            this.zzc.zzp().zza(this.zzb, (String) null);
        }
    }
}