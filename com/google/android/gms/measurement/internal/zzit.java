package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzit implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzm zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzis zzd;

    zzit(zzis zzisVar, AtomicReference atomicReference, zzm zzmVar, boolean z) {
        this.zzd = zzisVar;
        this.zza = atomicReference;
        this.zzb = zzmVar;
        this.zzc = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar;
        synchronized (this.zza) {
            try {
                try {
                    zzetVar = this.zzd.zzb;
                } catch (RemoteException e2) {
                    this.zzd.zzr().zzf().zza("Failed to get all user properties; remote exception", e2);
                }
                if (zzetVar == null) {
                    this.zzd.zzr().zzf().zza("Failed to get all user properties; not connected to service");
                    return;
                }
                this.zza.set(zzetVar.zza(this.zzb, this.zzc));
                this.zzd.zzaj();
                this.zza.notify();
            } finally {
                this.zza.notify();
            }
        }
    }
}