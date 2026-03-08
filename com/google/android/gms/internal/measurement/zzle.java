package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzle implements zzlf {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;

    @Override // com.google.android.gms.internal.measurement.zzlf
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzlf
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlf
    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlf
    public final boolean zzd() {
        return zzc.zzc().booleanValue();
    }

    static {
        zzct zzctVar = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzctVar.zza("measurement.client.sessions.check_on_reset_and_enable", false);
        zzb = zzctVar.zza("measurement.client.sessions.check_on_startup", true);
        zzc = zzctVar.zza("measurement.client.sessions.start_session_before_view_screen", true);
    }
}