package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* JADX INFO: loaded from: classes.dex */
final class zzda extends zzes {
    private final BaseImplementation.ResultHolder<BleDevicesResult> zzev;

    private zzda(BaseImplementation.ResultHolder<BleDevicesResult> resultHolder) {
        this.zzev = resultHolder;
    }

    /* synthetic */ zzda(BaseImplementation.ResultHolder resultHolder, zzcu zzcuVar) {
        this(resultHolder);
    }

    @Override // com.google.android.gms.internal.fitness.zzer
    public final void zza(BleDevicesResult bleDevicesResult) {
        this.zzev.setResult(bleDevicesResult);
    }
}