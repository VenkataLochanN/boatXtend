package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkj implements zzdb<zzki> {
    private static zzkj zza = new zzkj();
    private final zzdb<zzki> zzb;

    public static boolean zzb() {
        return ((zzki) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzki) zza.zza()).zzb();
    }

    private zzkj(zzdb<zzki> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzkj() {
        this(zzda.zza(new zzkl()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzki zza() {
        return this.zzb.zza();
    }
}