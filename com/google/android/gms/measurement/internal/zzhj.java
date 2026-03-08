package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzhj implements Runnable {
    private final zzhk zza;

    zzhj(zzhk zzhkVar) {
        this.zza = zzhkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzhk zzhkVar = this.zza;
        zzhkVar.zzd();
        if (zzhkVar.zzs().zzu.zza()) {
            zzhkVar.zzr().zzw().zza("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long jZza = zzhkVar.zzs().zzv.zza();
        zzhkVar.zzs().zzv.zza(1 + jZza);
        if (jZza < 5) {
            zzhkVar.zzx.zzai();
        } else {
            zzhkVar.zzr().zzi().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            zzhkVar.zzs().zzu.zza(true);
        }
    }
}