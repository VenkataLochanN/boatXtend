package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmr implements zzdb<zzmq> {
    private static zzmr zza = new zzmr();
    private final zzdb<zzmq> zzb;

    public static boolean zzb() {
        return ((zzmq) zza.zza()).zza();
    }

    private zzmr(zzdb<zzmq> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzmr() {
        this(zzda.zza(new zzmt()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzmq zza() {
        return this.zzb.zza();
    }
}