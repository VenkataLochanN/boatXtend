package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzjx implements Runnable {
    private final zzjy zza;

    zzjx(zzjy zzjyVar) {
        this.zza = zzjyVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjy zzjyVar = this.zza;
        zzjyVar.zza.zzq().zza(new Runnable(zzjyVar) { // from class: com.google.android.gms.measurement.internal.zzka
            private final zzjy zza;

            zzka(zzjy zzjyVar2) {
                this.zza = zzjyVar2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzjy zzjyVar2 = this.zza;
                zzjyVar2.zza.zzd();
                zzjyVar2.zza.zzr().zzw().zza("Application backgrounded");
                zzjyVar2.zza.zzf().zzb("auto", "_ab", new Bundle());
            }
        });
    }
}