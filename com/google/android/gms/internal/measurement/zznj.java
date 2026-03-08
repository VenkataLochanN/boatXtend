package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zznj implements zzdb<zzni> {
    private static zznj zza = new zznj();
    private final zzdb<zzni> zzb;

    public static boolean zzb() {
        return ((zzni) zza.zza()).zza();
    }

    private zznj(zzdb<zzni> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zznj() {
        this(zzda.zza(new zznl()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzni zza() {
        return this.zzb.zza();
    }
}