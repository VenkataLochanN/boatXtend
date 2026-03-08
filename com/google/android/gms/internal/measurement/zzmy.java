package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmy implements zzdb<zznb> {
    private static zzmy zza = new zzmy();
    private final zzdb<zznb> zzb;

    public static boolean zzb() {
        return ((zznb) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zznb) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zznb) zza.zza()).zzc();
    }

    private zzmy(zzdb<zznb> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzmy() {
        this(zzda.zza(new zzna()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zznb zza() {
        return this.zzb.zza();
    }
}