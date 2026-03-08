package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class m implements Parcelable.Creator<TaxiInfo> {
    m() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TaxiInfo createFromParcel(Parcel parcel) {
        return new TaxiInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TaxiInfo[] newArray(int i) {
        return new TaxiInfo[i];
    }
}