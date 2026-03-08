package com.baidu.mapapi.search.share;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class a implements Parcelable.Creator<ShareUrlResult> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ShareUrlResult createFromParcel(Parcel parcel) {
        return new ShareUrlResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ShareUrlResult[] newArray(int i) {
        return new ShareUrlResult[i];
    }
}