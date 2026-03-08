package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkn implements Callable<String> {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ zzkj zzb;

    zzkn(zzkj zzkjVar, zzm zzmVar) {
        this.zzb = zzkjVar;
        this.zza = zzmVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        zzg zzgVarZzc = this.zzb.zzc(this.zza);
        if (zzgVarZzc == null) {
            this.zzb.zzr().zzi().zza("App info was null when attempting to get app instance id");
            return null;
        }
        return zzgVarZzc.zzd();
    }
}