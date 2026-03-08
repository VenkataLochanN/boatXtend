package com.baidu.mapsdkplatform.comapi.synchronization.render;

import android.content.Context;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3790a = e.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private f f3791b = f.a();

    public e(Context context, BaiduMap baiduMap) {
        this.f3791b.a(baiduMap);
    }

    public void a() {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.e();
        }
    }

    public void a(int i) {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.a(i);
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.a(i, i2, i3, i4);
        }
    }

    public void a(d dVar) {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.a(dVar);
        }
    }

    public void b() {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.f();
        }
    }

    public void b(int i) {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.d(i);
        }
    }

    public Marker c() {
        f fVar = this.f3791b;
        if (fVar != null) {
            return fVar.b();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3790a, "The mSyncRenderStrategic created failed");
        return null;
    }

    public void c(int i) {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.b(i);
        }
    }

    public Marker d() {
        f fVar = this.f3791b;
        if (fVar != null) {
            return fVar.c();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3790a, "The mSyncRenderStrategic created failed");
        return null;
    }

    public void d(int i) {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.c(i);
        }
    }

    public Marker e() {
        f fVar = this.f3791b;
        if (fVar != null) {
            return fVar.d();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3790a, "The mSyncRenderStrategic created failed");
        return null;
    }

    public void f() {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.g();
            this.f3791b = null;
        }
    }

    public void g() {
        f fVar = this.f3791b;
        if (fVar != null) {
            fVar.h();
        }
    }
}