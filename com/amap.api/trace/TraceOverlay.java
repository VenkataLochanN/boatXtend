package com.amap.api.trace;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TraceOverlay {
    public static final int TRACE_STATUS_FAILURE = 3;
    public static final int TRACE_STATUS_FINISH = 2;
    public static final int TRACE_STATUS_PREPARE = 4;
    public static final int TRACE_STATUS_PROCESSING = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Polyline f1959a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PolylineOptions f1960b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AMap f1961c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<LatLng> f1962d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1963e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1964f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1965g;

    public TraceOverlay(AMap aMap, List<LatLng> list) {
        this.f1962d = new ArrayList();
        this.f1963e = 4;
        this.f1961c = aMap;
        a();
        this.f1962d = list;
        this.f1960b.addAll(list);
        this.f1959a = aMap.addPolyline(this.f1960b);
    }

    public TraceOverlay(AMap aMap) {
        this.f1962d = new ArrayList();
        this.f1963e = 4;
        this.f1961c = aMap;
        a();
    }

    public void add(List<LatLng> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.f1962d.addAll(list);
        a();
        if (this.f1959a == null) {
            this.f1959a = this.f1961c.addPolyline(this.f1960b);
        }
        Polyline polyline = this.f1959a;
        if (polyline != null) {
            polyline.setPoints(this.f1962d);
        }
    }

    public void remove() {
        Polyline polyline = this.f1959a;
        if (polyline != null) {
            polyline.remove();
        }
    }

    public void setProperCamera(List<LatLng> list) {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (list == null || list.size() == 0) {
            return;
        }
        Iterator<LatLng> it = list.iterator();
        while (it.hasNext()) {
            builder.include(it.next());
        }
        try {
            this.f1961c.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 20));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void zoopToSpan() {
        setProperCamera(this.f1960b.getPoints());
    }

    private PolylineOptions a() {
        if (this.f1960b == null) {
            this.f1960b = new PolylineOptions();
            this.f1960b.setCustomTexture(BitmapDescriptorFactory.fromAsset("tracelinetexture.png"));
            this.f1960b.width(40.0f);
        }
        return this.f1960b;
    }

    public int getTraceStatus() {
        return this.f1963e;
    }

    public void setTraceStatus(int i) {
        this.f1963e = i;
    }

    public int getDistance() {
        return this.f1964f;
    }

    public void setDistance(int i) {
        this.f1964f = i;
    }

    public int getWaitTime() {
        return this.f1965g;
    }

    public void setWaitTime(int i) {
        this.f1965g = i;
    }
}