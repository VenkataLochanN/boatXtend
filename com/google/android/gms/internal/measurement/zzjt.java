package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjt implements zzjq {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;

    @Override // com.google.android.gms.internal.measurement.zzjq
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzjq
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.gold.enhanced_ecommerce.format_logs", false);
        zzb = zzctVar.zza("measurement.gold.enhanced_ecommerce.nested_complex_events", false);
    }
}