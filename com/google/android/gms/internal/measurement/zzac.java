package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzac extends zzx.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzk zze;
    private final /* synthetic */ zzx zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzac(zzx zzxVar, String str, String str2, zzk zzkVar) {
        super(zzxVar);
        this.zzf = zzxVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzkVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        this.zzf.zzr.getConditionalUserProperties(this.zzc, this.zzd, this.zze);
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    protected final void zzb() {
        this.zze.zza((Bundle) null);
    }
}