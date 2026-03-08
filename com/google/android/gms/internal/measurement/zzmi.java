package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmi implements zzmj {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Double> zzb;
    private static final zzcn<Long> zzc;
    private static final zzcn<Long> zzd;
    private static final zzcn<String> zze;

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final double zzb() {
        return zzb.zzc().doubleValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final long zzc() {
        return zzc.zzc().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final long zzd() {
        return zzd.zzc().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final String zze() {
        return zze.zzc();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.test.boolean_flag", false);
        zzb = zzctVar.zza("measurement.test.double_flag", -3.0d);
        zzc = zzctVar.zza("measurement.test.int_flag", -2L);
        zzd = zzctVar.zza("measurement.test.long_flag", -1L);
        zze = zzctVar.zza("measurement.test.string_flag", "---");
    }
}