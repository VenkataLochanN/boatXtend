package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class a implements Parcelable.Creator<PoiDetailResult> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiDetailResult createFromParcel(Parcel parcel) {
        return new PoiDetailResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiDetailResult[] newArray(int i) {
        return new PoiDetailResult[i];
    }
}