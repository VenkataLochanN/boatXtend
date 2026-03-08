package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class b implements Parcelable.Creator<ReverseGeoCodeResult> {
    b() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ReverseGeoCodeResult createFromParcel(Parcel parcel) {
        return new ReverseGeoCodeResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ReverseGeoCodeResult[] newArray(int i) {
        return new ReverseGeoCodeResult[i];
    }
}