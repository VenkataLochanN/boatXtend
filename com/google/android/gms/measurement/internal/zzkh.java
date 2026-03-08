package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
class zzkh extends zzha implements zzhc {
    protected final zzkj zza;

    zzkh(zzkj zzkjVar) {
        super(zzkjVar.zzs());
        Preconditions.checkNotNull(zzkjVar);
        this.zza = zzkjVar;
    }

    public zzfz zzj() {
        return this.zza.zzc();
    }

    public zzac zzi() {
        return this.zza.zze();
    }

    public zzn e_() {
        return this.zza.zzf();
    }

    public zzkr zzg() {
        return this.zza.zzh();
    }
}