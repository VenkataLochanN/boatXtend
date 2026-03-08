package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.SessionInsertRequest;

/* JADX INFO: loaded from: classes.dex */
final class zzeh extends zzbd {
    private final /* synthetic */ SessionInsertRequest zzfz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzeh(zzee zzeeVar, GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        super(googleApiClient);
        this.zzfz = sessionInsertRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new SessionInsertRequest(this.zzfz, new zzen(this)));
    }
}