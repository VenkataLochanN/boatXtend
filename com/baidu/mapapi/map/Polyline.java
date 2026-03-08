package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Polyline extends Overlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2943a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<LatLng> f2944b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int[] f2945c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int[] f2946d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2947e = 5;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f2948f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f2949g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f2950h = true;
    BitmapDescriptor i;
    List<BitmapDescriptor> j;

    Polyline() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.polyline;
    }

    private Bundle a(boolean z) {
        return (z ? BitmapDescriptorFactory.fromAsset("lineDashTexture.png") : this.i).b();
    }

    private static void a(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("traffic_array", iArr);
    }

    private Bundle b(boolean z) {
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putInt("total", 1);
            bundle.putBundle("texture_0", BitmapDescriptorFactory.fromAsset("lineDashTexture.png").b());
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        int i = 0;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            if (this.j.get(i2) != null) {
                bundle2.putBundle("texture_" + String.valueOf(i), this.j.get(i2).b());
                i++;
            }
        }
        bundle2.putInt("total", i);
        return bundle2;
    }

    private static void b(int[] iArr, Bundle bundle) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        bundle.putIntArray("color_array", iArr);
        bundle.putInt("total", 1);
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f2944b.get(0));
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("width", this.f2947e);
        Overlay.a(this.f2944b, bundle);
        Overlay.a(this.f2943a, bundle);
        a(this.f2945c, bundle);
        b(this.f2946d, bundle);
        int[] iArr = this.f2945c;
        int i = 1;
        if (iArr != null && iArr.length > 0 && iArr.length > this.f2944b.size() - 1) {
            Log.e("baidumapsdk", "the size of textureIndexs is larger than the size of points");
        }
        bundle.putInt("dotline", this.f2948f ? 1 : 0);
        bundle.putInt("focus", this.f2949g ? 1 : 0);
        try {
            if (this.i != null) {
                bundle.putInt("custom", 1);
                bundle.putBundle("image_info", a(false));
            } else {
                if (this.f2948f) {
                    bundle.putBundle("image_info", a(true));
                }
                bundle.putInt("custom", 0);
            }
            if (this.j != null) {
                bundle.putInt("customlist", 1);
                bundle.putBundle("image_info_list", b(false));
            } else {
                if (this.f2948f && ((this.f2945c != null && this.f2945c.length > 0) || (this.f2946d != null && this.f2946d.length > 0))) {
                    bundle.putBundle("image_info_list", b(true));
                }
                bundle.putInt("customlist", 0);
            }
            if (!this.f2950h) {
                i = 0;
            }
            bundle.putInt("keep", i);
        } catch (Exception unused) {
            Log.e("baidumapsdk", "load texture resource failed!");
            bundle.putInt("dotline", 0);
        }
        return bundle;
    }

    public int getColor() {
        return this.f2943a;
    }

    public int[] getColorList() {
        return this.f2946d;
    }

    public List<LatLng> getPoints() {
        return this.f2944b;
    }

    public BitmapDescriptor getTexture() {
        return this.i;
    }

    public int getWidth() {
        return this.f2947e;
    }

    public boolean isDottedLine() {
        return this.f2948f;
    }

    public boolean isFocus() {
        return this.f2949g;
    }

    public boolean isIsKeepScale() {
        return this.f2950h;
    }

    public void setColor(int i) {
        this.f2943a = i;
        this.listener.b(this);
    }

    public void setColorList(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: colorList can not empty");
        }
        this.f2946d = iArr;
    }

    public void setDottedLine(boolean z) {
        this.f2948f = z;
        this.listener.b(this);
    }

    public void setFocus(boolean z) {
        this.f2949g = z;
        this.listener.b(this);
    }

    public void setIndexs(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: indexList can not empty");
        }
        this.f2945c = iArr;
    }

    public void setIsKeepScale(boolean z) {
        this.f2950h = z;
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than 2 or more than 10000");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
        }
        this.f2944b = list;
        this.listener.b(this);
    }

    public void setTexture(BitmapDescriptor bitmapDescriptor) {
        this.i = bitmapDescriptor;
        this.listener.b(this);
    }

    public void setTextureList(List<BitmapDescriptor> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("BDMapSDKException: textureList can not empty");
        }
        this.j = list;
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f2947e = i;
            this.listener.b(this);
        }
    }
}