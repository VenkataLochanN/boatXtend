package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.TransitRouteLine;

/* JADX INFO: loaded from: classes.dex */
final class p implements Parcelable.Creator<TransitRouteLine.TransitStep> {
    p() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitRouteLine.TransitStep createFromParcel(Parcel parcel) {
        return new TransitRouteLine.TransitStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitRouteLine.TransitStep[] newArray(int i) {
        return new TransitRouteLine.TransitStep[i];
    }
}