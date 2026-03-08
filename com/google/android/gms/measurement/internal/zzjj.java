package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjj implements Runnable {
    private final /* synthetic */ zzet zza;
    private final /* synthetic */ zzjk zzb;

    zzjj(zzjk zzjkVar, zzet zzetVar) {
        this.zzb = zzjkVar;
        this.zza = zzetVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb) {
            zzjk.zza(this.zzb, false);
            if (!this.zzb.zza.zzab()) {
                this.zzb.zza.zzr().zzx().zza("Connected to service");
                this.zzb.zza.zza(this.zza);
            }
        }
    }
}