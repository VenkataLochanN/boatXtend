package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjs implements zzdb<zzjv> {
    private static zzjs zza = new zzjs();
    private final zzdb<zzjv> zzb;

    public static boolean zzb() {
        return ((zzjv) zza.zza()).zza();
    }

    private zzjs(zzdb<zzjv> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzjs() {
        this(zzda.zza(new zzju()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjv zza() {
        return this.zzb.zza();
    }
}