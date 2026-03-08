package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmx implements zzdb<zzmw> {
    private static zzmx zza = new zzmx();
    private final zzdb<zzmw> zzb;

    public static boolean zzb() {
        return ((zzmw) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzmw) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzmw) zza.zza()).zzc();
    }

    private zzmx(zzdb<zzmw> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzmx() {
        this(zzda.zza(new zzmz()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzmw zza() {
        return this.zzb.zza();
    }
}