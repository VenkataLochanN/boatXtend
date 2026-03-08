package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class o implements Parcelable.Creator<TransitBaseInfo> {
    o() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitBaseInfo createFromParcel(Parcel parcel) {
        return new TransitBaseInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitBaseInfo[] newArray(int i) {
        return new TransitBaseInfo[i];
    }
}