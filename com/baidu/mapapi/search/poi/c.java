package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class c implements Parcelable.Creator<PoiFilter> {
    c() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiFilter createFromParcel(Parcel parcel) {
        return new PoiFilter(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiFilter[] newArray(int i) {
        return new PoiFilter[i];
    }
}