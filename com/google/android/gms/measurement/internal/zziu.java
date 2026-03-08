package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zziu implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzkq zzb;
    private final /* synthetic */ zzm zzc;
    private final /* synthetic */ zzis zzd;

    zziu(zzis zzisVar, boolean z, zzkq zzkqVar, zzm zzmVar) {
        this.zzd = zzisVar;
        this.zza = z;
        this.zzb = zzkqVar;
        this.zzc = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar = this.zzd.zzb;
        if (zzetVar == null) {
            this.zzd.zzr().zzf().zza("Discarding data. Failed to set user property");
        } else {
            this.zzd.zza(zzetVar, this.zza ? null : this.zzb, this.zzc);
            this.zzd.zzaj();
        }
    }
}