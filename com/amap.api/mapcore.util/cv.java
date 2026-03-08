package com.amap.api.mapcore.util;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: compiled from: MyLocationOverlay.java */
/* JADX INFO: loaded from: classes.dex */
public class cv {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ValueAnimator f473b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private IAMapDelegate f476e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Marker f477f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Circle f478g;
    private LatLng i;
    private double j;
    private Context k;
    private aa l;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private MyLocationStyle f479h = new MyLocationStyle();
    private int m = 4;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a f472a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Animator.AnimatorListener f474c = new Animator.AnimatorListener() { // from class: com.amap.api.mapcore.util.cv.1
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            cv.this.j();
        }
    };

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ValueAnimator.AnimatorUpdateListener f475d = new ValueAnimator.AnimatorUpdateListener() { // from class: com.amap.api.mapcore.util.cv.2
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            try {
                if (cv.this.f478g != null) {
                    LatLng latLng = (LatLng) valueAnimator.getAnimatedValue();
                    cv.this.f478g.setCenter(latLng);
                    cv.this.f477f.setPosition(latLng);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };

    public cv(IAMapDelegate iAMapDelegate, Context context) {
        this.k = context.getApplicationContext();
        this.f476e = iAMapDelegate;
        this.l = new aa(this.k, iAMapDelegate);
        a(4, true);
    }

    public void a(MyLocationStyle myLocationStyle) {
        try {
            this.f479h = myLocationStyle;
            a(this.f479h.isMyLocationShowing());
            if (!this.f479h.isMyLocationShowing()) {
                this.l.a(false);
                this.m = this.f479h.getMyLocationType();
                return;
            }
            k();
            if (this.f477f == null && this.f478g == null) {
                return;
            }
            this.l.a(this.f477f);
            a(this.f479h.getMyLocationType());
        } catch (Throwable th) {
            hn.c(th, "MyLocationOverlay", "setMyLocationStyle");
            th.printStackTrace();
        }
    }

    public MyLocationStyle a() {
        return this.f479h;
    }

    public void a(int i) {
        a(i, false);
    }

    private void a(int i, boolean z) {
        this.m = i;
        this.n = false;
        this.p = false;
        this.o = false;
        this.r = false;
        this.s = false;
        switch (this.m) {
            case 1:
                this.o = true;
                this.p = true;
                this.q = true;
                break;
            case 2:
                this.o = true;
                this.q = true;
                break;
            case 3:
                this.o = true;
                this.s = true;
                break;
            case 4:
                this.o = true;
                this.r = true;
                this.q = false;
                break;
            case 5:
                this.r = true;
                this.q = false;
                break;
            case 7:
                this.s = true;
                break;
        }
        if (this.r || this.s) {
            if (this.s) {
                this.l.a(true);
                if (!z) {
                    try {
                        this.f476e.moveCamera(ah.a(17.0f));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                b(45.0f);
            } else {
                this.l.a(false);
            }
            this.l.a();
            Marker marker = this.f477f;
            if (marker != null) {
                marker.setFlat(true);
                return;
            }
            return;
        }
        Marker marker2 = this.f477f;
        if (marker2 != null) {
            marker2.setFlat(false);
        }
        i();
        h();
        g();
    }

    public void b() {
        aa aaVar;
        if (this.m != 3 || (aaVar = this.l) == null) {
            return;
        }
        aaVar.a();
    }

    private void g() {
        this.l.b();
    }

    private void h() {
        b(0.0f);
    }

    private void i() {
        c(0.0f);
    }

    private void b(float f2) {
        IAMapDelegate iAMapDelegate = this.f476e;
        if (iAMapDelegate == null) {
            return;
        }
        try {
            iAMapDelegate.moveCamera(ah.c(f2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(float f2) {
        IAMapDelegate iAMapDelegate = this.f476e;
        if (iAMapDelegate == null) {
            return;
        }
        try {
            iAMapDelegate.moveCamera(ah.d(f2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(Location location) {
        if (location == null) {
            return;
        }
        a(this.f479h.isMyLocationShowing());
        if (this.f479h.isMyLocationShowing()) {
            this.i = new LatLng(location.getLatitude(), location.getLongitude());
            this.j = location.getAccuracy();
            if (this.f477f == null && this.f478g == null) {
                k();
            }
            Circle circle = this.f478g;
            if (circle != null) {
                try {
                    if (this.j != -1.0d) {
                        circle.setRadius(this.j);
                    }
                } catch (Throwable th) {
                    hn.c(th, "MyLocationOverlay", "setCentAndRadius");
                    th.printStackTrace();
                }
            }
            d(location.getBearing());
            if (!this.i.equals(this.f477f.getPosition())) {
                a(this.i);
            } else {
                j();
            }
        }
    }

    private void d(float f2) {
        if (this.q) {
            float f3 = f2 % 360.0f;
            if (f3 > 180.0f) {
                f3 -= 360.0f;
            } else if (f3 < -180.0f) {
                f3 += 360.0f;
            }
            Marker marker = this.f477f;
            if (marker != null) {
                marker.setRotateAngle(-f3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.i != null && this.o) {
            if (this.p && this.n) {
                return;
            }
            this.n = true;
            try {
                IPoint iPointObtain = IPoint.obtain();
                GLMapState.lonlat2Geo(this.i.longitude, this.i.latitude, iPointObtain);
                this.f476e.animateCamera(ah.a(iPointObtain));
            } catch (Throwable th) {
                hn.c(th, "MyLocationOverlay", "moveMapToLocation");
                th.printStackTrace();
            }
        }
    }

    private void k() {
        MyLocationStyle myLocationStyle = this.f479h;
        if (myLocationStyle == null) {
            this.f479h = new MyLocationStyle();
            this.f479h.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            m();
        } else {
            if (myLocationStyle.getMyLocationIcon() == null || this.f479h.getMyLocationIcon().getBitmap() == null) {
                this.f479h.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            }
            m();
        }
    }

    public void c() throws RemoteException {
        l();
        if (this.l != null) {
            g();
            this.l = null;
        }
    }

    private void l() {
        Circle circle = this.f478g;
        if (circle != null) {
            try {
                this.f476e.removeGLOverlay(circle.getId());
            } catch (Throwable th) {
                hn.c(th, "MyLocationOverlay", "locationIconRemove");
                th.printStackTrace();
            }
            this.f478g = null;
        }
        Marker marker = this.f477f;
        if (marker != null) {
            marker.remove();
            this.f477f = null;
            this.l.a((Marker) null);
        }
    }

    public void a(boolean z) {
        Circle circle = this.f478g;
        if (circle != null && circle.isVisible() != z) {
            this.f478g.setVisible(z);
        }
        Marker marker = this.f477f;
        if (marker == null || marker.isVisible() == z) {
            return;
        }
        this.f477f.setVisible(z);
    }

    private void m() {
        try {
            if (this.f478g == null) {
                this.f478g = this.f476e.addCircle(new CircleOptions().zIndex(1.0f));
            }
            if (this.f478g != null) {
                if (this.f478g.getStrokeWidth() != this.f479h.getStrokeWidth()) {
                    this.f478g.setStrokeWidth(this.f479h.getStrokeWidth());
                }
                if (this.f478g.getFillColor() != this.f479h.getRadiusFillColor()) {
                    this.f478g.setFillColor(this.f479h.getRadiusFillColor());
                }
                if (this.f478g.getStrokeColor() != this.f479h.getStrokeColor()) {
                    this.f478g.setStrokeColor(this.f479h.getStrokeColor());
                }
                if (this.i != null) {
                    this.f478g.setCenter(this.i);
                }
                this.f478g.setRadius(this.j);
                this.f478g.setVisible(true);
            }
            if (this.f477f == null) {
                this.f477f = this.f476e.addMarker(new MarkerOptions().visible(false));
            }
            if (this.f477f != null) {
                if (this.f477f.getAnchorU() != this.f479h.getAnchorU() || this.f477f.getAnchorV() != this.f479h.getAnchorV()) {
                    this.f477f.setAnchor(this.f479h.getAnchorU(), this.f479h.getAnchorV());
                }
                if (this.f477f.getIcons() == null || this.f477f.getIcons().size() == 0) {
                    this.f477f.setIcon(this.f479h.getMyLocationIcon());
                } else if (this.f479h.getMyLocationIcon() != null && !this.f477f.getIcons().get(0).equals(this.f479h.getMyLocationIcon())) {
                    this.f477f.setIcon(this.f479h.getMyLocationIcon());
                }
                if (this.i != null) {
                    this.f477f.setPosition(this.i);
                    this.f477f.setVisible(true);
                }
            }
            j();
            this.l.a(this.f477f);
        } catch (Throwable th) {
            hn.c(th, "MyLocationOverlay", "myLocStyle");
            th.printStackTrace();
        }
    }

    public void a(float f2) {
        Marker marker = this.f477f;
        if (marker != null) {
            marker.setRotateAngle(f2);
        }
    }

    public String d() {
        Marker marker = this.f477f;
        if (marker != null) {
            return marker.getId();
        }
        return null;
    }

    public String e() throws RemoteException {
        Circle circle = this.f478g;
        if (circle != null) {
            return circle.getId();
        }
        return null;
    }

    public void f() {
        this.f478g = null;
        this.f477f = null;
    }

    private void a(LatLng latLng) {
        LatLng position = this.f477f.getPosition();
        if (position == null) {
            position = new LatLng(0.0d, 0.0d);
        }
        if (this.f472a == null) {
            this.f472a = new a();
        }
        ValueAnimator valueAnimator = this.f473b;
        if (valueAnimator == null) {
            this.f473b = ValueAnimator.ofObject(new a(), position, latLng);
            this.f473b.addListener(this.f474c);
            this.f473b.addUpdateListener(this.f475d);
        } else {
            valueAnimator.setObjectValues(position, latLng);
            this.f473b.setEvaluator(this.f472a);
        }
        if (position.latitude == 0.0d && position.longitude == 0.0d) {
            this.f473b.setDuration(1L);
        } else {
            this.f473b.setDuration(1000L);
        }
        this.f473b.start();
    }

    /* JADX INFO: compiled from: MyLocationOverlay.java */
    public static class a implements TypeEvaluator {
        @Override // android.animation.TypeEvaluator
        public Object evaluate(float f2, Object obj, Object obj2) {
            LatLng latLng = (LatLng) obj;
            LatLng latLng2 = (LatLng) obj2;
            double d2 = f2;
            return new LatLng(latLng.latitude + ((latLng2.latitude - latLng.latitude) * d2), latLng.longitude + (d2 * (latLng2.longitude - latLng.longitude)));
        }
    }
}