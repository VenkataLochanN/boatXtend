package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.ab;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
class l implements com.baidu.mapsdkplatform.comapi.map.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MapView f3050a;

    l(MapView mapView) {
        this.f3050a = mapView;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a() {
        String str;
        if (this.f3050a.f2892e == null || this.f3050a.f2892e.a() == null) {
            return;
        }
        float f2 = this.f3050a.f2892e.a().E().f3518a;
        if (f2 < this.f3050a.f2892e.a().f3562b) {
            f2 = this.f3050a.f2892e.a().f3562b;
        } else if (f2 > this.f3050a.f2892e.a().f3561a) {
            f2 = this.f3050a.f2892e.a().f3561a;
        }
        if (Math.abs(this.f3050a.u - f2) > 0.0f) {
            int iIntValue = ((Integer) MapView.q.get(Math.round(f2))).intValue();
            int i = ((int) (((double) iIntValue) / this.f3050a.f2892e.a().E().m)) / 2;
            this.f3050a.o.setPadding(i, 0, i, 0);
            Object[] objArr = new Object[1];
            if (iIntValue >= 1000) {
                objArr[0] = Integer.valueOf(iIntValue / 1000);
                str = String.format(" %d公里 ", objArr);
            } else {
                objArr[0] = Integer.valueOf(iIntValue);
                str = String.format(" %d米 ", objArr);
            }
            this.f3050a.m.setText(str);
            this.f3050a.n.setText(str);
            this.f3050a.u = f2;
        }
        this.f3050a.b();
        this.f3050a.requestLayout();
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(Bitmap bitmap) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(MotionEvent motionEvent) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(GeoPoint geoPoint) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(ab abVar) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(String str) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(GL10 gl10, ab abVar) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(boolean z) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(boolean z, int i) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void b() {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void b(GeoPoint geoPoint) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void b(ab abVar) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public boolean b(String str) {
        return false;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void c() {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void c(GeoPoint geoPoint) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void c(ab abVar) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void d() {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void d(GeoPoint geoPoint) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void e() {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void e(GeoPoint geoPoint) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void f() {
    }
}