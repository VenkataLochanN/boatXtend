package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlv implements zzls {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;
    private static final zzcn<Boolean> zzd;
    private static final zzcn<Boolean> zze;
    private static final zzcn<Long> zzf;

    @Override // com.google.android.gms.internal.measurement.zzls
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzls
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzls
    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzls
    public final boolean zzd() {
        return zzc.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzls
    public final boolean zze() {
        return zzd.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzls
    public final boolean zzf() {
        return zze.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzb = zzctVar.zza("measurement.sdk.collection.last_deep_link_referrer2", false);
        zzc = zzctVar.zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzd = zzctVar.zza("measurement.sdk.collection.last_gclid_from_referrer2", false);
        zze = zzctVar.zza("measurement.sdk.collection.worker_thread_referrer", true);
        zzf = zzctVar.zza("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }
}