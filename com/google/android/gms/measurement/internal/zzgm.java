package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgm implements Runnable {
    private final /* synthetic */ zzv zza;
    private final /* synthetic */ zzgk zzb;

    zzgm(zzgk zzgkVar, zzv zzvVar) {
        this.zzb = zzgkVar;
        this.zza = zzvVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzo();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zzb(this.zza);
        } else {
            this.zzb.zza.zza(this.zza);
        }
    }
}