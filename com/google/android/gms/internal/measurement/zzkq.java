package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkq implements zzdb<zzkt> {
    private static zzkq zza = new zzkq();
    private final zzdb<zzkt> zzb;

    public static boolean zzb() {
        return ((zzkt) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkt) zza.zza()).zzb();
    }

    private zzkq(zzdb<zzkt> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzkq() {
        this(zzda.zza(new zzks()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkt zza() {
        return this.zzb.zza();
    }
}