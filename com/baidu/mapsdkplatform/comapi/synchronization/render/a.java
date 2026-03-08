package com.baidu.mapsdkplatform.comapi.synchronization.render;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class a implements Parcelable.Creator<LinkPointPolyLineInfo> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public LinkPointPolyLineInfo createFromParcel(Parcel parcel) {
        return new LinkPointPolyLineInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public LinkPointPolyLineInfo[] newArray(int i) {
        return new LinkPointPolyLineInfo[i];
    }
}