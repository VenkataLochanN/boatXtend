package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class CameraPositionCreator implements Parcelable.Creator<CameraPosition> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CameraPosition createFromParcel(Parcel parcel) {
        float f2 = parcel.readFloat();
        float f3 = parcel.readFloat();
        float f4 = parcel.readFloat();
        return new CameraPosition(new LatLng(f3, f4), parcel.readFloat(), parcel.readFloat(), f2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }
}