package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzlc;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhp implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzhk zzb;

    zzhp(zzhk zzhkVar, long j) {
        this.zzb = zzhkVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzhk zzhkVar = this.zzb;
        long j = this.zza;
        zzhkVar.zzd();
        zzhkVar.zzb();
        zzhkVar.zzw();
        zzhkVar.zzr().zzw().zza("Resetting analytics data (FE)");
        zzjt zzjtVarZzk = zzhkVar.zzk();
        zzjtVarZzk.zzd();
        zzjtVarZzk.zzb.zza();
        boolean zZzab = zzhkVar.zzx.zzab();
        zzfo zzfoVarZzs = zzhkVar.zzs();
        zzfoVarZzs.zzh.zza(j);
        if (!TextUtils.isEmpty(zzfoVarZzs.zzs().zzw.zza())) {
            zzfoVarZzs.zzw.zza(null);
        }
        if (zzlc.zzb() && zzfoVarZzs.zzt().zza(zzap.zzcp)) {
            zzfoVarZzs.zzq.zza(0L);
        }
        if (!zzfoVarZzs.zzt().zzg()) {
            zzfoVarZzs.zzc(!zZzab);
        }
        zzhkVar.zzh().zzad();
        if (zzlc.zzb() && zzhkVar.zzt().zza(zzap.zzcp)) {
            zzhkVar.zzk().zza.zza();
        }
        zzhkVar.zzb = !zZzab;
        this.zzb.zzh().zza(new AtomicReference<>());
    }
}