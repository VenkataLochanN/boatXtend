package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzix implements Runnable {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzis zzc;

    zzix(zzis zzisVar, zzm zzmVar, boolean z) {
        this.zzc = zzisVar;
        this.zza = zzmVar;
        this.zzb = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar = this.zzc.zzb;
        if (zzetVar == null) {
            this.zzc.zzr().zzf().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzetVar.zza(this.zza);
            if (this.zzb) {
                this.zzc.zzj().zzad();
            }
            this.zzc.zza(zzetVar, (AbstractSafeParcelable) null, this.zza);
            this.zzc.zzaj();
        } catch (RemoteException e2) {
            this.zzc.zzr().zzf().zza("Failed to send app launch to the service", e2);
        }
    }
}