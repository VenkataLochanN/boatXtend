package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.StartBleScanRequest;

/* JADX INFO: loaded from: classes.dex */
final class zzcu extends zzu {
    private final /* synthetic */ StartBleScanRequest zzex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcu(zzct zzctVar, GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest) {
        super(googleApiClient);
        this.zzex = startBleScanRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new StartBleScanRequest(this.zzex, new zzen(this)));
    }
}