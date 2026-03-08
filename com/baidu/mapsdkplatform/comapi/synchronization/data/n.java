package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class n implements Parcelable.Creator<TrafficInfo> {
    n() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TrafficInfo createFromParcel(Parcel parcel) {
        return new TrafficInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TrafficInfo[] newArray(int i) {
        return new TrafficInfo[i];
    }
}