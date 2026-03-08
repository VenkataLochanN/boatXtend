package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class r implements Parcelable.Creator<WalkingRouteLine> {
    r() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalkingRouteLine createFromParcel(Parcel parcel) {
        return new WalkingRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public WalkingRouteLine[] newArray(int i) {
        return new WalkingRouteLine[i];
    }
}