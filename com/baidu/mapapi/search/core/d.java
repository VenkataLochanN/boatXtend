package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class d implements Parcelable.Creator<PlaneInfo> {
    d() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PlaneInfo createFromParcel(Parcel parcel) {
        return new PlaneInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PlaneInfo[] newArray(int i) {
        return new PlaneInfo[i];
    }
}