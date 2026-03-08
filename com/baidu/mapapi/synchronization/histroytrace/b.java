package com.baidu.mapapi.synchronization.histroytrace;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceData;

/* JADX INFO: loaded from: classes.dex */
final class b implements Parcelable.Creator<HistoryTraceData.HistoryTracePoint> {
    b() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public HistoryTraceData.HistoryTracePoint createFromParcel(Parcel parcel) {
        return new HistoryTraceData.HistoryTracePoint(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public HistoryTraceData.HistoryTracePoint[] newArray(int i) {
        return new HistoryTraceData.HistoryTracePoint[i];
    }
}