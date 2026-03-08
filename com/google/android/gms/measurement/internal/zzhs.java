package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhs implements Runnable {
    private final /* synthetic */ zzhf zza;
    private final /* synthetic */ zzhk zzb;

    zzhs(zzhk zzhkVar, zzhf zzhfVar) {
        this.zzb = zzhkVar;
        this.zza = zzhfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}