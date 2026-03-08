package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zznf implements zznc {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.integration.disable_firebase_instance_id", false);

    @Override // com.google.android.gms.internal.measurement.zznc
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }
}