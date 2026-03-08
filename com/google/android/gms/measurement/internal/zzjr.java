package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjr implements Runnable {
    private final /* synthetic */ zzkj zza;
    private final /* synthetic */ Runnable zzb;

    zzjr(zzjq zzjqVar, zzkj zzkjVar, Runnable runnable) {
        this.zza = zzkjVar;
        this.zzb = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzo();
        this.zza.zza(this.zzb);
        this.zza.zzl();
    }
}