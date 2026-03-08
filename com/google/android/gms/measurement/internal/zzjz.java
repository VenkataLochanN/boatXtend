package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjz implements Runnable {
    long zza;
    final /* synthetic */ zzjy zzb;

    zzjz(zzjy zzjyVar, long j) {
        this.zzb = zzjyVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzq().zza(new Runnable(this) { // from class: com.google.android.gms.measurement.internal.zzkc
            private final zzjz zza;

            {
                this.zza = this;
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
        });
    }
}