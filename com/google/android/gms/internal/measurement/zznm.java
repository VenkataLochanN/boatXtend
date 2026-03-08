package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zznm implements zznn {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.upload.file_lock_state_check", false);

    @Override // com.google.android.gms.internal.measurement.zznn
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}