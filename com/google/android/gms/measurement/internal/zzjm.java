package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjm implements Runnable {
    private final /* synthetic */ ComponentName zza;
    private final /* synthetic */ zzjk zzb;

    zzjm(zzjk zzjkVar, ComponentName componentName) {
        this.zzb = zzjkVar;
        this.zza = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zza(this.zza);
    }
}