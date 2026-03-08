package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzna implements zznb {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;
    private static final zzcn<Boolean> zzd;

    @Override // com.google.android.gms.internal.measurement.zznb
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final boolean zzb() {
        return zzc.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final boolean zzc() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.client.sessions.background_sessions_enabled", true);
        zzb = zzctVar.zza("measurement.client.sessions.immediate_start_enabled_foreground", true);
        zzc = zzctVar.zza("measurement.client.sessions.remove_expired_session_properties_enabled", true);
        zzd = zzctVar.zza("measurement.client.sessions.session_id_enabled", true);
    }
}