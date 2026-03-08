package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzli implements zzdb<zzll> {
    private static zzli zza = new zzli();
    private final zzdb<zzll> zzb;

    public static boolean zzb() {
        return ((zzll) zza.zza()).zza();
    }

    private zzli(zzdb<zzll> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzli() {
        this(zzda.zza(new zzlk()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzll zza() {
        return this.zzb.zza();
    }
}