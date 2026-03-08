package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class MarkerOptions implements Parcelable {
    public static final MarkerOptionsCreator CREATOR = new MarkerOptionsCreator();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f1860a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f1865f;
    private boolean isBelowMaskLayer;
    private LatLng latLng;
    private String snippet;
    private String title;
    private float anchorU = 0.5f;
    private float anchorV = 1.0f;
    private float zIndex = 0.0f;
    private boolean isDraggable = false;
    private boolean isVisible = true;
    private boolean perspective = false;
    private int offsetX = 0;
    private int offsetY = 0;
    private ArrayList<BitmapDescriptor> bitmapDescriptors = new ArrayList<>();
    private int period = 20;
    private boolean isGps = false;
    private boolean isFlat = false;
    private boolean isRotatingMode = false;
    private float angleOffset = 0.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f1861b = 1.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1862c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1863d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1864e = 5;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MarkerOptions icons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            this.bitmapDescriptors = arrayList;
            this.isRotatingMode = false;
        }
        return this;
    }

    public MarkerOptions rotatingIcons(ArrayList<BitmapDescriptor> arrayList, float f2) {
        if (arrayList != null && arrayList.size() > 0) {
            this.bitmapDescriptors = arrayList;
            if (f2 != 0.0f) {
                this.angleOffset = f2;
            } else {
                this.angleOffset = 360.0f / arrayList.size();
            }
            this.isRotatingMode = true;
        }
        return this;
    }

    protected MarkerOptions angleOffset(float f2) {
        this.angleOffset = f2;
        return this;
    }

    public float getAngleOffset() {
        return this.angleOffset;
    }

    protected MarkerOptions setRotatingMode(boolean z) {
        this.isRotatingMode = z;
        return this;
    }

    public boolean isRotatingMode() {
        return this.isRotatingMode;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.bitmapDescriptors;
    }

    public MarkerOptions period(int i) {
        if (i <= 1) {
            this.period = 1;
        } else {
            this.period = i;
        }
        return this;
    }

    public int getPeriod() {
        return this.period;
    }

    public boolean isPerspective() {
        return this.perspective;
    }

    public MarkerOptions perspective(boolean z) {
        this.perspective = z;
        return this;
    }

    public MarkerOptions position(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public MarkerOptions setFlat(boolean z) {
        this.isFlat = z;
        return this;
    }

    private void a() {
        if (this.bitmapDescriptors == null) {
            try {
                this.bitmapDescriptors = new ArrayList<>();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        try {
            a();
            this.bitmapDescriptors.clear();
            this.bitmapDescriptors.add(bitmapDescriptor);
            this.isRotatingMode = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this;
    }

    public MarkerOptions anchor(float f2, float f3) {
        this.anchorU = f2;
        this.anchorV = f3;
        return this;
    }

    public MarkerOptions setInfoWindowOffset(int i, int i2) {
        this.offsetX = i;
        this.offsetY = i2;
        return this;
    }

    public MarkerOptions title(String str) {
        this.title = str;
        return this;
    }

    public MarkerOptions snippet(String str) {
        this.snippet = str;
        return this;
    }

    public MarkerOptions draggable(boolean z) {
        this.isDraggable = z;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.isVisible = z;
        return this;
    }

    public MarkerOptions setGps(boolean z) {
        this.isGps = z;
        return this;
    }

    public LatLng getPosition() {
        return this.latLng;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSnippet() {
        return this.snippet;
    }

    public BitmapDescriptor getIcon() {
        ArrayList<BitmapDescriptor> arrayList = this.bitmapDescriptors;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        return this.bitmapDescriptors.get(0);
    }

    public float getAnchorU() {
        return this.anchorU;
    }

    public int getInfoWindowOffsetX() {
        return this.offsetX;
    }

    public int getInfoWindowOffsetY() {
        return this.offsetY;
    }

    public float getAnchorV() {
        return this.anchorV;
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public boolean isGps() {
        return this.isGps;
    }

    public boolean isFlat() {
        return this.isFlat;
    }

    public MarkerOptions zIndex(float f2) {
        this.zIndex = f2;
        return this;
    }

    public float getZIndex() {
        return this.zIndex;
    }

    public MarkerOptions alpha(float f2) {
        this.f1861b = f2;
        return this;
    }

    public float getAlpha() {
        return this.f1861b;
    }

    public MarkerOptions autoOverturnInfoWindow(boolean z) {
        this.f1862c = z;
        return this;
    }

    public boolean isInfoWindowAutoOverturn() {
        return this.f1862c;
    }

    public MarkerOptions displayLevel(int i) {
        this.f1864e = i;
        return this;
    }

    public int getDisplayLevel() {
        return this.f1864e;
    }

    public MarkerOptions rotateAngle(float f2) {
        this.f1865f = f2;
        return this;
    }

    public float getRotateAngle() {
        return this.f1865f;
    }

    public MarkerOptions infoWindowEnable(boolean z) {
        this.f1863d = z;
        return this;
    }

    public boolean isInfoWindowEnable() {
        return this.f1863d;
    }

    public MarkerOptions belowMaskLayer(boolean z) {
        this.isBelowMaskLayer = z;
        return this;
    }

    public boolean isBelowMaskLayer() {
        return this.isBelowMaskLayer;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.latLng, i);
        parcel.writeString(this.title);
        parcel.writeString(this.snippet);
        parcel.writeFloat(this.anchorU);
        parcel.writeFloat(this.anchorV);
        parcel.writeInt(this.offsetX);
        parcel.writeInt(this.offsetY);
        parcel.writeBooleanArray(new boolean[]{this.isVisible, this.isDraggable, this.isGps, this.isFlat, this.f1862c, this.f1863d, this.isBelowMaskLayer, this.isRotatingMode});
        parcel.writeString(this.f1860a);
        parcel.writeInt(this.period);
        parcel.writeList(this.bitmapDescriptors);
        parcel.writeFloat(this.zIndex);
        parcel.writeFloat(this.f1861b);
        parcel.writeInt(this.f1864e);
        parcel.writeFloat(this.f1865f);
        parcel.writeFloat(this.angleOffset);
        ArrayList<BitmapDescriptor> arrayList = this.bitmapDescriptors;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        parcel.writeParcelable(this.bitmapDescriptors.get(0), i);
    }
}