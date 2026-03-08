package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkv implements zzdb<zzku> {
    private static zzkv zza = new zzkv();
    private final zzdb<zzku> zzb;

    public static boolean zzb() {
        return ((zzku) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzku) zza.zza()).zzb();
    }

    private zzkv(zzdb<zzku> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzkv() {
        this(zzda.zza(new zzkx()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzku zza() {
        return this.zzb.zza();
    }
}