package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjy implements zzdb<zzkb> {
    private static zzjy zza = new zzjy();
    private final zzdb<zzkb> zzb;

    public static boolean zzb() {
        return ((zzkb) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkb) zza.zza()).zzb();
    }

    private zzjy(zzdb<zzkb> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzjy() {
        this(zzda.zza(new zzka()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkb zza() {
        return this.zzb.zza();
    }
}