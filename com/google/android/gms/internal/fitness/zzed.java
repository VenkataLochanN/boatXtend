package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
final class zzed extends zzax {
    private final /* synthetic */ PendingIntent zzfk;
    private final /* synthetic */ com.google.android.gms.fitness.data.zzt zzfw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzed(zzea zzeaVar, GoogleApiClient googleApiClient, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzfw = zztVar;
        this.zzfk = pendingIntent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.fitness.zzax
    /* JADX INFO: renamed from: a_ */
    public final Status createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.internal.fitness.zzax, com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return createFailedResult(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcd) ((zzas) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzar(this.zzfw, this.zzfk, new zzen(this)));
    }
}