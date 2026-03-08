package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhz implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzhk zzb;

    zzhz(zzhk zzhkVar, boolean z) {
        this.zzb = zzhkVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzd(this.zza);
    }
}