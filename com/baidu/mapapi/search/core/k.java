package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class k implements Parcelable.Creator<RouteStep> {
    k() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public RouteStep createFromParcel(Parcel parcel) {
        return new RouteStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public RouteStep[] newArray(int i) {
        return new RouteStep[i];
    }
}