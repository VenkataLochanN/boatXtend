package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.DrivingRouteLine;

/* JADX INFO: loaded from: classes.dex */
final class e implements Parcelable.Creator<DrivingRouteLine.DrivingStep> {
    e() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public DrivingRouteLine.DrivingStep createFromParcel(Parcel parcel) {
        return new DrivingRouteLine.DrivingStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public DrivingRouteLine.DrivingStep[] newArray(int i) {
        return new DrivingRouteLine.DrivingStep[i];
    }
}