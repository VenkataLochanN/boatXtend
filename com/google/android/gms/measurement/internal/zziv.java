package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zziv implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzm zzb;
    private final /* synthetic */ zzis zzc;

    zziv(zzis zzisVar, AtomicReference atomicReference, zzm zzmVar) {
        this.zzc = zzisVar;
        this.zza = atomicReference;
        this.zzb = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar;
        synchronized (this.zza) {
            try {
                try {
                    zzetVar = this.zzc.zzb;
                } catch (RemoteException e2) {
                    this.zzc.zzr().zzf().zza("Failed to get app instance id", e2);
                }
                if (zzetVar == null) {
                    this.zzc.zzr().zzf().zza("Failed to get app instance id");
                    return;
                }
                this.zza.set(zzetVar.zzc(this.zzb));
                String str = (String) this.zza.get();
                if (str != null) {
                    this.zzc.zzf().zza(str);
                    this.zzc.zzs().zzj.zza(str);
                }
                this.zzc.zzaj();
                this.zza.notify();
            } finally {
                this.zza.notify();
            }
        }
    }
}