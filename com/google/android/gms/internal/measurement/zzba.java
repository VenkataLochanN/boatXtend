package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzba extends zzx.zza {
    private final /* synthetic */ Long zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ boolean zzg;
    private final /* synthetic */ boolean zzh;
    private final /* synthetic */ zzx zzi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzba(zzx zzxVar, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzxVar);
        this.zzi = zzxVar;
        this.zzc = l;
        this.zzd = str;
        this.zze = str2;
        this.zzf = bundle;
        this.zzg = z;
        this.zzh = z2;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        Long l = this.zzc;
        this.zzi.zzr.logEvent(this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l == null ? this.zza : l.longValue());
    }
}