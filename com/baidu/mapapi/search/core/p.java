package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class p implements Parcelable.Creator<TransitResultNode> {
    p() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitResultNode createFromParcel(Parcel parcel) {
        return new TransitResultNode(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public TransitResultNode[] newArray(int i) {
        return new TransitResultNode[i];
    }
}