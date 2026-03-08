package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzne implements zzdb<zznh> {
    private static zzne zza = new zzne();
    private final zzdb<zznh> zzb;

    public static boolean zzb() {
        return ((zznh) zza.zza()).zza();
    }

    private zzne(zzdb<zznh> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzne() {
        this(zzda.zza(new zzng()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zznh zza() {
        return this.zzb.zza();
    }
}