package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjb implements zziy {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Long> zzb;

    @Override // com.google.android.gms.internal.measurement.zziy
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.app_launch.event_ordering_fix", false);
        zzb = zzctVar.zza("measurement.id.app_launch.event_ordering_fix", 0L);
    }
}