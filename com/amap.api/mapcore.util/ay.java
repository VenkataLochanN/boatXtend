package com.amap.api.mapcore.util;

import com.amap.api.maps.model.MultiPointItem;
import com.autonavi.amap.mapcore.IPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: QuadTree2.java */
/* JADX INFO: loaded from: classes.dex */
public class ay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final au f212a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f213b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f214c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<MultiPointItem> f215d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<ay> f216e;

    private int a(int i) {
        switch (i) {
            case 0:
                return 50;
            case 1:
                return 30;
            case 2:
            case 3:
                return 20;
            case 4:
            case 5:
                return 10;
            case 6:
            default:
                return 5;
        }
    }

    protected ay(au auVar) {
        this(auVar, 0);
    }

    private ay(int i, int i2, int i3, int i4, int i5) {
        this(new au(i, i2, i3, i4), i5);
    }

    private ay(au auVar, int i) {
        this.f214c = 30;
        this.f216e = null;
        this.f212a = auVar;
        this.f213b = i;
        this.f214c = a(this.f213b);
    }

    protected void a(MultiPointItem multiPointItem) {
        IPoint iPoint = multiPointItem.getIPoint();
        if (this.f212a.a(iPoint.x, iPoint.y)) {
            a(iPoint.x, iPoint.y, multiPointItem);
        }
    }

    private void a(int i, int i2, MultiPointItem multiPointItem) {
        if (this.f215d == null) {
            this.f215d = new ArrayList();
        }
        if (this.f215d.size() > this.f214c && this.f213b < 40) {
            if (this.f216e == null) {
                b();
            }
            if (this.f216e != null) {
                if (i2 < this.f212a.f196f) {
                    if (i < this.f212a.f195e) {
                        this.f216e.get(0).a(i, i2, multiPointItem);
                        return;
                    } else {
                        this.f216e.get(1).a(i, i2, multiPointItem);
                        return;
                    }
                }
                if (i < this.f212a.f195e) {
                    this.f216e.get(2).a(i, i2, multiPointItem);
                    return;
                } else {
                    this.f216e.get(3).a(i, i2, multiPointItem);
                    return;
                }
            }
            return;
        }
        this.f215d.add(multiPointItem);
    }

    private void b() {
        this.f216e = new ArrayList(4);
        this.f216e.add(new ay(this.f212a.f191a, this.f212a.f195e, this.f212a.f192b, this.f212a.f196f, this.f213b + 1));
        this.f216e.add(new ay(this.f212a.f195e, this.f212a.f193c, this.f212a.f192b, this.f212a.f196f, this.f213b + 1));
        this.f216e.add(new ay(this.f212a.f191a, this.f212a.f195e, this.f212a.f196f, this.f212a.f194d, this.f213b + 1));
        this.f216e.add(new ay(this.f212a.f195e, this.f212a.f193c, this.f212a.f196f, this.f212a.f194d, this.f213b + 1));
    }

    protected void a() {
        this.f216e = null;
        List<MultiPointItem> list = this.f215d;
        if (list != null) {
            list.clear();
        }
    }

    protected void a(au auVar, Collection<MultiPointItem> collection, double d2) {
        a(auVar, collection, 1.0f, d2);
    }

    private void a(au auVar, Collection<MultiPointItem> collection, float f2, double d2) {
        if (this.f212a.a(auVar)) {
            if (this.f215d != null) {
                int size = (int) (r0.size() * f2);
                for (int i = 0; i < size; i++) {
                    MultiPointItem multiPointItem = this.f215d.get(i);
                    if (auVar.a(multiPointItem.getIPoint())) {
                        collection.add(multiPointItem);
                    }
                }
            }
            if (d2 > 0.0d) {
                double d3 = ((((double) this.f212a.f194d) - ((double) this.f212a.f192b)) * (((double) this.f212a.f193c) - ((double) this.f212a.f191a))) / d2;
                if (d3 < 0.7f) {
                    return;
                } else {
                    f2 = d3 > 1.0d ? 1.0f : (float) ((((4.8188d * d3) * d3) - (d3 * 4.9339d)) + 1.1093d);
                }
            }
            List<ay> list = this.f216e;
            if (list != null) {
                Iterator<ay> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(auVar, collection, f2, d2);
                }
            }
        }
    }
}