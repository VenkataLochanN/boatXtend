package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjx implements zzdb<zzjw> {
    private static zzjx zza = new zzjx();
    private final zzdb<zzjw> zzb;

    public static boolean zzb() {
        return ((zzjw) zza.zza()).zza();
    }

    private zzjx(zzdb<zzjw> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzjx() {
        this(zzda.zza(new zzjz()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjw zza() {
        return this.zzb.zza();
    }
}