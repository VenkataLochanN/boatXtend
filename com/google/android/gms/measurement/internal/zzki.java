package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzki extends zzaf {
    private final /* synthetic */ zzkj zza;
    private final /* synthetic */ zzkf zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzki(zzkf zzkfVar, zzhc zzhcVar, zzkj zzkjVar) {
        super(zzhcVar);
        this.zzb = zzkfVar;
        this.zza = zzkjVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzaf
    public final void zza() {
        this.zzb.zzf();
        this.zzb.zzr().zzx().zza("Starting upload from DelayedRunnable");
        this.zza.zzl();
    }
}