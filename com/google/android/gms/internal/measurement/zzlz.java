package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlz implements zzdb<zzly> {
    private static zzlz zza = new zzlz();
    private final zzdb<zzly> zzb;

    public static boolean zzb() {
        return ((zzly) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzly) zza.zza()).zzb();
    }

    private zzlz(zzdb<zzly> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzlz() {
        this(zzda.zza(new zzmb()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzly zza() {
        return this.zzb.zza();
    }
}