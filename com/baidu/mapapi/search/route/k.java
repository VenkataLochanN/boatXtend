package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.MassTransitRouteLine;

/* JADX INFO: loaded from: classes.dex */
final class k implements Parcelable.Creator<MassTransitRouteLine.TransitStep.TrafficCondition> {
    k() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteLine.TransitStep.TrafficCondition createFromParcel(Parcel parcel) {
        return new MassTransitRouteLine.TransitStep.TrafficCondition(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteLine.TransitStep.TrafficCondition[] newArray(int i) {
        return new MassTransitRouteLine.TransitStep.TrafficCondition[i];
    }
}