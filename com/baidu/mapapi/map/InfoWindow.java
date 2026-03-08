package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class InfoWindow {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    BitmapDescriptor f2847a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    View f2848b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LatLng f2849c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    OnInfoWindowClickListener f2850d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    a f2851e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f2852f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f2853g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2854h;
    boolean i;
    boolean j;
    boolean k;
    private String l;

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick();
    }

    interface a {
        void a(InfoWindow infoWindow);

        void b(InfoWindow infoWindow);
    }

    public InfoWindow(View view, LatLng latLng, int i) {
        this.l = "";
        this.f2853g = false;
        this.f2854h = SysOSUtil.getDensityDpi();
        this.i = false;
        this.j = false;
        this.k = false;
        if (view == null || latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: view and position can not be null");
        }
        this.f2848b = view;
        this.f2849c = latLng;
        this.f2852f = i;
        this.j = true;
    }

    public InfoWindow(View view, LatLng latLng, int i, boolean z, int i2) {
        this.l = "";
        this.f2853g = false;
        this.f2854h = SysOSUtil.getDensityDpi();
        this.i = false;
        this.j = false;
        this.k = false;
        if (view == null || latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: view and position can not be null");
        }
        this.f2848b = view;
        this.f2849c = latLng;
        this.f2852f = i;
        this.f2853g = z;
        this.f2854h = i2;
        this.j = true;
    }

    public InfoWindow(BitmapDescriptor bitmapDescriptor, LatLng latLng, int i, OnInfoWindowClickListener onInfoWindowClickListener) {
        this.l = "";
        this.f2853g = false;
        this.f2854h = SysOSUtil.getDensityDpi();
        this.i = false;
        this.j = false;
        this.k = false;
        if (bitmapDescriptor == null || latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: bitmapDescriptor and position can not be null");
        }
        this.f2847a = bitmapDescriptor;
        this.f2849c = latLng;
        this.f2850d = onInfoWindowClickListener;
        this.f2852f = i;
        this.k = true;
    }

    public BitmapDescriptor getBitmapDescriptor() {
        return this.f2847a;
    }

    public LatLng getPosition() {
        return this.f2849c;
    }

    public String getTag() {
        return this.l;
    }

    public View getView() {
        return this.f2848b;
    }

    public int getYOffset() {
        return this.f2852f;
    }

    public void setBitmapDescriptor(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            return;
        }
        this.f2847a = bitmapDescriptor;
        this.f2851e.b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            return;
        }
        this.f2849c = latLng;
        this.f2851e.b(this);
    }

    public void setTag(String str) {
        this.l = str;
    }

    public void setView(View view) {
        if (view == null) {
            return;
        }
        this.f2848b = view;
        this.f2851e.b(this);
    }

    public void setYOffset(int i) {
        this.f2852f = i;
        this.f2851e.b(this);
    }
}