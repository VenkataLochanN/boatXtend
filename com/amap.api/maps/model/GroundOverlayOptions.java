package com.amap.api.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class GroundOverlayOptions extends BaseOptions implements Parcelable {
    private static final String CLASSNAME = "GroundOverlayOptions";
    public static final GroundOverlayOptionsCreator CREATOR = new GroundOverlayOptionsCreator();
    public static final float NO_DIMENSION = -1.0f;
    private final double NF_PI;
    private final double R;
    private float anchorU;
    private float anchorV;
    private float bearing;
    private BitmapDescriptor bitmapDescriptor;
    private String bitmapSymbol;
    private float height;
    private boolean isVisible;
    private LatLng latLng;
    private LatLngBounds latlngBounds;
    private final int mVersionCode;
    private LatLng northeast;
    private LatLng southwest;
    private float transparency;
    private final String type;
    private float width;
    private float zIndex;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f2, float f3, LatLngBounds latLngBounds, float f4, float f5, boolean z, float f6, float f7, float f8) {
        this.zIndex = 0.0f;
        this.isVisible = true;
        this.transparency = 0.0f;
        this.anchorU = 0.5f;
        this.anchorV = 0.5f;
        this.NF_PI = 0.01745329251994329d;
        this.R = 6371000.79d;
        this.type = CLASSNAME;
        this.mVersionCode = i;
        this.bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(null);
        this.latLng = latLng;
        this.width = f2;
        this.height = f3;
        this.latlngBounds = latLngBounds;
        this.bearing = f4;
        this.zIndex = f5;
        this.isVisible = z;
        this.transparency = f6;
        this.anchorU = f7;
        this.anchorV = f8;
        this.southwest = latLngBounds.southwest;
        this.northeast = latLngBounds.northeast;
    }

    public GroundOverlayOptions() {
        this.zIndex = 0.0f;
        this.isVisible = true;
        this.transparency = 0.0f;
        this.anchorU = 0.5f;
        this.anchorV = 0.5f;
        this.NF_PI = 0.01745329251994329d;
        this.R = 6371000.79d;
        this.type = CLASSNAME;
        this.mVersionCode = 1;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        this.bitmapDescriptor = bitmapDescriptor;
        BitmapDescriptor bitmapDescriptor2 = this.bitmapDescriptor;
        if (bitmapDescriptor2 != null) {
            this.bitmapSymbol = bitmapDescriptor2.getId();
        }
        return this;
    }

    public GroundOverlayOptions anchor(float f2, float f3) {
        this.anchorU = f2;
        this.anchorV = f3;
        if (this.latlngBounds != null) {
            a();
        } else if (this.latLng != null) {
            b();
        }
        return this;
    }

    public GroundOverlayOptions position(LatLng latLng, float f2) {
        if (this.latlngBounds != null) {
            Log.w(CLASSNAME, "Position has already been set using positionFromBounds");
        }
        if (latLng == null) {
            Log.w(CLASSNAME, "Location must be specified");
        }
        if (f2 <= 0.0f) {
            Log.w(CLASSNAME, "Width must be non-negative");
        }
        return a(latLng, f2, f2);
    }

    public GroundOverlayOptions position(LatLng latLng, float f2, float f3) {
        if (this.latlngBounds != null) {
            Log.w(CLASSNAME, "Position has already been set using positionFromBounds");
        }
        if (latLng == null) {
            Log.w(CLASSNAME, "Location must be specified");
        }
        if (f2 <= 0.0f || f3 <= 0.0f) {
            Log.w(CLASSNAME, "Width and Height must be non-negative");
        }
        return a(latLng, f2, f3);
    }

    private GroundOverlayOptions a(LatLng latLng, float f2, float f3) {
        this.latLng = latLng;
        this.width = f2;
        this.height = f3;
        b();
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        this.latlngBounds = latLngBounds;
        this.southwest = latLngBounds.southwest;
        this.northeast = latLngBounds.northeast;
        a();
        return this;
    }

    public GroundOverlayOptions bearing(float f2) {
        this.bearing = f2;
        return this;
    }

    public GroundOverlayOptions zIndex(float f2) {
        this.zIndex = f2;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.isVisible = z;
        return this;
    }

    public GroundOverlayOptions transparency(float f2) {
        if (f2 < 0.0f) {
            Log.w(CLASSNAME, "Transparency must be in the range [0..1]");
            f2 = 0.0f;
        }
        this.transparency = f2;
        return this;
    }

    public BitmapDescriptor getImage() {
        return this.bitmapDescriptor;
    }

    public LatLng getLocation() {
        return this.latLng;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public LatLngBounds getBounds() {
        return this.latlngBounds;
    }

    public float getBearing() {
        return this.bearing;
    }

    public float getZIndex() {
        return this.zIndex;
    }

    public float getTransparency() {
        return this.transparency;
    }

    public float getAnchorU() {
        return this.anchorU;
    }

    public float getAnchorV() {
        return this.anchorV;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mVersionCode);
        parcel.writeParcelable(this.bitmapDescriptor, i);
        parcel.writeParcelable(this.latLng, i);
        parcel.writeFloat(this.width);
        parcel.writeFloat(this.height);
        parcel.writeParcelable(this.latlngBounds, i);
        parcel.writeFloat(this.bearing);
        parcel.writeFloat(this.zIndex);
        parcel.writeByte(this.isVisible ? (byte) 1 : (byte) 0);
        parcel.writeFloat(this.transparency);
        parcel.writeFloat(this.anchorU);
        parcel.writeFloat(this.anchorV);
    }

    private void a() {
        LatLng latLng = this.southwest;
        if (latLng == null || this.northeast == null) {
            return;
        }
        this.latLng = new LatLng(latLng.latitude + (((double) (1.0f - this.anchorV)) * (this.northeast.latitude - this.southwest.latitude)), this.southwest.longitude + (((double) this.anchorU) * (this.northeast.longitude - this.southwest.longitude)));
        this.width = (float) (Math.cos(this.latLng.latitude * 0.01745329251994329d) * 6371000.79d * (this.northeast.longitude - this.southwest.longitude) * 0.01745329251994329d);
        this.height = (float) ((this.northeast.latitude - this.southwest.latitude) * 6371000.79d * 0.01745329251994329d);
    }

    private void b() {
        LatLng latLng = this.latLng;
        if (latLng == null) {
            return;
        }
        double dCos = ((double) this.width) / ((Math.cos(latLng.latitude * 0.01745329251994329d) * 6371000.79d) * 0.01745329251994329d);
        double d2 = ((double) this.height) / 111194.94043265979d;
        try {
            this.latlngBounds = new LatLngBounds(new LatLng(this.latLng.latitude - (((double) (1.0f - this.anchorV)) * d2), this.latLng.longitude - (((double) this.anchorU) * dCos)), new LatLng(this.latLng.latitude + (((double) this.anchorV) * d2), this.latLng.longitude + (((double) (1.0f - this.anchorU)) * dCos)));
            this.southwest = this.latlngBounds.southwest;
            this.northeast = this.latlngBounds.northeast;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}