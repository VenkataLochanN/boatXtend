package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgl implements Callable<List<zzks>> {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzgk zzd;

    zzgl(zzgk zzgkVar, zzm zzmVar, String str, String str2) {
        this.zzd = zzgkVar;
        this.zza = zzmVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzks> call() throws Exception {
        this.zzd.zza.zzo();
        return this.zzd.zza.zze().zza(this.zza.zza, this.zzb, this.zzc);
    }
}