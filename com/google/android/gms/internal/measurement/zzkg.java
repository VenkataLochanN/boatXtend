package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkg implements zzkh {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.service.audience.invalidate_config_cache_after_app_unisntall", true);

    @Override // com.google.android.gms.internal.measurement.zzkh
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}