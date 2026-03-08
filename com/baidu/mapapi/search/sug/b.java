package com.baidu.mapapi.search.sug;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.sug.SuggestionResult;

/* JADX INFO: loaded from: classes.dex */
final class b implements Parcelable.Creator<SuggestionResult.SuggestionInfo> {
    b() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SuggestionResult.SuggestionInfo createFromParcel(Parcel parcel) {
        return new SuggestionResult.SuggestionInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SuggestionResult.SuggestionInfo[] newArray(int i) {
        return new SuggestionResult.SuggestionInfo[i];
    }
}