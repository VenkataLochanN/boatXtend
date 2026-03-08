package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.MapRenderer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class j extends GLSurfaceView implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, MapRenderer.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3583a = j.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3584b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MapRenderer f3585c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3586d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3587e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private GestureDetector f3588f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private e f3589g;

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f3590a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f3591b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f3592c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f3593d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f3594e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        float f3595f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float f3596g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        double f3597h;

        a() {
        }

        public String toString() {
            return "MultiTouch{x1=" + this.f3590a + ", x2=" + this.f3591b + ", y1=" + this.f3592c + ", y2=" + this.f3593d + ", mTwoTouch=" + this.f3594e + ", centerX=" + this.f3595f + ", centerY=" + this.f3596g + ", length=" + this.f3597h + '}';
        }
    }

    public j(Context context, z zVar, String str, int i) throws Throwable {
        super(context);
        if (context == null) {
            throw new RuntimeException("BDMapSDKException: when you create an mapview, the context can not be null");
        }
        setEGLContextClientVersion(2);
        this.f3588f = new GestureDetector(context, this);
        EnvironmentUtilities.initAppDirectory(context);
        if (this.f3589g == null) {
            this.f3589g = new e(context, str, i);
        }
        this.f3589g.a(context.hashCode());
        g();
        this.f3589g.a();
        this.f3589g.a(zVar);
        h();
        this.f3589g.a(this.f3584b);
        this.f3589g.f();
        setBackgroundColor(0);
    }

    private static boolean a(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        return egl10.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, new EGLConfig[100], 100, iArr) && iArr[0] > 0;
    }

    private void g() {
        try {
            if (a(8, 8, 8, 8, 24, 0)) {
                setEGLConfigChooser(8, 8, 8, 8, 24, 0);
            } else if (a(5, 6, 5, 0, 24, 0)) {
                setEGLConfigChooser(5, 6, 5, 0, 24, 0);
            } else {
                setEGLConfigChooser(true);
            }
        } catch (IllegalArgumentException unused) {
            setEGLConfigChooser(true);
        }
        this.f3585c = new MapRenderer(this, this);
        this.f3585c.a(this.f3589g.j);
        setRenderer(this.f3585c);
        setRenderMode(1);
    }

    private void h() {
        this.f3584b = new k(this);
    }

    public e a() {
        return this.f3589g;
    }

    public void a(float f2, float f3) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return;
        }
        this.f3589g.b(f2, f3);
    }

    public void a(int i) {
        int i2;
        if (this.f3589g == null) {
            return;
        }
        Message message = new Message();
        message.what = 50;
        message.obj = Long.valueOf(this.f3589g.j);
        boolean zQ = this.f3589g.q();
        if (i != 3) {
            i2 = zQ ? 1 : 0;
            this.f3584b.sendMessage(message);
        }
        message.arg1 = i2;
        this.f3584b.sendMessage(message);
    }

    public void a(String str, Rect rect) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return;
        }
        if (rect != null) {
            int i = rect.left;
            int i2 = this.f3587e < rect.bottom ? 0 : this.f3587e - rect.bottom;
            int iWidth = rect.width();
            int iHeight = rect.height();
            if (i < 0 || i2 < 0 || iWidth <= 0 || iHeight <= 0) {
                return;
            }
            if (iWidth > this.f3586d) {
                iWidth = Math.abs(rect.width()) - (rect.right - this.f3586d);
            }
            if (iHeight > this.f3587e) {
                iHeight = Math.abs(rect.height()) - (rect.bottom - this.f3587e);
            }
            if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                this.f3589g.i.a(str, (Bundle) null);
                requestRender();
                return;
            }
            this.f3586d = iWidth;
            this.f3587e = iHeight;
            Bundle bundle = new Bundle();
            bundle.putInt("x", i);
            bundle.putInt("y", i2);
            bundle.putInt("width", iWidth);
            bundle.putInt("height", iHeight);
            this.f3589g.i.a(str, bundle);
        } else {
            this.f3589g.i.a(str, (Bundle) null);
        }
        requestRender();
    }

    public boolean a(float f2, float f3, float f4, float f5) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return false;
        }
        return this.f3589g.a(f2, f3, f4, f5);
    }

    public void b() {
        e eVar = this.f3589g;
        if (eVar == null) {
            return;
        }
        eVar.u();
    }

    public void b(int i) {
        e eVar = this.f3589g;
        if (eVar != null) {
            if (eVar.f3567h != null) {
                for (l lVar : this.f3589g.f3567h) {
                    if (lVar != null) {
                        lVar.f();
                    }
                }
            }
            this.f3589g.b(this.f3584b);
            this.f3589g.b(i);
            this.f3589g = null;
        }
        Handler handler = this.f3584b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public boolean b(float f2, float f3) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return false;
        }
        return this.f3589g.d(f2, f3);
    }

    public void c() {
        e eVar = this.f3589g;
        if (eVar == null) {
            return;
        }
        eVar.v();
    }

    public boolean c(float f2, float f3) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return false;
        }
        return this.f3589g.c(f2, f3);
    }

    public void d() {
        getHolder().setFormat(-3);
        this.f3589g.i.s();
    }

    public boolean d(float f2, float f3) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return false;
        }
        return this.f3589g.c((int) f2, (int) f3);
    }

    public void e() {
        getHolder().setFormat(-1);
        this.f3589g.i.t();
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.MapRenderer.a
    public void f() {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null || !this.f3589g.k) {
            return true;
        }
        GeoPoint geoPointB = this.f3589g.b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (geoPointB != null) {
            if (this.f3589g.f3567h != null) {
                for (l lVar : this.f3589g.f3567h) {
                    if (lVar != null) {
                        lVar.b(geoPointB);
                    }
                }
            }
            if (this.f3589g.f3565f) {
                ab abVarE = this.f3589g.E();
                abVarE.f3518a += 1.0f;
                if (!this.f3589g.f3566g) {
                    abVarE.f3521d = geoPointB.getLongitudeE6();
                    abVarE.f3522e = geoPointB.getLatitudeE6();
                }
                BaiduMap.mapStatusReason |= 1;
                this.f3589g.a(abVarE, 300);
                e eVar2 = this.f3589g;
                e.m = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null || !this.f3589g.k) {
            return true;
        }
        if (!this.f3589g.f3564e) {
            return false;
        }
        float fSqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
        if (fSqrt <= 500.0f) {
            return false;
        }
        BaiduMap.mapStatusReason |= 1;
        this.f3589g.A();
        this.f3589g.a(34, (int) (fSqrt * 0.6f), ((int) motionEvent2.getX()) | (((int) motionEvent2.getY()) << 16));
        this.f3589g.M();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null || !this.f3589g.k) {
            return;
        }
        String strA = this.f3589g.i.a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f3589g.l);
        if (strA == null || strA.equals("")) {
            if (this.f3589g.f3567h != null) {
                for (l lVar : this.f3589g.f3567h) {
                    GeoPoint geoPointB = this.f3589g.b((int) motionEvent.getX(), (int) motionEvent.getY());
                    if (lVar != null) {
                        lVar.c(geoPointB);
                    }
                }
                return;
            }
            return;
        }
        if (this.f3589g.f3567h != null) {
            for (l lVar2 : this.f3589g.f3567h) {
                if (lVar2 != null) {
                    if (lVar2.b(strA)) {
                        this.f3589g.p = true;
                    } else {
                        lVar2.c(this.f3589g.b((int) motionEvent.getX(), (int) motionEvent.getY()));
                    }
                }
            }
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        super.onPause();
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return;
        }
        this.f3589g.i.c();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return;
        }
        if (this.f3589g.f3567h != null) {
            for (l lVar : this.f3589g.f3567h) {
                if (lVar != null) {
                    lVar.d();
                }
            }
        }
        this.f3589g.i.g();
        this.f3589g.i.d();
        this.f3589g.i.n();
        setRenderMode(1);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        JSONObject jSONObject;
        e eVar = this.f3589g;
        if (eVar != null && eVar.i != null && this.f3589g.k) {
            String strA = this.f3589g.i.a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f3589g.l);
            if (strA != null && !strA.equals("")) {
                try {
                    jSONObject = new JSONObject(strA);
                } catch (JSONException e2) {
                    e = e2;
                    jSONObject = null;
                }
                try {
                    jSONObject.put("px", (int) motionEvent.getX());
                    jSONObject.put("py", (int) motionEvent.getY());
                } catch (JSONException e3) {
                    e = e3;
                    e.printStackTrace();
                }
                if (this.f3589g.f3567h != null) {
                    for (l lVar : this.f3589g.f3567h) {
                        if (jSONObject != null && lVar != null) {
                            lVar.a(jSONObject.toString());
                        }
                    }
                }
            } else if (this.f3589g.f3567h != null) {
                for (l lVar2 : this.f3589g.f3567h) {
                    if (lVar2 != null) {
                        lVar2.a(this.f3589g.b((int) motionEvent.getX(), (int) motionEvent.getY()));
                    }
                }
            }
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return true;
        }
        super.onTouchEvent(motionEvent);
        if (this.f3589g.f3567h != null) {
            for (l lVar : this.f3589g.f3567h) {
                if (lVar != null) {
                    lVar.a(motionEvent);
                }
            }
        }
        if (this.f3588f.onTouchEvent(motionEvent)) {
            return true;
        }
        return this.f3589g.a(motionEvent);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        super.surfaceChanged(surfaceHolder, i, i2, i3);
        e eVar = this.f3589g;
        if (eVar == null || eVar.i == null) {
            return;
        }
        MapRenderer mapRenderer = this.f3585c;
        mapRenderer.f3508a = i2;
        mapRenderer.f3509b = i3;
        this.f3586d = i2;
        this.f3587e = i3;
        mapRenderer.f3510c = 0;
        ab abVarE = this.f3589g.E();
        if (abVarE.f3523f == 0 || abVarE.f3523f == -1 || abVarE.f3523f == (abVarE.j.left - abVarE.j.right) / 2) {
            abVarE.f3523f = -1;
        }
        if (abVarE.f3524g == 0 || abVarE.f3524g == -1 || abVarE.f3524g == (abVarE.j.bottom - abVarE.j.top) / 2) {
            abVarE.f3524g = -1;
        }
        abVarE.j.left = 0;
        abVarE.j.top = 0;
        abVarE.j.bottom = i3;
        abVarE.j.right = i2;
        this.f3589g.a(abVarE);
        this.f3589g.a(this.f3586d, this.f3587e);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        if (surfaceHolder == null || surfaceHolder.getSurface().isValid()) {
            return;
        }
        surfaceDestroyed(surfaceHolder);
    }
}