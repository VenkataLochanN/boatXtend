package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkk implements zzdb<zzkn> {
    private static zzkk zza = new zzkk();
    private final zzdb<zzkn> zzb;

    public static boolean zzb() {
        return ((zzkn) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkn) zza.zza()).zzb();
    }

    private zzkk(zzdb<zzkn> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzkk() {
        this(zzda.zza(new zzkm()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkn zza() {
        return this.zzb.zza();
    }
}