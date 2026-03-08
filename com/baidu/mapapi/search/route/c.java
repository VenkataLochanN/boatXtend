package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class c implements Parcelable.Creator<BikingRouteResult> {
    c() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BikingRouteResult createFromParcel(Parcel parcel) {
        return new BikingRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BikingRouteResult[] newArray(int i) {
        return new BikingRouteResult[i];
    }
}