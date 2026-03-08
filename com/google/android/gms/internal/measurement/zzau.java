package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzau extends zzx.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzk zzd;
    private final /* synthetic */ zzx zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzau(zzx zzxVar, String str, zzk zzkVar) {
        super(zzxVar);
        this.zze = zzxVar;
        this.zzc = str;
        this.zzd = zzkVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        this.zze.zzr.getMaxUserProperties(this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    protected final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}