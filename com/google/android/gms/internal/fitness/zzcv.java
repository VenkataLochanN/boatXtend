package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.BleScanCallback;

/* JADX INFO: loaded from: classes.dex */
final class zzcv extends zzu {
    private final /* synthetic */ BleScanCallback zzey;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcv(zzct zzctVar, GoogleApiClient googleApiClient, BleScanCallback bleScanCallback) {
        super(googleApiClient);
        this.zzey = bleScanCallback;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzbh(this.zzey, new zzen(this)));
    }
}