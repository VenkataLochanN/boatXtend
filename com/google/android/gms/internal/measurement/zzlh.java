package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlh implements zzdb<zzlg> {
    private static zzlh zza = new zzlh();
    private final zzdb<zzlg> zzb;

    public static boolean zzb() {
        return ((zzlg) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzlg) zza.zza()).zzb();
    }

    private zzlh(zzdb<zzlg> zzdbVar) {
        this.zzb = zzda.zza((zzdb) zzdbVar);
    }

    public zzlh() {
        this(zzda.zza(new zzlj()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlg zza() {
        return this.zzb.zza();
    }
}