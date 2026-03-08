package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzju implements zzjv {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.collection.event_safelist", true);

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}