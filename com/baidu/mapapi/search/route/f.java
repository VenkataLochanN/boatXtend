package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class f implements Parcelable.Creator<DrivingRouteResult> {
    f() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public DrivingRouteResult createFromParcel(Parcel parcel) {
        return new DrivingRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public DrivingRouteResult[] newArray(int i) {
        return new DrivingRouteResult[i];
    }
}