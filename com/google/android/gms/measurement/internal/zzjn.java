package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjn implements Runnable {
    private final /* synthetic */ zzjk zza;

    zzjn(zzjk zzjkVar) {
        this.zza = zzjkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzis.zza(this.zza.zza, (zzet) null);
        this.zza.zza.zzal();
    }
}