package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
class zzha implements zzhc {
    protected final zzgf zzx;

    zzha(zzgf zzgfVar) {
        Preconditions.checkNotNull(zzgfVar);
        this.zzx = zzgfVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public zzw zzu() {
        return this.zzx.zzu();
    }

    public zzx zzt() {
        return this.zzx.zzb();
    }

    public zzfo zzs() {
        return this.zzx.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public zzfb zzr() {
        return this.zzx.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public zzgc zzq() {
        return this.zzx.zzq();
    }

    public zzkv zzp() {
        return this.zzx.zzi();
    }

    public zzez zzo() {
        return this.zzx.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public Context zzn() {
        return this.zzx.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public Clock zzm() {
        return this.zzx.zzm();
    }

    public zzah zzl() {
        return this.zzx.zzx();
    }

    public void zzd() {
        this.zzx.zzq().zzd();
    }

    public void zzc() {
        this.zzx.zzq().zzc();
    }

    public void zzb() {
        this.zzx.zzae();
    }

    public void zza() {
        this.zzx.zzaf();
    }
}