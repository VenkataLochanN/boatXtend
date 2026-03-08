package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.amap.api.mapcore.util.fh;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.Marker;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.ae.gmap.listener.AMapWidgetListener;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;
import com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;

/* JADX INFO: compiled from: MapOverlayViewGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class fj extends ViewGroup implements IInfoWindowAction {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    fk f868a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ar f869b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IAMapDelegate f870c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f871d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private fm f872e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private fi f873f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private fg f874g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private fl f875h;
    private ff i;
    private fh j;
    private fn k;
    private View l;
    private BaseOverlayImp m;
    private Drawable n;
    private boolean o;
    private View p;
    private boolean q;
    private boolean r;
    private boolean s;

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public boolean isInfoWindowShown() {
        return false;
    }

    public void j() {
    }

    public fj(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.n = null;
        int i = 1;
        this.o = true;
        this.r = true;
        this.s = true;
        try {
            this.f870c = iAMapDelegate;
            this.f871d = context;
            this.f868a = new fk();
            this.i = new ff(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            if (this.f870c.getGLMapView() != null) {
                addView(this.f870c.getGLMapView(), 0, layoutParams);
            } else {
                i = 0;
            }
            addView(this.i, i, layoutParams);
            if (this.r) {
                return;
            }
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    private void a(Context context) {
        this.f872e = new fm(context, this.f870c);
        this.f872e.c(this.s);
        this.f875h = new fl(context, this.f870c);
        this.j = new fh(context);
        this.k = new fn(context, this.f870c);
        this.f873f = new fi(context, this.f870c);
        this.f874g = new fg(context, this.f870c);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        addView(this.f872e, layoutParams);
        addView(this.f875h, layoutParams);
        addView(this.j, new ViewGroup.LayoutParams(-2, -2));
        addView(this.k, new a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        addView(this.f873f, new a(-2, -2, FPoint.obtain(0.0f, 0.0f), 0, 0, 83));
        addView(this.f874g, new a(-2, -2, FPoint.obtain(0.0f, 0.0f), 0, 0, 51));
        this.f874g.setVisibility(8);
        this.f870c.setMapWidgetListener(new AMapWidgetListener() { // from class: com.amap.api.mapcore.util.fj.1
            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public void setFrontViewVisibility(boolean z) {
            }

            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public void invalidateScaleView() {
                if (fj.this.f875h == null) {
                    return;
                }
                fj.this.f875h.post(new Runnable() { // from class: com.amap.api.mapcore.util.fj.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        fj.this.f875h.b();
                    }
                });
            }

            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public void invalidateCompassView() {
                if (fj.this.f874g == null) {
                    return;
                }
                fj.this.f874g.post(new Runnable() { // from class: com.amap.api.mapcore.util.fj.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        fj.this.f874g.b();
                    }
                });
            }

            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public void invalidateZoomController(final float f2) {
                if (fj.this.k == null) {
                    return;
                }
                fj.this.k.post(new Runnable() { // from class: com.amap.api.mapcore.util.fj.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        fj.this.k.a(f2);
                    }
                });
            }
        });
        try {
            if (this.f870c.getUiSettings().isMyLocationButtonEnabled()) {
                return;
            }
            this.f873f.setVisibility(8);
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImpGLSurfaceView", "locationView gone");
            th.printStackTrace();
        }
    }

    public void a(Boolean bool) {
        fh fhVar = this.j;
        if (fhVar == null) {
            this.f868a.a(this, bool);
        } else if (fhVar != null && bool.booleanValue() && this.f870c.canShowIndoorSwitch()) {
            this.j.a(true);
        }
    }

    public void b(Boolean bool) {
        fn fnVar = this.k;
        if (fnVar == null) {
            this.f868a.a(this, bool);
        } else {
            fnVar.a(bool.booleanValue());
        }
    }

    public void c(Boolean bool) {
        if (this.f873f == null) {
            this.f868a.a(this, bool);
        } else if (bool.booleanValue()) {
            this.f873f.setVisibility(0);
        } else {
            this.f873f.setVisibility(8);
        }
    }

    public void d(Boolean bool) {
        fg fgVar = this.f874g;
        if (fgVar == null) {
            this.f868a.a(this, bool);
        } else {
            fgVar.a(bool.booleanValue());
        }
    }

    public void e(Boolean bool) {
        fl flVar = this.f875h;
        if (flVar == null) {
            this.f868a.a(this, bool);
        } else {
            flVar.a(bool.booleanValue());
        }
    }

    public void f(Boolean bool) {
        fm fmVar = this.f872e;
        if (fmVar == null) {
            this.f868a.a(this, bool);
        } else {
            fmVar.setVisibility(bool.booleanValue() ? 0 : 8);
        }
    }

    public void a(String str, Boolean bool, Integer num) {
        if (this.f872e == null) {
            this.f868a.a(this, str, bool, num);
            return;
        }
        if (num.intValue() == 2) {
            this.f872e.b(bool.booleanValue());
        } else {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.f872e.a(str, num.intValue());
            this.f872e.d(bool.booleanValue());
        }
    }

    public void a(Float f2) {
        fn fnVar = this.k;
        if (fnVar == null) {
            this.f868a.a(this, f2);
        } else if (fnVar != null) {
            fnVar.a(f2.floatValue());
        }
    }

    public void a(Integer num) {
        fn fnVar = this.k;
        if (fnVar == null) {
            this.f868a.a(this, num);
        } else if (fnVar != null) {
            fnVar.a(num.intValue());
        }
    }

    public void b(Integer num) {
        fm fmVar = this.f872e;
        if (fmVar == null) {
            this.f868a.a(this, num);
        } else if (fmVar != null) {
            fmVar.a(num.intValue());
            this.f872e.postInvalidate();
            m();
        }
    }

    private void m() {
        fl flVar = this.f875h;
        if (flVar == null) {
            this.f868a.a(this, new Object[0]);
        } else {
            if (flVar == null || flVar.getVisibility() != 0) {
                return;
            }
            this.f875h.postInvalidate();
        }
    }

    public void c(Integer num) {
        fm fmVar = this.f872e;
        if (fmVar == null) {
            this.f868a.a(this, num);
        } else if (fmVar != null) {
            fmVar.b(num.intValue());
            m();
        }
    }

    public void d(Integer num) {
        fm fmVar = this.f872e;
        if (fmVar == null) {
            this.f868a.a(this, num);
        } else if (fmVar != null) {
            fmVar.c(num.intValue());
            m();
        }
    }

    public float a(int i) {
        if (this.f872e == null) {
            return 0.0f;
        }
        m();
        return this.f872e.d(i);
    }

    public void a(Integer num, Float f2) {
        fm fmVar = this.f872e;
        if (fmVar != null) {
            this.f868a.a(this, num, f2);
        } else if (fmVar != null) {
            fmVar.a(num.intValue(), f2.floatValue());
            m();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public void setInfoWindowAdapterManager(ar arVar) {
        this.f869b = arVar;
    }

    public Point a() {
        fm fmVar = this.f872e;
        if (fmVar == null) {
            return null;
        }
        return fmVar.c();
    }

    public void g(Boolean bool) {
        fm fmVar = this.f872e;
        if (fmVar == null) {
            this.f868a.a(this, bool);
            return;
        }
        if (fmVar != null && bool.booleanValue()) {
            this.f872e.a(true);
            return;
        }
        fm fmVar2 = this.f872e;
        if (fmVar2 != null) {
            fmVar2.a(false);
        }
    }

    public boolean b() {
        fm fmVar = this.f872e;
        if (fmVar != null) {
            return fmVar.e();
        }
        return false;
    }

    public void c() {
        fm fmVar = this.f872e;
        if (fmVar == null) {
            this.f868a.a(this, new Object[0]);
        } else if (fmVar != null) {
            fmVar.d();
        }
    }

    public ff d() {
        return this.i;
    }

    public fh e() {
        return this.j;
    }

    public fi f() {
        return this.f873f;
    }

    public fm g() {
        return this.f872e;
    }

    public void a(CameraPosition cameraPosition) {
        if (this.f872e == null) {
            this.f868a.a(this, cameraPosition);
            return;
        }
        if (this.f870c.getUiSettings().isLogoEnable()) {
            if (MapsInitializer.isLoadWorldGridMap() && cameraPosition.zoom >= 6.0f && !ek.a(cameraPosition.target.latitude, cameraPosition.target.longitude)) {
                this.f872e.setVisibility(8);
            } else if (this.f870c.getMaskLayerType() == -1) {
                this.f872e.setVisibility(0);
            }
        }
    }

    public void h() {
        fn fnVar = this.k;
        if (fnVar != null) {
            fnVar.a();
        }
        fl flVar = this.f875h;
        if (flVar != null) {
            flVar.a();
        }
        fm fmVar = this.f872e;
        if (fmVar != null) {
            fmVar.a();
        }
        fi fiVar = this.f873f;
        if (fiVar != null) {
            fiVar.a();
        }
        fg fgVar = this.f874g;
        if (fgVar != null) {
            fgVar.a();
        }
        fh fhVar = this.j;
        if (fhVar != null) {
            fhVar.b();
        }
    }

    public void a(boolean z) {
        fm fmVar = this.f872e;
        if (fmVar != null) {
            fmVar.c(z);
        }
        this.s = z;
    }

    /* JADX INFO: compiled from: MapOverlayViewGroup.java */
    public static class a extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public FPoint f882a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f883b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f884c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f885d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f886e;

        public a(int i, int i2, FPoint fPoint, int i3, int i4, int i5) {
            super(i, i2);
            this.f882a = null;
            this.f883b = false;
            this.f884c = 0;
            this.f885d = 0;
            this.f886e = 51;
            this.f882a = fPoint;
            this.f884c = i3;
            this.f885d = i4;
            this.f886e = i5;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != null) {
                    if (childAt.getLayoutParams() instanceof a) {
                        a(childAt, (a) childAt.getLayoutParams());
                    } else {
                        a(childAt, childAt.getLayoutParams());
                    }
                }
            }
            if (this.f872e != null) {
                this.f872e.d();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(View view, ViewGroup.LayoutParams layoutParams) {
        int[] iArr = new int[2];
        a(view, layoutParams.width, layoutParams.height, iArr);
        if (view instanceof fh) {
            a(view, iArr[0], iArr[1], 20, (this.f870c.getWaterMarkerPositon().y - 80) - iArr[1], 51);
        } else {
            a(view, iArr[0], iArr[1], 0, 0, 51);
        }
    }

    private void a(View view, a aVar) {
        int[] iArr = new int[2];
        a(view, aVar.width, aVar.height, iArr);
        if (view instanceof fn) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], getHeight(), aVar.f886e);
            return;
        }
        if (view instanceof fi) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], iArr[1], aVar.f886e);
            return;
        }
        if (view instanceof fg) {
            a(view, iArr[0], iArr[1], 0, 0, aVar.f886e);
            return;
        }
        if (aVar.f882a != null) {
            IPoint iPointObtain = IPoint.obtain();
            MapConfig mapConfig = this.f870c.getMapConfig();
            GLMapState mapProjection = this.f870c.getMapProjection();
            if (mapConfig != null && mapProjection != null) {
                FPoint fPointObtain = FPoint.obtain();
                if (aVar.f883b) {
                    fPointObtain.x = (int) aVar.f882a.x;
                    fPointObtain.y = (int) aVar.f882a.y;
                } else {
                    mapProjection.p20ToScreenPoint((int) aVar.f882a.x, (int) aVar.f882a.y, fPointObtain);
                }
                iPointObtain.x = (int) fPointObtain.x;
                iPointObtain.y = (int) fPointObtain.y;
                fPointObtain.recycle();
            }
            iPointObtain.x += aVar.f884c;
            iPointObtain.y += aVar.f885d;
            a(view, iArr[0], iArr[1], iPointObtain.x, iPointObtain.y, aVar.f886e);
            iPointObtain.recycle();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public void showInfoWindow(BaseOverlayImp baseOverlayImp) {
        if (baseOverlayImp == null) {
            return;
        }
        try {
            if (!(this.f869b != null && this.f869b.a() && baseOverlayImp.getTitle() == null && baseOverlayImp.getSnippet() == null) && baseOverlayImp.isInfoWindowEnable()) {
                if (this.m != null && !this.m.getId().equals(baseOverlayImp.getId())) {
                    hideInfoWindow();
                }
                if (this.f869b != null) {
                    this.m = baseOverlayImp;
                    baseOverlayImp.setInfoWindowShown(true);
                    this.q = true;
                }
            }
        } catch (Throwable unused) {
        }
    }

    private View a(BaseOverlayImp baseOverlayImp) throws RemoteException {
        View viewA;
        View viewA2;
        View view = null;
        if (baseOverlayImp instanceof cu) {
            Marker marker = new Marker((cu) baseOverlayImp);
            try {
                if (this.n == null) {
                    this.n = eg.a(this.f871d, "infowindow_bg.9.png");
                }
            } catch (Throwable th) {
                hn.c(th, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
            }
            try {
                if (this.q) {
                    viewA2 = this.f869b.a((BasePointOverlay) marker);
                    if (viewA2 == null) {
                        try {
                            viewA2 = this.f869b.b((BasePointOverlay) marker);
                        } catch (Throwable th2) {
                            th = th2;
                            view = viewA2;
                            hn.c(th, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                            th.printStackTrace();
                        }
                    }
                    this.p = viewA2;
                    this.q = false;
                } else {
                    viewA2 = this.p;
                }
                if (viewA2 == null) {
                    if (!this.f869b.a()) {
                        return null;
                    }
                    viewA2 = this.f869b.a((BasePointOverlay) marker);
                }
                view = viewA2;
                if (view != null && view.getBackground() == null) {
                    view.setBackground(this.n);
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            try {
                if (this.n == null) {
                    this.n = eg.a(this.f871d, "infowindow_bg.9.png");
                }
            } catch (Throwable th4) {
                hn.c(th4, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
                th4.printStackTrace();
            }
            try {
                GL3DModel gL3DModel = new GL3DModel((cr) baseOverlayImp);
                if (this.q) {
                    viewA = this.f869b.a(gL3DModel);
                    if (viewA == null) {
                        try {
                            viewA = this.f869b.b(gL3DModel);
                        } catch (Throwable th5) {
                            th = th5;
                            view = viewA;
                            hn.c(th, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                            th.printStackTrace();
                            return view;
                        }
                    }
                    this.p = viewA;
                    this.q = false;
                } else {
                    viewA = this.p;
                }
                if (viewA == null) {
                    if (!this.f869b.a()) {
                        return null;
                    }
                    viewA = this.f869b.a(gL3DModel);
                }
                view = viewA;
                if (view.getBackground() == null) {
                    view.setBackground(this.n);
                }
                return view;
            } catch (Throwable th6) {
                th = th6;
            }
        }
        return view;
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public void redrawInfoWindow() {
        try {
            if (this.m != null && this.m.checkInBounds()) {
                if (this.o) {
                    int realInfoWindowOffsetX = this.m.getRealInfoWindowOffsetX() + this.m.getInfoWindowOffsetX();
                    int realInfoWindowOffsetY = this.m.getRealInfoWindowOffsetY() + this.m.getInfoWindowOffsetY() + 2;
                    View viewA = a(this.m);
                    if (viewA == null) {
                        return;
                    }
                    a(viewA, realInfoWindowOffsetX, realInfoWindowOffsetY);
                    if (this.l != null) {
                        a aVar = (a) this.l.getLayoutParams();
                        if (aVar != null) {
                            aVar.f883b = this.m.isViewMode();
                            if (aVar.f883b) {
                                aVar.f882a = FPoint.obtain(this.m.getScreenPosition().x, this.m.getScreenPosition().y);
                            } else {
                                aVar.f882a = FPoint.obtain(this.m.getGeoPosition().x, this.m.getGeoPosition().y);
                            }
                            aVar.f884c = realInfoWindowOffsetX;
                            aVar.f885d = realInfoWindowOffsetY;
                        }
                        onLayout(false, 0, 0, 0, 0);
                        if (this.f869b.a()) {
                            this.f869b.a(this.m.getTitle(), this.m.getSnippet());
                        }
                        if (this.l.getVisibility() == 8) {
                            this.l.setVisibility(0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.l == null || this.l.getVisibility() != 0) {
                return;
            }
            this.l.setVisibility(8);
        } catch (Throwable th) {
            hn.c(th, "MapOverlayViewGroup", "redrawInfoWindow");
            th.printStackTrace();
        }
    }

    private void a(View view, int i, int i2) throws RemoteException {
        int i3;
        int i4;
        if (view == null) {
            return;
        }
        View view2 = this.l;
        if (view2 != null) {
            if (view == view2) {
                return;
            }
            view2.clearFocus();
            removeView(this.l);
        }
        this.l = view;
        ViewGroup.LayoutParams layoutParams = this.l.getLayoutParams();
        this.l.setDrawingCacheEnabled(true);
        this.l.setDrawingCacheQuality(0);
        this.m.getRect();
        if (layoutParams != null) {
            int i5 = layoutParams.width;
            i4 = layoutParams.height;
            i3 = i5;
        } else {
            i3 = -2;
            i4 = -2;
        }
        addView(this.l, new a(i3, i4, this.m.getGeoPosition(), i, i2, 81));
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public void hideInfoWindow() {
        IAMapDelegate iAMapDelegate = this.f870c;
        if (iAMapDelegate == null || iAMapDelegate.getMainHandler() == null) {
            return;
        }
        this.f870c.getMainHandler().post(new Runnable() { // from class: com.amap.api.mapcore.util.fj.2
            @Override // java.lang.Runnable
            public void run() {
                if (fj.this.l != null) {
                    fj.this.l.clearFocus();
                    fj fjVar = fj.this;
                    fjVar.removeView(fjVar.l);
                    er.a(fj.this.l.getBackground());
                    er.a(fj.this.n);
                    fj.this.l = null;
                }
            }
        });
        BaseOverlayImp baseOverlayImp = this.m;
        if (baseOverlayImp != null) {
            baseOverlayImp.setInfoWindowShown(false);
        }
        this.m = null;
    }

    private void a(View view, int i, int i2, int[] iArr) {
        View view2;
        if ((view instanceof ListView) && (view2 = (View) view.getParent()) != null) {
            iArr[0] = view2.getWidth();
            iArr[1] = view2.getHeight();
        }
        if (i <= 0 || i2 <= 0) {
            view.measure(0, 0);
        }
        if (i == -2) {
            iArr[0] = view.getMeasuredWidth();
        } else if (i == -1) {
            iArr[0] = getMeasuredWidth();
        } else {
            iArr[0] = i;
        }
        if (i2 == -2) {
            iArr[1] = view.getMeasuredHeight();
        } else if (i2 == -1) {
            iArr[1] = getMeasuredHeight();
        } else {
            iArr[1] = i2;
        }
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7 = i5 & 7;
        int i8 = i5 & 112;
        if (i7 == 5) {
            i3 -= i;
        } else if (i7 == 1) {
            i3 -= i / 2;
        }
        if (i8 == 80) {
            i4 -= i2;
        } else {
            if (i8 == 17) {
                i6 = i2 / 2;
            } else if (i8 == 16) {
                i4 /= 2;
                i6 = i2 / 2;
            }
            i4 -= i6;
        }
        view.layout(i3, i4, i3 + i, i4 + i2);
        if (view instanceof IGLSurfaceView) {
            this.f870c.changeSize(i, i2);
        }
    }

    public void i() {
        hideInfoWindow();
        er.a(this.n);
        h();
        removeAllViews();
        this.p = null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public boolean onInfoWindowTap(MotionEvent motionEvent) {
        View view = this.l;
        return (view == null || this.m == null || !er.a(new Rect(view.getLeft(), this.l.getTop(), this.l.getRight(), this.l.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY())) ? false : true;
    }

    public void a(Canvas canvas) {
        Bitmap drawingCache;
        View view = this.l;
        if (view == null || this.m == null || (drawingCache = view.getDrawingCache(true)) == null) {
            return;
        }
        canvas.drawBitmap(drawingCache, this.l.getLeft(), this.l.getTop(), new Paint());
    }

    public void a(fh.a aVar) {
        fh fhVar = this.j;
        if (fhVar == null) {
            this.f868a.a(this, aVar);
        } else {
            fhVar.a(aVar);
        }
    }

    public void k() {
        fg fgVar = this.f874g;
        if (fgVar == null) {
            this.f868a.a(this, new Object[0]);
        } else {
            fgVar.b();
        }
    }

    public void h(Boolean bool) {
        fi fiVar = this.f873f;
        if (fiVar == null) {
            this.f868a.a(this, bool);
        } else {
            fiVar.a(bool.booleanValue());
        }
    }

    public void i(Boolean bool) {
        fh fhVar = this.j;
        if (fhVar == null) {
            this.f868a.a(this, bool);
        } else {
            fhVar.a(bool.booleanValue());
        }
    }

    public void j(Boolean bool) {
        if (this.f872e == null) {
            this.f868a.a(this, bool);
        } else {
            bool.booleanValue();
            this.f872e.setVisibility(4);
        }
    }

    public void l() {
        Context context;
        if (!this.r || (context = this.f871d) == null) {
            return;
        }
        a(context);
        fk fkVar = this.f868a;
        if (fkVar != null) {
            fkVar.a();
        }
    }
}