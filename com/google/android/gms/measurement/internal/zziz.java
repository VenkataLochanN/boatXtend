package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zziz implements Runnable {
    private final /* synthetic */ zzan zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzn zzc;
    private final /* synthetic */ zzis zzd;

    zziz(zzis zzisVar, zzan zzanVar, String str, com.google.android.gms.internal.measurement.zzn zznVar) {
        this.zzd = zzisVar;
        this.zza = zzanVar;
        this.zzb = str;
        this.zzc = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            zzet zzetVar = this.zzd.zzb;
            if (zzetVar == null) {
                this.zzd.zzr().zzf().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            byte[] bArrZza = zzetVar.zza(this.zza, this.zzb);
            this.zzd.zzaj();
            this.zzd.zzp().zza(this.zzc, bArrZza);
        } catch (RemoteException e2) {
            this.zzd.zzr().zzf().zza("Failed to send event to the service to bundle", e2);
        } finally {
            this.zzd.zzp().zza(this.zzc, (byte[]) null);
        }
    }
}