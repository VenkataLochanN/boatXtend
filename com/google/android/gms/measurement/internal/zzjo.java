package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjo implements Runnable {
    private final /* synthetic */ zzjk zza;

    zzjo(zzjk zzjkVar) {
        this.zza = zzjkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzis zzisVar = this.zza.zza;
        Context contextZzn = this.zza.zza.zzn();
        this.zza.zza.zzu();
        zzisVar.zza(new ComponentName(contextZzn, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}