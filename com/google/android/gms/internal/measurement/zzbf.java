package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzx;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzbf extends zzx.zza {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzx.zzd zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbf(zzx.zzd zzdVar, Activity activity) {
        super(zzx.this);
        this.zzd = zzdVar;
        this.zzc = activity;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    final void zza() throws RemoteException {
        zzx.this.zzr.onActivityStopped(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}