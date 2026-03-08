package com.amap.api.maps.model;

import com.amap.api.mapcore.util.du;
import com.autonavi.amap.mapcore.DPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: PointQuadTree.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final du f1872a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f1873b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<WeightedLatLng> f1874c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<a> f1875d;

    protected a(du duVar) {
        this(duVar, 0);
    }

    private a(double d2, double d3, double d4, double d5, int i) {
        this(new du(d2, d3, d4, d5), i);
    }

    private a(du duVar, int i) {
        this.f1875d = null;
        this.f1872a = duVar;
        this.f1873b = i;
    }

    protected void a(WeightedLatLng weightedLatLng) {
        DPoint point = weightedLatLng.getPoint();
        if (this.f1872a.a(point.x, point.y)) {
            a(point.x, point.y, weightedLatLng);
        }
    }

    private void a(double d2, double d3, WeightedLatLng weightedLatLng) {
        if (this.f1875d == null) {
            if (this.f1874c == null) {
                this.f1874c = new ArrayList();
            }
            this.f1874c.add(weightedLatLng);
            if (this.f1874c.size() <= 50 || this.f1873b >= 40) {
                return;
            }
            a();
            return;
        }
        if (d3 < this.f1872a.f682f) {
            if (d2 < this.f1872a.f681e) {
                this.f1875d.get(0).a(d2, d3, weightedLatLng);
                return;
            } else {
                this.f1875d.get(1).a(d2, d3, weightedLatLng);
                return;
            }
        }
        if (d2 < this.f1872a.f681e) {
            this.f1875d.get(2).a(d2, d3, weightedLatLng);
        } else {
            this.f1875d.get(3).a(d2, d3, weightedLatLng);
        }
    }

    private void a() {
        this.f1875d = new ArrayList(4);
        this.f1875d.add(new a(this.f1872a.f677a, this.f1872a.f681e, this.f1872a.f678b, this.f1872a.f682f, this.f1873b + 1));
        this.f1875d.add(new a(this.f1872a.f681e, this.f1872a.f679c, this.f1872a.f678b, this.f1872a.f682f, this.f1873b + 1));
        this.f1875d.add(new a(this.f1872a.f677a, this.f1872a.f681e, this.f1872a.f682f, this.f1872a.f680d, this.f1873b + 1));
        this.f1875d.add(new a(this.f1872a.f681e, this.f1872a.f679c, this.f1872a.f682f, this.f1872a.f680d, this.f1873b + 1));
        List<WeightedLatLng> list = this.f1874c;
        this.f1874c = null;
        for (WeightedLatLng weightedLatLng : list) {
            a(weightedLatLng.getPoint().x, weightedLatLng.getPoint().y, weightedLatLng);
        }
    }

    protected Collection<WeightedLatLng> a(du duVar) {
        ArrayList arrayList = new ArrayList();
        a(duVar, arrayList);
        return arrayList;
    }

    private void a(du duVar, Collection<WeightedLatLng> collection) {
        if (this.f1872a.a(duVar)) {
            List<a> list = this.f1875d;
            if (list != null) {
                Iterator<a> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(duVar, collection);
                }
            } else if (this.f1874c != null) {
                if (duVar.b(this.f1872a)) {
                    collection.addAll(this.f1874c);
                    return;
                }
                for (WeightedLatLng weightedLatLng : this.f1874c) {
                    if (duVar.a(weightedLatLng.getPoint())) {
                        collection.add(weightedLatLng);
                    }
                }
            }
        }
    }
}