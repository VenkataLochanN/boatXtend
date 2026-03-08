package com.baidu.mapsdkplatform.comjni.tools;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class b implements Parcelable.Creator<ParcelItem> {
    b() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelItem createFromParcel(Parcel parcel) {
        ParcelItem parcelItem = new ParcelItem();
        parcelItem.setBundle(parcel.readBundle());
        return parcelItem;
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelItem[] newArray(int i) {
        return new ParcelItem[i];
    }
}