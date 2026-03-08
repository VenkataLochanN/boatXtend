package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjw implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzjt zzb;

    zzjw(zzjt zzjtVar, long j) {
        this.zzb = zzjtVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}