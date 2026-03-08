package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class f implements Parcelable.Creator<PoiDetailInfo> {
    f() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiDetailInfo createFromParcel(Parcel parcel) {
        return new PoiDetailInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiDetailInfo[] newArray(int i) {
        return new PoiDetailInfo[i];
    }
}