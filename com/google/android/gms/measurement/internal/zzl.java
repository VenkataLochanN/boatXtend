package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzl implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzn zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzl(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzn zznVar) {
        this.zzb = appMeasurementDynamiteService;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzi().zza(this.zza, this.zzb.zza.zzaa());
    }
}