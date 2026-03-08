package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzma implements zzdb<zzmd> {
    private static zzma zza = new zzma();
    private final zzdb<zzmd> zzb;

    public static boolean zzb() {
        return ((zzmd) zza.zza()).zza();
    }

    private zzma(zzdb<zzmd> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzma() {
        this(zzda.zza(new zzmc()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzmd zza() {
        return this.zzb.zza();
    }
}