package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzlc;
import com.ido.life.util.DateUtil;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkb {
    private long zza;
    private long zzb;
    private final zzaf zzc;
    private final /* synthetic */ zzjt zzd;

    public zzkb(zzjt zzjtVar) {
        this.zzd = zzjtVar;
        this.zzc = new zzke(this, this.zzd.zzx);
        this.zza = zzjtVar.zzm().elapsedRealtime();
        this.zzb = this.zza;
    }

    final void zza(long j) {
        this.zzd.zzd();
        this.zzc.zzc();
        this.zza = j;
        this.zzb = this.zza;
    }

    final void zzb(long j) {
        this.zzc.zzc();
        if (this.zza != 0) {
            this.zzd.zzs().zzr.zza(this.zzd.zzs().zzr.zza() + (j - this.zza));
        }
    }

    final void zza() {
        this.zzc.zzc();
        this.zza = 0L;
        this.zzb = this.zza;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc() {
        this.zzd.zzd();
        zza(false, false, this.zzd.zzm().elapsedRealtime());
        this.zzd.zze().zza(this.zzd.zzm().elapsedRealtime());
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        this.zzd.zzd();
        this.zzd.zzw();
        if (!com.google.android.gms.internal.measurement.zzkk.zzb() || !this.zzd.zzt().zza(zzap.zzcu)) {
            j = this.zzd.zzm().elapsedRealtime();
        }
        if (!zzlc.zzb() || !this.zzd.zzt().zza(zzap.zzcp) || this.zzd.zzx.zzab()) {
            this.zzd.zzs().zzq.zza(this.zzd.zzm().currentTimeMillis());
        }
        long j2 = j - this.zza;
        if (!z && j2 < 1000) {
            this.zzd.zzr().zzx().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
            return false;
        }
        this.zzd.zzs().zzr.zza(j2);
        this.zzd.zzr().zzx().zza("Recording user engagement, ms", Long.valueOf(j2));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", j2);
        zzin.zza(this.zzd.zzi().zzab(), bundle, true);
        if (this.zzd.zzt().zze(this.zzd.zzg().zzab(), zzap.zzax)) {
            if (this.zzd.zzt().zza(zzap.zzay)) {
                if (!z2) {
                    zzb();
                }
            } else if (z2) {
                bundle.putLong("_fr", 1L);
            } else {
                zzb();
            }
        }
        if (!this.zzd.zzt().zza(zzap.zzay) || !z2) {
            this.zzd.zzf().zza("auto", "_e", bundle);
        }
        this.zza = j;
        this.zzc.zzc();
        this.zzc.zza(Math.max(0L, DateUtil.HOUR - this.zzd.zzs().zzr.zza()));
        return true;
    }

    final long zzb() {
        long jElapsedRealtime = this.zzd.zzm().elapsedRealtime();
        long j = jElapsedRealtime - this.zzb;
        this.zzb = jElapsedRealtime;
        return j;
    }
}