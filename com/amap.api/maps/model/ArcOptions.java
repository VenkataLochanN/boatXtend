package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ArcOptions extends BaseOptions implements Parcelable {
    public static final ArcOptionsCreator CREATOR = new ArcOptionsCreator();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f1848a;
    private LatLng endpoint;
    private LatLng passedpoint;
    private LatLng startpoint;
    private float strokeWidth = 10.0f;
    private int strokeColor = ViewCompat.MEASURED_STATE_MASK;
    private float zIndex = 0.0f;
    private boolean isVisible = true;
    private final String type = "ArcOptions";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        LatLng latLng = this.startpoint;
        if (latLng != null) {
            bundle.putDouble("startlat", latLng.latitude);
            bundle.putDouble("startlng", this.startpoint.longitude);
        }
        LatLng latLng2 = this.passedpoint;
        if (latLng2 != null) {
            bundle.putDouble("passedlat", latLng2.latitude);
            bundle.putDouble("passedlng", this.passedpoint.longitude);
        }
        LatLng latLng3 = this.endpoint;
        if (latLng3 != null) {
            bundle.putDouble("endlat", latLng3.latitude);
            bundle.putDouble("endlng", this.endpoint.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeFloat(this.strokeWidth);
        parcel.writeInt(this.strokeColor);
        parcel.writeFloat(this.zIndex);
        parcel.writeByte(this.isVisible ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f1848a);
    }

    public ArcOptions point(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        this.startpoint = latLng;
        this.passedpoint = latLng2;
        this.endpoint = latLng3;
        return this;
    }

    public ArcOptions strokeWidth(float f2) {
        this.strokeWidth = f2;
        return this;
    }

    public ArcOptions strokeColor(int i) {
        this.strokeColor = i;
        return this;
    }

    public ArcOptions zIndex(float f2) {
        this.zIndex = f2;
        return this;
    }

    public ArcOptions visible(boolean z) {
        this.isVisible = z;
        return this;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public float getZIndex() {
        return this.zIndex;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public LatLng getStart() {
        return this.startpoint;
    }

    public LatLng getPassed() {
        return this.passedpoint;
    }

    public LatLng getEnd() {
        return this.endpoint;
    }
}