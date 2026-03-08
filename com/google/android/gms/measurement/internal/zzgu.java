package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgu implements Callable<byte[]> {
    private final /* synthetic */ zzan zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzgk zzc;

    zzgu(zzgk zzgkVar, zzan zzanVar, String str) {
        this.zzc = zzgkVar;
        this.zza = zzanVar;
        this.zzb = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() throws Exception {
        this.zzc.zza.zzo();
        return this.zzc.zza.zzg().zza(this.zza, this.zzb);
    }
}