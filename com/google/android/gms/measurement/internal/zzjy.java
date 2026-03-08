package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzlb;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjy {
    final /* synthetic */ zzjt zza;
    private zzjz zzb;
    private final Runnable zzc = new Runnable(this) { // from class: com.google.android.gms.measurement.internal.zzjx
        private final zzjy zza;

        {
            this.zza = this;
        }

        @Override // java.lang.Runnable
        public final void run() {
            final zzjy zzjyVar = this.zza;
            zzjyVar.zza.zzq().zza(new Runnable(zzjyVar) { // from class: com.google.android.gms.measurement.internal.zzka
                private final zzjy zza;

                {
                    this.zza = zzjyVar;
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
    };

    zzjy(zzjt zzjtVar) {
        this.zza = zzjtVar;
    }

    final void zza() {
        this.zza.zzd();
        if (this.zza.zzt().zza(zzap.zzcg)) {
            if (zzlb.zzb() && this.zza.zzt().zze(this.zza.zzg().zzab(), zzap.zzct)) {
                if (this.zzb != null) {
                    this.zza.zzc.removeCallbacks(this.zzb);
                    return;
                }
                return;
            }
            this.zza.zzc.removeCallbacks(this.zzc);
        }
    }

    final void zzb() {
        if (this.zza.zzt().zza(zzap.zzcg)) {
            if (zzlb.zzb() && this.zza.zzt().zze(this.zza.zzg().zzab(), zzap.zzct)) {
                this.zzb = new zzjz(this, this.zza.zzm().currentTimeMillis());
                this.zza.zzc.postDelayed(this.zzb, 2000L);
            } else {
                this.zza.zzc.postDelayed(this.zzc, 2000L);
            }
        }
    }
}