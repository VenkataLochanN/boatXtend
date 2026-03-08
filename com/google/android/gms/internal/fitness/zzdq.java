package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* JADX INFO: loaded from: classes.dex */
final class zzdq extends zzaj<DailyTotalResult> {
    private final /* synthetic */ DataType zzfm;
    private final /* synthetic */ boolean zzfn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdq(zzdj zzdjVar, GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        super(googleApiClient);
        this.zzfm = dataType;
        this.zzfn = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return DailyTotalResult.zza(status, this.zzfm);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzg((zzbe) new zzdr(this), this.zzfm, this.zzfn));
    }
}