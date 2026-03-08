package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class b implements Parcelable.Creator<RouteLineInfo> {
    b() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public RouteLineInfo createFromParcel(Parcel parcel) {
        return new RouteLineInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public RouteLineInfo[] newArray(int i) {
        return new RouteLineInfo[i];
    }
}