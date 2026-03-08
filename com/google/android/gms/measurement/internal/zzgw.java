package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgw implements Callable<List<zzks>> {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ zzgk zzb;

    zzgw(zzgk zzgkVar, zzm zzmVar) {
        this.zzb = zzgkVar;
        this.zza = zzmVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzks> call() throws Exception {
        this.zzb.zza.zzo();
        return this.zzb.zza.zze().zza(this.zza.zza);
    }
}