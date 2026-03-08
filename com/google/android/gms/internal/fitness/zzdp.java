package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

/* JADX INFO: loaded from: classes.dex */
final class zzdp extends zzaj<DataReadResult> {
    private final /* synthetic */ DataReadRequest zzfl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdp(zzdj zzdjVar, GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        super(googleApiClient);
        this.zzfl = dataReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return DataReadResult.zza(status, this.zzfl.getDataTypes(), this.zzfl.getDataSources());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new DataReadRequest(this.zzfl, new zzds(this, null)));
    }
}