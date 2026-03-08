package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* JADX INFO: loaded from: classes.dex */
final class zzdz extends zzci {
    private final BaseImplementation.ResultHolder<ListSubscriptionsResult> zzev;

    private zzdz(BaseImplementation.ResultHolder<ListSubscriptionsResult> resultHolder) {
        this.zzev = resultHolder;
    }

    /* synthetic */ zzdz(BaseImplementation.ResultHolder resultHolder, zzdu zzduVar) {
        this(resultHolder);
    }

    @Override // com.google.android.gms.internal.fitness.zzch
    public final void zza(ListSubscriptionsResult listSubscriptionsResult) {
        this.zzev.setResult(listSubscriptionsResult);
    }
}