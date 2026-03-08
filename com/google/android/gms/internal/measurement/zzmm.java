package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmm implements zzdb<zzmp> {
    private static zzmm zza = new zzmm();
    private final zzdb<zzmp> zzb;

    public static boolean zzb() {
        return ((zzmp) zza.zza()).zza();
    }

    private zzmm(zzdb<zzmp> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzmm() {
        this(zzda.zza(new zzmo()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzmp zza() {
        return this.zzb.zza();
    }
}