package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzjt extends zze {
    protected zzkd zza;
    protected zzkb zzb;
    private Handler zzc;
    private zzjy zzd;

    zzjt(zzgf zzgfVar) {
        super(zzgfVar);
        this.zza = new zzkd(this);
        this.zzb = new zzkb(this);
        this.zzd = new zzjy(this);
    }

    @Override // com.google.android.gms.measurement.internal.zze
    protected final boolean zzz() {
        return false;
    }

    protected final void zzab() {
        zzq().zza(new zzjw(this, zzm().elapsedRealtime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzac() {
        zzd();
        if (this.zzc == null) {
            this.zzc = new com.google.android.gms.internal.measurement.zzj(Looper.getMainLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(long j) {
        zzd();
        zzac();
        zzr().zzx().zza("Activity resumed, time", Long.valueOf(j));
        this.zzd.zza();
        this.zzb.zza(j);
        zzkd zzkdVar = this.zza;
        zzkdVar.zza.zzd();
        if (zzkdVar.zza.zzx.zzab()) {
            if (zzkdVar.zza.zzt().zza(zzap.zzar)) {
                zzkdVar.zza.zzs().zzt.zza(false);
            }
            zzkdVar.zza(zzkdVar.zza.zzm().currentTimeMillis(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(long j) {
        zzd();
        zzac();
        zzr().zzx().zza("Activity paused, time", Long.valueOf(j));
        this.zzd.zzb();
        this.zzb.zzb(j);
        zzkd zzkdVar = this.zza;
        if (zzkdVar.zza.zzt().zza(zzap.zzar)) {
            zzkdVar.zza.zzs().zzt.zza(true);
        }
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        return this.zzb.zza(z, z2, j);
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzb zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzhk zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzey zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzis zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzin zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzex zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzjt zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzah zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzez zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzkv zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzgc zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzfb zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzfo zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzw zzu() {
        return super.zzu();
    }
}