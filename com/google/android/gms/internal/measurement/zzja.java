package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzja implements zzdb<zzjd> {
    private static zzja zza = new zzja();
    private final zzdb<zzjd> zzb;

    public static boolean zzb() {
        return ((zzjd) zza.zza()).zza();
    }

    private zzja(zzdb<zzjd> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzja() {
        this(zzda.zza(new zzjc()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjd zza() {
        return this.zzb.zza();
    }
}