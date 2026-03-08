package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
final class zzeg extends zzbb<SessionStopResult> {
    private final /* synthetic */ String val$name = null;
    private final /* synthetic */ String zzfy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzeg(zzee zzeeVar, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzfy = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new SessionStopResult(status, Collections.emptyList());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzbb(this.val$name, this.zzfy, (zzcn) new zzem(this, null)));
    }
}