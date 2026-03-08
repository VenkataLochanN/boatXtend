package com.baidu.mapapi.search.sug;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
final class a implements Parcelable.Creator<SuggestionResult> {
    a() {
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SuggestionResult createFromParcel(Parcel parcel) {
        return new SuggestionResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public SuggestionResult[] newArray(int i) {
        return new SuggestionResult[i];
    }
}