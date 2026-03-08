package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgh implements Runnable {
    private final /* synthetic */ zzhh zza;
    private final /* synthetic */ zzgf zzb;

    zzgh(zzgf zzgfVar, zzhh zzhhVar) {
        this.zzb = zzgfVar;
        this.zza = zzhhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}