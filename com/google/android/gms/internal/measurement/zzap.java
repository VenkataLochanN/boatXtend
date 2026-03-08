package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzap extends zzx.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzk zzf;
    private final /* synthetic */ zzx zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzap(zzx zzxVar, String str, String str2, boolean z, zzk zzkVar) {
        super(zzxVar);
        this.zzg = zzxVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = z;
        this.zzf = zzkVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        this.zzg.zzr.getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    protected final void zzb() {
        this.zzf.zza((Bundle) null);
    }
}