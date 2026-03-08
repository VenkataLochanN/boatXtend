package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zziq implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzio zzc;
    private final /* synthetic */ zzio zzd;
    private final /* synthetic */ zzin zze;

    zziq(zzin zzinVar, boolean z, long j, zzio zzioVar, zzio zzioVar2) {
        this.zze = zzinVar;
        this.zza = z;
        this.zzb = j;
        this.zzc = zzioVar;
        this.zzd = zzioVar2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        if (this.zze.zzt().zza(zzap.zzax)) {
            z = this.zza && this.zze.zza != null;
            if (z) {
                zzin zzinVar = this.zze;
                zzinVar.zza(zzinVar.zza, true, this.zzb);
            }
        } else {
            if (this.zza && this.zze.zza != null) {
                zzin zzinVar2 = this.zze;
                zzinVar2.zza(zzinVar2.zza, true, this.zzb);
            }
            z = false;
        }
        zzio zzioVar = this.zzc;
        if ((zzioVar != null && zzioVar.zzc == this.zzd.zzc && zzkv.zzc(this.zzc.zzb, this.zzd.zzb) && zzkv.zzc(this.zzc.zza, this.zzd.zza)) ? false : true) {
            Bundle bundle = new Bundle();
            zzin.zza(this.zzd, bundle, true);
            zzio zzioVar2 = this.zzc;
            if (zzioVar2 != null) {
                if (zzioVar2.zza != null) {
                    bundle.putString("_pn", this.zzc.zza);
                }
                bundle.putString("_pc", this.zzc.zzb);
                bundle.putLong("_pi", this.zzc.zzc);
            }
            if (this.zze.zzt().zza(zzap.zzax) && z) {
                long jZzb = this.zze.zzk().zzb.zzb();
                if (jZzb > 0) {
                    this.zze.zzp().zza(bundle, jZzb);
                }
            }
            this.zze.zzf().zzb("auto", "_vs", bundle);
        }
        zzin zzinVar3 = this.zze;
        zzinVar3.zza = this.zzd;
        zzinVar3.zzh().zza(this.zzd);
    }
}