package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzkc implements Runnable {
    private final zzjz zza;

    zzkc(zzjz zzjzVar) {
        this.zza = zzjzVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjz zzjzVar = this.zza;
        zzjy zzjyVar = zzjzVar.zzb;
        long j = zzjzVar.zza;
        zzjyVar.zza.zzd();
        zzjyVar.zza.zzr().zzw().zza("Application going to the background");
        zzjyVar.zza.zzf().zza("auto", "_ab", j, new Bundle());
    }
}