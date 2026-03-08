package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapsdkplatform.comapi.synchronization.data.RouteLineInfo;

/* JADX INFO: loaded from: classes.dex */
final class c implements Parcelable.Creator<RouteLineInfo.RouteSectionInfo> {
    c() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public RouteLineInfo.RouteSectionInfo createFromParcel(Parcel parcel) {
        return new RouteLineInfo.RouteSectionInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public RouteLineInfo.RouteSectionInfo[] newArray(int i) {
        return new RouteLineInfo.RouteSectionInfo[i];
    }
}