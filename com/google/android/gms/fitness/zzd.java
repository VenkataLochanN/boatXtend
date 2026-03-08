package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DataTypeResult;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzd implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zzd();

    private zzd() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((DataTypeResult) result).getDataType();
    }
}