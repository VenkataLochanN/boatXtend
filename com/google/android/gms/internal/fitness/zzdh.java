package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
final class zzdh extends zzae<GoalsResult> {
    private final /* synthetic */ GoalsReadRequest zzfd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdh(zzdg zzdgVar, GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        super(googleApiClient);
        this.zzfd = goalsReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new GoalsResult(status, Collections.emptyList());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbx) ((zzab) anyClient).getService()).zza(new GoalsReadRequest(this.zzfd, new zzdi(this)));
    }
}