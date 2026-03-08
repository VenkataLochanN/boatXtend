package com.baidu.mapapi.search.busline;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class a implements Parcelable.Creator<BusLineResult> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BusLineResult createFromParcel(Parcel parcel) {
        return new BusLineResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BusLineResult[] newArray(int i) {
        return new BusLineResult[i];
    }
}