package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.ab;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
class x implements com.baidu.mapsdkplatform.comapi.map.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WearMapView f3076a;

    x(WearMapView wearMapView) {
        this.f3076a = wearMapView;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a() {
        String str;
        if (this.f3076a.f3023f == null || this.f3076a.f3023f.a() == null) {
            return;
        }
        float f2 = this.f3076a.f3023f.a().E().f3518a;
        if (this.f3076a.A != f2) {
            int iIntValue = ((Integer) WearMapView.x.get((int) f2)).intValue();
            int i = ((int) (((double) iIntValue) / this.f3076a.f3023f.a().E().m)) / 2;
            this.f3076a.r.setPadding(i, 0, i, 0);
            Object[] objArr = new Object[1];
            if (iIntValue >= 1000) {
                objArr[0] = Integer.valueOf(iIntValue / 1000);
                str = String.format(" %d公里 ", objArr);
            } else {
                objArr[0] = Integer.valueOf(iIntValue);
                str = String.format(" %d米 ", objArr);
            }
            this.f3076a.p.setText(str);
            this.f3076a.q.setText(str);
            this.f3076a.A = f2;
        }
        this.f3076a.requestLayout();
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