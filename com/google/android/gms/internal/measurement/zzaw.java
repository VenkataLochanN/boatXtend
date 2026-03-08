package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzaw extends zzx.zza {
    private final /* synthetic */ com.google.android.gms.measurement.internal.zzhi zzc;
    private final /* synthetic */ zzx zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaw(zzx zzxVar, com.google.android.gms.measurement.internal.zzhi zzhiVar) {
        super(zzxVar);
        this.zzd = zzxVar;
        this.zzc = zzhiVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        for (int i = 0; i < this.zzd.zzf.size(); i++) {
            if (this.zzc.equals(((Pair) this.zzd.zzf.get(i)).first)) {
                Log.w(this.zzd.zzc, "OnEventListener already registered.");
                return;
            }
        }
        zzx.zzb zzbVar = new zzx.zzb(this.zzc);
        this.zzd.zzf.add(new Pair(this.zzc, zzbVar));
        this.zzd.zzr.registerOnMeasurementEventListener(zzbVar);
    }
}