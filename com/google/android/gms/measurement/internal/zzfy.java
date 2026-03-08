package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfy implements Runnable {
    private final /* synthetic */ zzgf zza;
    private final /* synthetic */ zzfb zzb;

    zzfy(zzfv zzfvVar, zzgf zzgfVar, zzfb zzfbVar) {
        this.zza = zzgfVar;
        this.zzb = zzfbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzf() == null) {
            this.zzb.zzf().zza("Install Referrer Reporter is null");
        } else {
            this.zza.zzf().zza();
        }
    }
}