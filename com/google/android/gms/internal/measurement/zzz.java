package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzz extends zzx.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Bundle zze;
    private final /* synthetic */ zzx zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzz(zzx zzxVar, String str, String str2, Bundle bundle) {
        super(zzxVar);
        this.zzf = zzxVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        this.zzf.zzr.clearConditionalUserProperty(this.zzc, this.zzd, this.zze);
    }
}