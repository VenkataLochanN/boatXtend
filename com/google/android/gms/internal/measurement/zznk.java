package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zznk implements zzdb<zznn> {
    private static zznk zza = new zznk();
    private final zzdb<zznn> zzb;

    public static boolean zzb() {
        return ((zznn) zza.zza()).zza();
    }

    private zznk(zzdb<zznn> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zznk() {
        this(zzda.zza(new zznm()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zznn zza() {
        return this.zzb.zza();
    }
}