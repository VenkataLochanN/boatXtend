package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionReadResult;

/* JADX INFO: loaded from: classes.dex */
final class zzel extends zzcl {
    private final BaseImplementation.ResultHolder<SessionReadResult> zzev;

    private zzel(BaseImplementation.ResultHolder<SessionReadResult> resultHolder) {
        this.zzev = resultHolder;
    }

    /* synthetic */ zzel(BaseImplementation.ResultHolder resultHolder, zzef zzefVar) {
        this(resultHolder);
    }

    @Override // com.google.android.gms.internal.fitness.zzck
    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        this.zzev.setResult(sessionReadResult);
    }
}