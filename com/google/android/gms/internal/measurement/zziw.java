package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zziw implements zzix {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.module.collection.conditionally_omit_admob_app_id", true);

    @Override // com.google.android.gms.internal.measurement.zzix
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}