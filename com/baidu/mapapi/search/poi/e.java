package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class e implements Parcelable.Creator<PoiIndoorResult> {
    e() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiIndoorResult createFromParcel(Parcel parcel) {
        return new PoiIndoorResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiIndoorResult[] newArray(int i) {
        return new PoiIndoorResult[i];
    }
}