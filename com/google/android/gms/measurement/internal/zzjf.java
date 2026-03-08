package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjf implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzm zzc;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzn zzd;
    private final /* synthetic */ zzis zze;

    zzjf(zzis zzisVar, String str, String str2, zzm zzmVar, com.google.android.gms.internal.measurement.zzn zznVar) {
        this.zze = zzisVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzmVar;
        this.zzd = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            zzet zzetVar = this.zze.zzb;
            if (zzetVar == null) {
                this.zze.zzr().zzf().zza("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                return;
            }
            ArrayList<Bundle> arrayListZzb = zzkv.zzb(zzetVar.zza(this.zza, this.zzb, this.zzc));
            this.zze.zzaj();
            this.zze.zzp().zza(this.zzd, arrayListZzb);
        } catch (RemoteException e2) {
            this.zze.zzr().zzf().zza("Failed to get conditional properties; remote exception", this.zza, this.zzb, e2);
        } finally {
            this.zze.zzp().zza(this.zzd, arrayList);
        }
    }
}