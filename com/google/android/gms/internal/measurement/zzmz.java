package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmz implements zzmw {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;

    @Override // com.google.android.gms.internal.measurement.zzmw
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmw
    public final boolean zzb() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmw
    public final boolean zzc() {
        return zzc.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.service.sessions.remove_disabled_session_number", true);
        zzb = zzctVar.zza("measurement.service.sessions.session_number_enabled", true);
        zzc = zzctVar.zza("measurement.service.sessions.session_number_backfill_enabled", true);
    }
}