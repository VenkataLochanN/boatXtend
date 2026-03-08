package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgq implements Callable<List<zzv>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzgk zzd;

    zzgq(zzgk zzgkVar, String str, String str2, String str3) {
        this.zzd = zzgkVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzv> call() throws Exception {
        this.zzd.zza.zzo();
        return this.zzd.zza.zze().zzb(this.zza, this.zzb, this.zzc);
    }
}