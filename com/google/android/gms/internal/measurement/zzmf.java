package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmf implements zzdb<zzme> {
    private static zzmf zza = new zzmf();
    private final zzdb<zzme> zzb;

    public static boolean zzb() {
        return ((zzme) zza.zza()).zza();
    }

    private zzmf(zzdb<zzme> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzmf() {
        this(zzda.zza(new zzmh()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzme zza() {
        return this.zzb.zza();
    }
}