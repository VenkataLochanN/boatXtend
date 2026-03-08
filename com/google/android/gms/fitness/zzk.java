package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzk implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zzk();

    private zzk() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((ListSubscriptionsResult) result).getSubscriptions();
    }
}