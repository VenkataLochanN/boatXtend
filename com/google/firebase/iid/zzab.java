package com.google.firebase.iid;

import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzab implements Runnable {
    private final zzw zza;
    private final IBinder zzb;

    zzab(zzw zzwVar, IBinder iBinder) {
        this.zza = zzwVar;
        this.zzb = iBinder;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzw zzwVar = this.zza;
        IBinder iBinder = this.zzb;
        synchronized (zzwVar) {
            try {
                if (iBinder == null) {
                    zzwVar.zza(0, "Null service connection");
                    return;
                }
                try {
                    zzwVar.zzc = new zzaf(iBinder);
                    zzwVar.zza = 2;
                    zzwVar.zza();
                } catch (RemoteException e2) {
                    zzwVar.zza(0, e2.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}