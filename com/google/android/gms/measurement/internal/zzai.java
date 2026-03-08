package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzai implements Runnable {
    private final /* synthetic */ zzhc zza;
    private final /* synthetic */ zzaf zzb;

    zzai(zzaf zzafVar, zzhc zzhcVar) {
        this.zzb = zzafVar;
        this.zza = zzhcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzu();
        if (zzw.zza()) {
            this.zza.zzq().zza(this);
            return;
        }
        boolean zZzb = this.zzb.zzb();
        zzaf.zza(this.zzb, 0L);
        if (zZzb) {
            this.zzb.zza();
        }
    }
}