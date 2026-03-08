package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjl implements Runnable {
    private final /* synthetic */ zzet zza;
    private final /* synthetic */ zzjk zzb;

    zzjl(zzjk zzjkVar, zzet zzetVar) {
        this.zzb = zzjkVar;
        this.zza = zzetVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb) {
            zzjk.zza(this.zzb, false);
            if (!this.zzb.zza.zzab()) {
                this.zzb.zza.zzr().zzw().zza("Connected to remote service");
                this.zzb.zza.zza(this.zza);
            }
        }
    }
}