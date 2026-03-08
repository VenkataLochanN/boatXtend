package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgp implements Runnable {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ zzgk zzb;

    zzgp(zzgk zzgkVar, zzm zzmVar) {
        this.zzb = zzgkVar;
        this.zza = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzo();
        this.zzb.zza.zza(this.zza);
    }
}