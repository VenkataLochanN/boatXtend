package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlw implements zzlx {
    private static final zzcn<Long> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;
    private static final zzcn<Boolean> zzd;
    private static final zzcn<Long> zze;

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final boolean zza() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final boolean zzb() {
        return zzc.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final boolean zzc() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.id.lifecycle.app_in_background_parameter", 0L);
        zzb = zzctVar.zza("measurement.lifecycle.app_backgrounded_engagement", false);
        zzc = zzctVar.zza("measurement.lifecycle.app_backgrounded_tracking", true);
        zzd = zzctVar.zza("measurement.lifecycle.app_in_background_parameter", false);
        zze = zzctVar.zza("measurement.id.lifecycle.app_backgrounded_tracking", 0L);
    }
}