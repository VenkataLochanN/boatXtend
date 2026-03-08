package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: classes.dex */
abstract class zzav<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzas> {
    public zzav(GoogleApiClient googleApiClient) {
        super(zzas.API, googleApiClient);
    }
}