package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjn implements zzjk {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.upload.disable_is_uploader", true);

    @Override // com.google.android.gms.internal.measurement.zzjk
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}