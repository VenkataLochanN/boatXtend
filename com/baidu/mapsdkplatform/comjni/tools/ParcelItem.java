package com.baidu.mapsdkplatform.comjni.tools;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelItem implements Parcelable {
    public static final Parcelable.Creator<ParcelItem> CREATOR = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Bundle f3870a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getBundle() {
        return this.f3870a;
    }

    public void setBundle(Bundle bundle) {
        this.f3870a = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f3870a);
    }
}