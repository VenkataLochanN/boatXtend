package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzjs implements Runnable {
    private final zzjq zza;
    private final zzfb zzb;
    private final JobParameters zzc;

    zzjs(zzjq zzjqVar, zzfb zzfbVar, JobParameters jobParameters) {
        this.zza = zzjqVar;
        this.zzb = zzfbVar;
        this.zzc = jobParameters;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}