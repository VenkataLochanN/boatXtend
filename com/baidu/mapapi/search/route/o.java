package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class o implements Parcelable.Creator<TransitRouteLine> {
    o() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitRouteLine createFromParcel(Parcel parcel) {
        return new TransitRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitRouteLine[] newArray(int i) {
        return new TransitRouteLine[i];
    }
}