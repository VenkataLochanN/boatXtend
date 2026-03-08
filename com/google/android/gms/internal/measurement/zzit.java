package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzit implements zzdb<zzis> {
    private static zzit zza = new zzit();
    private final zzdb<zzis> zzb;

    public static boolean zzb() {
        return ((zzis) zza.zza()).zza();
    }

    private zzit(zzdb<zzis> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzit() {
        this(zzda.zza(new zziv()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzis zza() {
        return this.zzb.zza();
    }
}