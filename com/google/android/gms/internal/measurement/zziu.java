package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zziu implements zzdb<zzix> {
    private static zziu zza = new zziu();
    private final zzdb<zzix> zzb;

    public static boolean zzb() {
        return ((zzix) zza.zza()).zza();
    }

    private zziu(zzdb<zzix> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zziu() {
        this(zzda.zza(new zziw()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzix zza() {
        return this.zzb.zza();
    }
}