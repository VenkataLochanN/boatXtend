package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.map.o.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class o<T extends a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f3053a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3054b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<T> f3055c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<o<T>> f3056d;

    static abstract class a {
        a() {
        }

        abstract Point a();
    }

    private o(double d2, double d3, double d4, double d5, int i) {
        this(new h(d2, d3, d4, d5), i);
    }

    public o(h hVar) {
        this(hVar, 0);
    }

    private o(h hVar, int i) {
        this.f3056d = null;
        this.f3053a = hVar;
        this.f3054b = i;
    }

    private void a() {
        this.f3056d = new ArrayList(4);
        this.f3056d.add(new o<>(this.f3053a.f3040a, this.f3053a.f3044e, this.f3053a.f3041b, this.f3053a.f3045f, this.f3054b + 1));
        this.f3056d.add(new o<>(this.f3053a.f3044e, this.f3053a.f3042c, this.f3053a.f3041b, this.f3053a.f3045f, this.f3054b + 1));
        this.f3056d.add(new o<>(this.f3053a.f3040a, this.f3053a.f3044e, this.f3053a.f3045f, this.f3053a.f3043d, this.f3054b + 1));
        this.f3056d.add(new o<>(this.f3053a.f3044e, this.f3053a.f3042c, this.f3053a.f3045f, this.f3053a.f3043d, this.f3054b + 1));
        List<T> list = this.f3055c;
        this.f3055c = null;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            a(r7.a().x, r7.a().y, it.next());
        }
    }

    private void a(double d2, double d3, T t) {
        List<o<T>> list;
        int i;
        if (this.f3056d == null) {
            if (this.f3055c == null) {
                this.f3055c = new ArrayList();
            }
            this.f3055c.add(t);
            if (this.f3055c.size() <= 40 || this.f3054b >= 40) {
                return;
            }
            a();
            return;
        }
        if (d3 < this.f3053a.f3045f) {
            if (d2 < this.f3053a.f3044e) {
                list = this.f3056d;
                i = 0;
            } else {
                list = this.f3056d;
                i = 1;
            }
        } else if (d2 < this.f3053a.f3044e) {
            list = this.f3056d;
            i = 2;
        } else {
            list = this.f3056d;
            i = 3;
        }
        list.get(i).a(d2, d3, t);
    }

    private void a(h hVar, Collection<T> collection) {
        if (this.f3053a.a(hVar)) {
            List<o<T>> list = this.f3056d;
            if (list != null) {
                Iterator<o<T>> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(hVar, collection);
                }
            } else if (this.f3055c != null) {
                if (hVar.b(this.f3053a)) {
                    collection.addAll(this.f3055c);
                    return;
                }
                for (T t : this.f3055c) {
                    if (hVar.a(t.a())) {
                        collection.add(t);
                    }
                }
            }
        }
    }

    public Collection<T> a(h hVar) {
        ArrayList arrayList = new ArrayList();
        a(hVar, arrayList);
        return arrayList;
    }

    public void a(T t) {
        Point pointA = t.a();
        if (this.f3053a.a(pointA.x, pointA.y)) {
            a(pointA.x, pointA.y, t);
        }
    }
}