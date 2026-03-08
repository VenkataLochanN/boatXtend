package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfl implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzfm zzb;

    zzfl(zzfm zzfmVar, boolean z) {
        this.zzb = zzfmVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb.zza(this.zza);
    }
}