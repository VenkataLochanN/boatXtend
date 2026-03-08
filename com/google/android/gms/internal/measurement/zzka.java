package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzka implements zzkb {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.client.firebase_feature_rollout.v1.enable", true);

    @Override // com.google.android.gms.internal.measurement.zzkb
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzkb
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }
}