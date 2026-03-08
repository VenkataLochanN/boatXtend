package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
final class zzcy extends zzu {
    private final /* synthetic */ String zzez;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcy(zzct zzctVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzez = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzbl(this.zzez, (zzcq) new zzen(this)));
    }
}