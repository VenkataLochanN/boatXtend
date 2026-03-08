package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzh implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzn zza;
    private final /* synthetic */ zzan zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzh(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzn zznVar, zzan zzanVar, String str) {
        this.zzd = appMeasurementDynamiteService;
        this.zza = zznVar;
        this.zzb = zzanVar;
        this.zzc = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzd.zza.zzw().zza(this.zza, this.zzb, this.zzc);
    }
}