package com.baidu.mapapi.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelItem implements Parcelable {
    public static final Parcelable.Creator<ParcelItem> CREATOR = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Bundle f3085a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getBundle() {
        return this.f3085a;
    }

    public void setBundle(Bundle bundle) {
        this.f3085a = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f3085a);
    }
}