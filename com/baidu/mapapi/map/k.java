package com.baidu.mapapi.map;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class k implements Parcelable.Creator<MapStatus> {
    k() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MapStatus createFromParcel(Parcel parcel) {
        return new MapStatus(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MapStatus[] newArray(int i) {
        return new MapStatus[i];
    }
}