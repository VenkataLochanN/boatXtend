package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzas extends zzx.zza {
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ zzx zzh;
    private final /* synthetic */ int zzc = 5;
    private final /* synthetic */ Object zzf = null;
    private final /* synthetic */ Object zzg = null;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzas(zzx zzxVar, boolean z, int i, String str, Object obj, Object obj2, Object obj3) {
        super(false);
        this.zzh = zzxVar;
        this.zzd = str;
        this.zze = obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        this.zzh.zzr.logHealthData(this.zzc, this.zzd, ObjectWrapper.wrap(this.zze), ObjectWrapper.wrap(this.zzf), ObjectWrapper.wrap(this.zzg));
    }
}