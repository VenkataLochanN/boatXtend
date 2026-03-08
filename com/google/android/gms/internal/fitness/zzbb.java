package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: classes.dex */
abstract class zzbb<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzay> {
    public zzbb(GoogleApiClient googleApiClient) {
        super(zzay.API, googleApiClient);
    }
}