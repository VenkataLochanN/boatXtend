package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfx implements Runnable {
    private final /* synthetic */ zzgf zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ Context zzd;
    private final /* synthetic */ zzfb zze;
    private final /* synthetic */ BroadcastReceiver.PendingResult zzf;

    zzfx(zzfv zzfvVar, zzgf zzgfVar, long j, Bundle bundle, Context context, zzfb zzfbVar, BroadcastReceiver.PendingResult pendingResult) {
        this.zza = zzgfVar;
        this.zzb = j;
        this.zzc = bundle;
        this.zzd = context;
        this.zze = zzfbVar;
        this.zzf = pendingResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long jZza = this.zza.zzc().zzh.zza();
        long j = this.zzb;
        if (jZza > 0 && (j >= jZza || j <= 0)) {
            j = jZza - 1;
        }
        if (j > 0) {
            this.zzc.putLong("click_timestamp", j);
        }
        this.zzc.putString("_cis", "referrer broadcast");
        zzgf.zza(this.zzd, (com.google.android.gms.internal.measurement.zzv) null).zzh().zza("auto", "_cmp", this.zzc);
        this.zze.zzx().zza("Install campaign recorded");
        BroadcastReceiver.PendingResult pendingResult = this.zzf;
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }
}