package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolylineOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2951a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bundle f2953c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<LatLng> f2955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<Integer> f2956f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<Integer> f2957g;
    private BitmapDescriptor i;
    private List<BitmapDescriptor> j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2954d = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2958h = 5;
    private boolean k = true;
    private boolean l = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2952b = true;
    private boolean m = false;

    @Override // com.baidu.mapapi.map.OverlayOptions
    Overlay a() {
        Polyline polyline = new Polyline();
        polyline.A = this.f2952b;
        polyline.f2948f = this.m;
        polyline.z = this.f2951a;
        polyline.B = this.f2953c;
        List<LatLng> list = this.f2955e;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("BDMapSDKException: when you add polyline, you must at least supply 2 points");
        }
        polyline.f2944b = this.f2955e;
        polyline.f2943a = this.f2954d;
        polyline.f2947e = this.f2958h;
        polyline.i = this.i;
        polyline.j = this.j;
        polyline.f2949g = this.k;
        polyline.f2950h = this.l;
        List<Integer> list2 = this.f2956f;
        if (list2 != null && list2.size() < this.f2955e.size() - 1) {
            ArrayList arrayList = new ArrayList((this.f2955e.size() - 1) - this.f2956f.size());
            List<Integer> list3 = this.f2956f;
            list3.addAll(list3.size(), arrayList);
        }
        List<Integer> list4 = this.f2956f;
        int i = 0;
        if (list4 != null && list4.size() > 0) {
            int[] iArr = new int[this.f2956f.size()];
            Iterator<Integer> it = this.f2956f.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                iArr[i2] = it.next().intValue();
                i2++;
            }
            polyline.f2945c = iArr;
        }
        List<Integer> list5 = this.f2957g;
        if (list5 != null && list5.size() < this.f2955e.size() - 1) {
            ArrayList arrayList2 = new ArrayList((this.f2955e.size() - 1) - this.f2957g.size());
            List<Integer> list6 = this.f2957g;
            list6.addAll(list6.size(), arrayList2);
        }
        List<Integer> list7 = this.f2957g;
        if (list7 != null && list7.size() > 0) {
            int[] iArr2 = new int[this.f2957g.size()];
            Iterator<Integer> it2 = this.f2957g.iterator();
            while (it2.hasNext()) {
                iArr2[i] = it2.next().intValue();
                i++;
            }
            polyline.f2946d = iArr2;
        }
        return polyline;
    }

    public PolylineOptions color(int i) {
        this.f2954d = i;
        return this;
    }

    public PolylineOptions colorsValues(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: colors list can not be null");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: colors list can not contains null");
        }
        this.f2957g = list;
        return this;
    }

    public PolylineOptions customTexture(BitmapDescriptor bitmapDescriptor) {
        this.i = bitmapDescriptor;
        return this;
    }

    public PolylineOptions customTextureList(List<BitmapDescriptor> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: customTexture list can not be null");
        }
        if (list.size() == 0) {
            Log.e("baidumapsdk", "custom texture list is empty,the texture will not work");
        }
        Iterator<BitmapDescriptor> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                Log.e("baidumapsdk", "the custom texture item is null,it will be discard");
            }
        }
        this.j = list;
        return this;
    }

    public PolylineOptions dottedLine(boolean z) {
        this.m = z;
        return this;
    }

    public PolylineOptions extraInfo(Bundle bundle) {
        this.f2953c = bundle;
        return this;
    }

    public PolylineOptions focus(boolean z) {
        this.k = z;
        return this;
    }

    public int getColor() {
        return this.f2954d;
    }

    public BitmapDescriptor getCustomTexture() {
        return this.i;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        return this.j;
    }

    public Bundle getExtraInfo() {
        return this.f2953c;
    }

    public List<LatLng> getPoints() {
        return this.f2955e;
    }

    public List<Integer> getTextureIndexs() {
        return this.f2956f;
    }

    public int getWidth() {
        return this.f2958h;
    }

    public int getZIndex() {
        return this.f2951a;
    }

    public boolean isDottedLine() {
        return this.m;
    }

    public boolean isFocus() {
        return this.k;
    }

    public boolean isVisible() {
        return this.f2952b;
    }

    public PolylineOptions keepScale(boolean z) {
        this.l = z;
        return this;
    }

    public PolylineOptions points(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than 2");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
        }
        this.f2955e = list;
        return this;
    }

    public PolylineOptions textureIndex(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: indexs list can not be null");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: index list can not contains null");
        }
        this.f2956f = list;
        return this;
    }

    public PolylineOptions visible(boolean z) {
        this.f2952b = z;
        return this;
    }

    public PolylineOptions width(int i) {
        if (i > 0) {
            this.f2958h = i;
        }
        return this;
    }

    public PolylineOptions zIndex(int i) {
        this.f2951a = i;
        return this;
    }
}