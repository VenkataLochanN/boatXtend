package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzks implements zzkt {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.service.use_appinfo_modified", false);

    @Override // com.google.android.gms.internal.measurement.zzkt
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzkt
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }
}