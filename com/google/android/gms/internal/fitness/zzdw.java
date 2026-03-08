package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Subscription;

/* JADX INFO: loaded from: classes.dex */
final class zzdw extends zzar {
    private final /* synthetic */ Subscription zzfr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdw(zzdt zzdtVar, GoogleApiClient googleApiClient, Subscription subscription) {
        super(googleApiClient);
        this.zzfr = subscription;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzam) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzbj(this.zzfr, false, (zzcq) new zzen(this)));
    }
}