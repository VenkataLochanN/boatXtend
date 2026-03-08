package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zziz implements zzdb<zziy> {
    private static zziz zza = new zziz();
    private final zzdb<zziy> zzb;

    public static boolean zzb() {
        return ((zziy) zza.zza()).zza();
    }

    private zziz(zzdb<zziy> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zziz() {
        this(zzda.zza(new zzjb()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zziy zza() {
        return this.zzb.zza();
    }
}