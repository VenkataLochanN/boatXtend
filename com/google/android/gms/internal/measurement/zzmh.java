package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmh implements zzme {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.config.string.always_update_disk_on_set", false);

    @Override // com.google.android.gms.internal.measurement.zzme
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}