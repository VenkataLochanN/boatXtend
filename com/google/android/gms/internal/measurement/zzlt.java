package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlt implements zzdb<zzls> {
    private static zzlt zza = new zzlt();
    private final zzdb<zzls> zzb;

    public static boolean zzb() {
        return ((zzls) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzls) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzls) zza.zza()).zzc();
    }

    public static boolean zze() {
        return ((zzls) zza.zza()).zzd();
    }

    public static boolean zzf() {
        return ((zzls) zza.zza()).zze();
    }

    public static boolean zzg() {
        return ((zzls) zza.zza()).zzf();
    }

    private zzlt(zzdb<zzls> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzlt() {
        this(zzda.zza(new zzlv()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzls zza() {
        return this.zzb.zza();
    }
}