package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlj implements zzlg {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final boolean zzb() {
        return zzb.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.collection.efficient_engagement_reporting_enabled", false);
        zzb = zzctVar.zza("measurement.collection.redundant_engagement_removal_enabled", false);
    }
}