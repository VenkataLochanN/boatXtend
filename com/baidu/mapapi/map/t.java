package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.ab;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
class t implements com.baidu.mapsdkplatform.comapi.map.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ TextureMapView f3068a;

    t(TextureMapView textureMapView) {
        this.f3068a = textureMapView;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a() {
        String str;
        if (this.f3068a.f2992b == null || this.f3068a.f2992b.b() == null) {
            return;
        }
        float f2 = this.f3068a.f2992b.b().E().f3518a;
        if (f2 < this.f3068a.f2992b.b().f3562b) {
            f2 = this.f3068a.f2992b.b().f3562b;
        } else if (f2 > this.f3068a.f2992b.b().f3561a) {
            f2 = this.f3068a.f2992b.b().f3561a;
        }
        if (Math.abs(this.f3068a.r - f2) > 0.0f) {
            int iIntValue = ((Integer) TextureMapView.q.get(Math.round(f2))).intValue();
            int i = ((int) (((double) iIntValue) / this.f3068a.f2992b.b().E().m)) / 2;
            this.f3068a.o.setPadding(i, 0, i, 0);
            Object[] objArr = new Object[1];
            if (iIntValue >= 1000) {
                objArr[0] = Integer.valueOf(iIntValue / 1000);
                str = String.format(" %d公里 ", objArr);
            } else {
                objArr[0] = Integer.valueOf(iIntValue);
                str = String.format(" %d米 ", objArr);
            }
            this.f3068a.m.setText(str);
            this.f3068a.n.setText(str);
            this.f3068a.r = f2;
        }
        this.f3068a.b();
        this.f3068a.requestLayout();
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