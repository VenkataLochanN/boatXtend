package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzag extends zzx.zza {
    private final /* synthetic */ zzx zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzag(zzx zzxVar) {
        super(zzxVar);
        this.zzc = zzxVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        this.zzc.zzr.resetAnalyticsData(this.zza);
    }
}