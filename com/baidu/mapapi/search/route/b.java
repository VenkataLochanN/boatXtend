package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.BikingRouteLine;

/* JADX INFO: loaded from: classes.dex */
final class b implements Parcelable.Creator<BikingRouteLine.BikingStep> {
    b() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BikingRouteLine.BikingStep createFromParcel(Parcel parcel) {
        return new BikingRouteLine.BikingStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public BikingRouteLine.BikingStep[] newArray(int i) {
        return new BikingRouteLine.BikingStep[i];
    }
}