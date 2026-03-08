package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class j implements Parcelable.Creator<SyncResponseResult> {
    j() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SyncResponseResult createFromParcel(Parcel parcel) {
        return new SyncResponseResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SyncResponseResult[] newArray(int i) {
        return new SyncResponseResult[i];
    }
}