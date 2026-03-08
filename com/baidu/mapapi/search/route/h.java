package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class h implements Parcelable.Creator<IndoorRouteResult> {
    h() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public IndoorRouteResult createFromParcel(Parcel parcel) {
        return new IndoorRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public IndoorRouteResult[] newArray(int i) {
        return new IndoorRouteResult[i];
    }
}