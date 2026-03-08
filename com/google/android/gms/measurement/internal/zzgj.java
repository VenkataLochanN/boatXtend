package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgj implements Runnable {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ zzgk zzb;

    zzgj(zzgk zzgkVar, zzm zzmVar) {
        this.zzb = zzgkVar;
        this.zza = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzo();
        zzkj zzkjVar = this.zzb.zza;
        zzm zzmVar = this.zza;
        zzkjVar.zzq().zzd();
        zzkjVar.zzk();
        Preconditions.checkNotEmpty(zzmVar.zza);
        zzkjVar.zzc(zzmVar);
    }
}