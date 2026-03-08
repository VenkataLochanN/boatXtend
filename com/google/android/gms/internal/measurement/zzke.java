package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzke implements zzdb<zzkh> {
    private static zzke zza = new zzke();
    private final zzdb<zzkh> zzb;

    public static boolean zzb() {
        return ((zzkh) zza.zza()).zza();
    }

    private zzke(zzdb<zzkh> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzke() {
        this(zzda.zza(new zzkg()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkh zza() {
        return this.zzb.zza();
    }
}