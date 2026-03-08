package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Session;

/* JADX INFO: loaded from: classes.dex */
final class zzef extends zzbd {
    private final /* synthetic */ Session zzfx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzef(zzee zzeeVar, GoogleApiClient googleApiClient, Session session) {
        super(googleApiClient);
        this.zzfx = session;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzaz(this.zzfx, (zzcq) new zzen(this)));
    }
}