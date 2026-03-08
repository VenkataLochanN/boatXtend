package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkp implements zzdb<zzko> {
    private static zzkp zza = new zzkp();
    private final zzdb<zzko> zzb;

    public static boolean zzb() {
        return ((zzko) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzko) zza.zza()).zzb();
    }

    private zzkp(zzdb<zzko> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzkp() {
        this(zzda.zza(new zzkr()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzko zza() {
        return this.zzb.zza();
    }
}