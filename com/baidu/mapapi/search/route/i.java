package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class i implements Parcelable.Creator<MassTransitRouteLine> {
    i() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteLine createFromParcel(Parcel parcel) {
        return new MassTransitRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public MassTransitRouteLine[] newArray(int i) {
        return new MassTransitRouteLine[i];
    }
}