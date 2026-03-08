package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmt implements zzmq {
    private static final zzcn<Boolean> zza = new zzct(zzck.zza("com.google.android.gms.measurement")).zza("measurement.scheduler.task_thread.cleanup_on_exit", false);

    @Override // com.google.android.gms.internal.measurement.zzmq
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}