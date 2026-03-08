package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class q implements Parcelable.Creator<VehicleInfo> {
    q() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public VehicleInfo createFromParcel(Parcel parcel) {
        return new VehicleInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public VehicleInfo[] newArray(int i) {
        return new VehicleInfo[i];
    }
}