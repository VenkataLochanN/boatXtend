package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzje implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzan zzc;
    private final /* synthetic */ zzm zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zzis zzf;

    zzje(zzis zzisVar, boolean z, boolean z2, zzan zzanVar, zzm zzmVar, String str) {
        this.zzf = zzisVar;
        this.zza = z;
        this.zzb = z2;
        this.zzc = zzanVar;
        this.zzd = zzmVar;
        this.zze = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar = this.zzf.zzb;
        if (zzetVar == null) {
            this.zzf.zzr().zzf().zza("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zza) {
            this.zzf.zza(zzetVar, this.zzb ? null : this.zzc, this.zzd);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze)) {
                    zzetVar.zza(this.zzc, this.zzd);
                } else {
                    zzetVar.zza(this.zzc, this.zze, this.zzf.zzr().zzy());
                }
            } catch (RemoteException e2) {
                this.zzf.zzr().zzf().zza("Failed to send event to the service", e2);
            }
        }
        this.zzf.zzaj();
    }
}