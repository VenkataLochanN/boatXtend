package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class n implements Parcelable.Creator<TrainInfo> {
    n() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TrainInfo createFromParcel(Parcel parcel) {
        return new TrainInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TrainInfo[] newArray(int i) {
        return new TrainInfo[i];
    }
}