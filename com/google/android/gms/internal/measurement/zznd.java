package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zznd implements zzdb<zznc> {
    private static zznd zza = new zznd();
    private final zzdb<zznc> zzb;

    public static boolean zzb() {
        return ((zznc) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zznc) zza.zza()).zzb();
    }

    private zznd(zzdb<zznc> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zznd() {
        this(zzda.zza(new zznf()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zznc zza() {
        return this.zzb.zza();
    }
}