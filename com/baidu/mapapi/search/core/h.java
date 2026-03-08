package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.PoiInfo;

/* JADX INFO: loaded from: classes.dex */
final class h implements Parcelable.Creator<PoiInfo.ParentPoiInfo> {
    h() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiInfo.ParentPoiInfo createFromParcel(Parcel parcel) {
        return new PoiInfo.ParentPoiInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public PoiInfo.ParentPoiInfo[] newArray(int i) {
        return new PoiInfo.ParentPoiInfo[i];
    }
}