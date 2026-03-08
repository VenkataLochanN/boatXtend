package com.baidu.mapapi.search.share;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;

/* JADX INFO: loaded from: classes.dex */
public class ShareUrlResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<ShareUrlResult> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3304b;

    public ShareUrlResult() {
    }

    protected ShareUrlResult(Parcel parcel) {
        this.f3303a = parcel.readString();
        this.f3304b = parcel.readInt();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return this.f3303a;
    }

    public void setType(int i) {
        this.f3304b = i;
    }

    public void setUrl(String str) {
        this.f3303a = str;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3303a);
        parcel.writeInt(this.f3304b);
    }
}