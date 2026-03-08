package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class l implements Parcelable.Creator<MassTransitRouteResult> {
    l() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteResult createFromParcel(Parcel parcel) {
        return new MassTransitRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteResult[] newArray(int i) {
        return new MassTransitRouteResult[i];
    }
}