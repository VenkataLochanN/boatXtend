package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhq implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhk zzb;

    zzhq(zzhk zzhkVar, AtomicReference atomicReference) {
        this.zzb = zzhkVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzh().zza(this.zza);
    }
}