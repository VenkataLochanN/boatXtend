package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjm implements zzdb<zzjp> {
    private static zzjm zza = new zzjm();
    private final zzdb<zzjp> zzb;

    public static boolean zzb() {
        return ((zzjp) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzjp) zza.zza()).zzb();
    }

    private zzjm(zzdb<zzjp> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzjm() {
        this(zzda.zza(new zzjo()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjp zza() {
        return this.zzb.zza();
    }
}