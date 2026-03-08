package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkm implements Runnable {
    private final /* synthetic */ zzkp zza;
    private final /* synthetic */ zzkj zzb;

    zzkm(zzkj zzkjVar, zzkp zzkpVar) {
        this.zzb = zzkjVar;
        this.zza = zzkpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}