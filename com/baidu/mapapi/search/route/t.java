package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class t implements Parcelable.Creator<WalkingRouteResult> {
    t() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalkingRouteResult createFromParcel(Parcel parcel) {
        return new WalkingRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalkingRouteResult[] newArray(int i) {
        return new WalkingRouteResult[i];
    }
}