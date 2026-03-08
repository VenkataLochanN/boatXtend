package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmo implements zzmp {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.experiment.enable_experiment_reporting", true);

    @Override // com.google.android.gms.internal.measurement.zzmp
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}