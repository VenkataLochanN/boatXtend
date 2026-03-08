package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzml implements zzdb<zzmk> {
    private static zzml zza = new zzml();
    private final zzdb<zzmk> zzb;

    public static boolean zzb() {
        return ((zzmk) zza.zza()).zza();
    }

    private zzml(zzdb<zzmk> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzml() {
        this(zzda.zza(new zzmn()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzmk zza() {
        return this.zzb.zza();
    }
}