package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkm implements zzkn {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Long> zzb;

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.engagement_time_main_thread", false);
        zzb = zzctVar.zza("measurement.id.engagement_time_main_thread", 0L);
    }
}