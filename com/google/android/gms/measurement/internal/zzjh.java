package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjh implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzm zzd;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzn zze;
    private final /* synthetic */ zzis zzf;

    zzjh(zzis zzisVar, String str, String str2, boolean z, zzm zzmVar, com.google.android.gms.internal.measurement.zzn zznVar) {
        this.zzf = zzisVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = zzmVar;
        this.zze = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle = new Bundle();
        try {
            zzet zzetVar = this.zzf.zzb;
            if (zzetVar == null) {
                this.zzf.zzr().zzf().zza("Failed to get user properties; not connected to service", this.zza, this.zzb);
                return;
            }
            Bundle bundleZza = zzkv.zza(zzetVar.zza(this.zza, this.zzb, this.zzc, this.zzd));
            this.zzf.zzaj();
            this.zzf.zzp().zza(this.zze, bundleZza);
        } catch (RemoteException e2) {
            this.zzf.zzr().zzf().zza("Failed to get user properties; remote exception", this.zza, e2);
        } finally {
            this.zzf.zzp().zza(this.zze, bundle);
        }
    }
}