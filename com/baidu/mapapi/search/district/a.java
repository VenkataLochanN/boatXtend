package com.baidu.mapapi.search.district;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class a implements Parcelable.Creator<DistrictResult> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public DistrictResult createFromParcel(Parcel parcel) {
        return new DistrictResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public DistrictResult[] newArray(int i) {
        return new DistrictResult[i];
    }
}