package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlu implements zzdb<zzlx> {
    private static zzlu zza = new zzlu();
    private final zzdb<zzlx> zzb;

    public static boolean zzb() {
        return ((zzlx) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzlx) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzlx) zza.zza()).zzc();
    }

    private zzlu(zzdb<zzlx> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzlu() {
        this(zzda.zza(new zzlw()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlx zza() {
        return this.zzb.zza();
    }
}