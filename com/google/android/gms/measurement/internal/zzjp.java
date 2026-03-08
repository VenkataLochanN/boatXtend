package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzjp implements Runnable {
    private final zzjq zza;
    private final int zzb;
    private final zzfb zzc;
    private final Intent zzd;

    zzjp(zzjq zzjqVar, int i, zzfb zzfbVar, Intent intent) {
        this.zza = zzjqVar;
        this.zzb = i;
        this.zzc = zzfbVar;
        this.zzd = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}