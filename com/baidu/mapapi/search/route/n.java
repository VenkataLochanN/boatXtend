package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class n implements Parcelable.Creator<SuggestAddrInfo> {
    n() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SuggestAddrInfo createFromParcel(Parcel parcel) {
        return new SuggestAddrInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SuggestAddrInfo[] newArray(int i) {
        return new SuggestAddrInfo[i];
    }
}