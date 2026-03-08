package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjl implements zzdb<zzjk> {
    private static zzjl zza = new zzjl();
    private final zzdb<zzjk> zzb;

    public static boolean zzb() {
        return ((zzjk) zza.zza()).zza();
    }

    private zzjl(zzdb<zzjk> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzjl() {
        this(zzda.zza(new zzjn()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjk zza() {
        return this.zzb.zza();
    }
}