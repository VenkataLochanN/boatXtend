package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkw implements zzdb<zzkz> {
    private static zzkw zza = new zzkw();
    private final zzdb<zzkz> zzb;

    public static boolean zzb() {
        return ((zzkz) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkz) zza.zza()).zzb();
    }

    private zzkw(zzdb<zzkz> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzkw() {
        this(zzda.zza(new zzky()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkz zza() {
        return this.zzb.zza();
    }
}