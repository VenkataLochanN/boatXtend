package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzk implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzn zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ AppMeasurementDynamiteService zze;

    zzk(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzn zznVar, String str, String str2, boolean z) {
        this.zze = appMeasurementDynamiteService;
        this.zza = zznVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza.zzw().zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}