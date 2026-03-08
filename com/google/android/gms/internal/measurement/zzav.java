package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzav extends zzx.zza {
    private final /* synthetic */ zzk zzc;
    private final /* synthetic */ int zzd;
    private final /* synthetic */ zzx zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzav(zzx zzxVar, zzk zzkVar, int i) {
        super(zzxVar);
        this.zze = zzxVar;
        this.zzc = zzkVar;
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        this.zze.zzr.getTestFlag(this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    protected final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}