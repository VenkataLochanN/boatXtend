package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgx implements Runnable {
    private final /* synthetic */ zzv zza;
    private final /* synthetic */ zzm zzb;
    private final /* synthetic */ zzgk zzc;

    zzgx(zzgk zzgkVar, zzv zzvVar, zzm zzmVar) {
        this.zzc = zzgkVar;
        this.zza = zzvVar;
        this.zzb = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzo();
        if (this.zza.zzc.zza() == null) {
            this.zzc.zza.zzb(this.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}