package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataTypeResult;

/* JADX INFO: loaded from: classes.dex */
final class zzdf extends zzbo {
    private final BaseImplementation.ResultHolder<DataTypeResult> zzev;

    private zzdf(BaseImplementation.ResultHolder<DataTypeResult> resultHolder) {
        this.zzev = resultHolder;
    }

    /* synthetic */ zzdf(BaseImplementation.ResultHolder resultHolder, zzdc zzdcVar) {
        this(resultHolder);
    }

    @Override // com.google.android.gms.internal.fitness.zzbn
    public final void zza(DataTypeResult dataTypeResult) {
        this.zzev.setResult(dataTypeResult);
    }
}