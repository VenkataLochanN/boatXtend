package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.MassTransitRouteLine;

/* JADX INFO: loaded from: classes.dex */
final class j implements Parcelable.Creator<MassTransitRouteLine.TransitStep> {
    j() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteLine.TransitStep createFromParcel(Parcel parcel) {
        return new MassTransitRouteLine.TransitStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteLine.TransitStep[] newArray(int i) {
        return new MassTransitRouteLine.TransitStep[i];
    }
}