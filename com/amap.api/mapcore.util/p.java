package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.amap.api.mapcore.util.al;
import com.amap.api.mapcore.util.am;
import com.amap.api.mapcore.util.ao;
import com.amap.api.mapcore.util.ap;
import com.amap.api.maps.model.AMapGestureListener;
import com.autonavi.base.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.message.HoverGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.MoveGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.RotateGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.ScaleGestureMapMessage;

/* JADX INFO: compiled from: GlMapGestureDetector.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IAMapDelegate f1732a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f1733b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    GestureDetector f1734c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public AMapGestureListener f1735d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ao f1736e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private am f1737f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private al f1738g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ap f1739h;
    private int r;
    private int s;
    private boolean i = false;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;
    private Handler t = new Handler(Looper.getMainLooper());

    static /* synthetic */ int g(p pVar) {
        int i = pVar.k;
        pVar.k = i + 1;
        return i;
    }

    static /* synthetic */ int h(p pVar) {
        int i = pVar.l;
        pVar.l = i + 1;
        return i;
    }

    static /* synthetic */ int l(p pVar) {
        int i = pVar.j;
        pVar.j = i + 1;
        return i;
    }

    static /* synthetic */ int m(p pVar) {
        int i = pVar.m;
        pVar.m = i + 1;
        return i;
    }

    public void a(AMapGestureListener aMapGestureListener) {
        this.f1735d = aMapGestureListener;
    }

    public void a() {
        this.j = 0;
        this.l = 0;
        this.k = 0;
        this.m = 0;
        this.n = 0;
    }

    public p(IAMapDelegate iAMapDelegate) {
        this.f1733b = iAMapDelegate.getContext();
        this.f1732a = iAMapDelegate;
        a aVar = new a();
        this.f1734c = new GestureDetector(this.f1733b, aVar, this.t);
        this.f1734c.setOnDoubleTapListener(aVar);
        this.f1736e = new ao(this.f1733b, new d());
        this.f1737f = new am(this.f1733b, new c());
        this.f1738g = new al(this.f1733b, new b());
        this.f1739h = new ap(this.f1733b, new e());
    }

    public void a(int i, int i2) {
        this.r = i;
        this.s = i2;
        ao aoVar = this.f1736e;
        if (aoVar != null) {
            aoVar.a(i, i2);
        }
        am amVar = this.f1737f;
        if (amVar != null) {
            amVar.a(i, i2);
        }
        al alVar = this.f1738g;
        if (alVar != null) {
            alVar.a(i, i2);
        }
        ap apVar = this.f1739h;
        if (apVar != null) {
            apVar.a(i, i2);
        }
    }

    public int b() {
        return this.r;
    }

    public int c() {
        return this.s;
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.n < motionEvent.getPointerCount()) {
            this.n = motionEvent.getPointerCount();
        }
        if ((motionEvent.getAction() & 255) == 0) {
            this.p = false;
            this.q = false;
        }
        if (motionEvent.getAction() == 6 && motionEvent.getPointerCount() > 0) {
            this.p = true;
        }
        if (this.o && this.n >= 2) {
            this.o = false;
        }
        try {
            int[] iArr = {0, 0};
            if (this.f1732a != null && this.f1732a.getGLMapView() != null) {
                this.f1732a.getGLMapView().getLocationOnScreen(iArr);
            }
            if (this.f1735d != null) {
                if (motionEvent.getAction() == 0) {
                    this.f1735d.onDown(motionEvent.getX(), motionEvent.getY());
                } else if (motionEvent.getAction() == 1) {
                    this.f1735d.onUp(motionEvent.getX(), motionEvent.getY());
                }
            }
            this.f1734c.onTouchEvent(motionEvent);
            boolean zD = this.f1738g.d(motionEvent, iArr[0], iArr[1]);
            if (this.i && this.m > 0) {
                return zD;
            }
            this.f1739h.d(motionEvent, iArr[0], iArr[1]);
            if (this.o) {
                return zD;
            }
            this.f1736e.a(motionEvent);
            return this.f1737f.d(motionEvent, iArr[0], iArr[1]);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void d() {
        Handler handler = this.t;
        if (handler != null) {
            handler.removeCallbacks(null);
            this.t = null;
        }
    }

    /* JADX INFO: compiled from: GlMapGestureDetector.java */
    private class a implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1740a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f1741b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f1743d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private EAMapPlatformGestureInfo f1744e;

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        private a() {
            this.f1743d = 0;
            this.f1740a = 0.0f;
            this.f1744e = new EAMapPlatformGestureInfo();
            this.f1741b = 0L;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            p.this.o = false;
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (p.this.f1735d != null) {
                p.this.f1735d.onFling(f2, f3);
            }
            try {
                if (p.this.f1732a.getUiSettings().isScrollGesturesEnabled() && p.this.m <= 0 && p.this.k <= 0 && p.this.l == 0 && !p.this.q) {
                    EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1744e;
                    eAMapPlatformGestureInfo.mGestureState = 3;
                    eAMapPlatformGestureInfo.mGestureType = 3;
                    eAMapPlatformGestureInfo.mLocation = new float[]{motionEvent2.getX(), motionEvent2.getY()};
                    int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1744e);
                    p.this.f1732a.onFling();
                    p.this.f1732a.getGLMapEngine().startMapSlidAnim(engineIDWithGestureInfo, new Point((int) motionEvent2.getX(), (int) motionEvent2.getY()), f2, f3);
                }
                return true;
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onFling");
                th.printStackTrace();
                return true;
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (p.this.n == 1) {
                EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1744e;
                eAMapPlatformGestureInfo.mGestureState = 3;
                eAMapPlatformGestureInfo.mGestureType = 7;
                eAMapPlatformGestureInfo.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                p.this.f1732a.onLongPress(p.this.f1732a.getEngineIDWithGestureInfo(this.f1744e), motionEvent);
                if (p.this.f1735d != null) {
                    p.this.f1735d.onLongPress(motionEvent.getX(), motionEvent.getY());
                }
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (p.this.f1735d == null) {
                return false;
            }
            p.this.f1735d.onScroll(f2, f3);
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
            try {
                this.f1744e.mGestureState = 3;
                this.f1744e.mGestureType = 7;
                this.f1744e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                p.this.f1732a.getGLMapEngine().clearAnimations(p.this.f1732a.getEngineIDWithGestureInfo(this.f1744e), false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            p.this.f1734c.setIsLongpressEnabled(false);
            this.f1743d = motionEvent.getPointerCount();
            if (p.this.f1735d != null) {
                p.this.f1735d.onDoubleTap(motionEvent.getX(), motionEvent.getY());
            }
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (this.f1743d < motionEvent.getPointerCount()) {
                this.f1743d = motionEvent.getPointerCount();
            }
            int action = motionEvent.getAction() & 255;
            if (this.f1743d != 1) {
                return false;
            }
            try {
                if (!p.this.f1732a.getUiSettings().isZoomGesturesEnabled()) {
                    return false;
                }
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onDoubleTapEvent");
                th.printStackTrace();
            }
            if (action == 0) {
                EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1744e;
                eAMapPlatformGestureInfo.mGestureState = 1;
                eAMapPlatformGestureInfo.mGestureType = 9;
                eAMapPlatformGestureInfo.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1744e);
                this.f1740a = motionEvent.getY();
                p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, ScaleGestureMapMessage.obtain(100, 1.0f, 0, 0));
                this.f1741b = SystemClock.uptimeMillis();
                return true;
            }
            if (action == 2) {
                p.this.o = true;
                float y = this.f1740a - motionEvent.getY();
                if (Math.abs(y) < 20) {
                    return true;
                }
                EAMapPlatformGestureInfo eAMapPlatformGestureInfo2 = this.f1744e;
                eAMapPlatformGestureInfo2.mGestureState = 2;
                eAMapPlatformGestureInfo2.mGestureType = 9;
                eAMapPlatformGestureInfo2.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                int engineIDWithGestureInfo2 = p.this.f1732a.getEngineIDWithGestureInfo(this.f1744e);
                float mapHeight = (4.0f * y) / p.this.f1732a.getMapHeight();
                if (y > 0.0f) {
                    p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo2, ScaleGestureMapMessage.obtain(101, mapHeight, 0, 0));
                } else {
                    p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo2, ScaleGestureMapMessage.obtain(101, mapHeight, 0, 0));
                }
                this.f1740a = motionEvent.getY();
                return true;
            }
            EAMapPlatformGestureInfo eAMapPlatformGestureInfo3 = this.f1744e;
            eAMapPlatformGestureInfo3.mGestureState = 3;
            eAMapPlatformGestureInfo3.mGestureType = 9;
            eAMapPlatformGestureInfo3.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
            int engineIDWithGestureInfo3 = p.this.f1732a.getEngineIDWithGestureInfo(this.f1744e);
            p.this.f1734c.setIsLongpressEnabled(true);
            p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo3, ScaleGestureMapMessage.obtain(102, 1.0f, 0, 0));
            if (action != 1) {
                p.this.o = false;
                return true;
            }
            p.this.f1732a.setGestureStatus(engineIDWithGestureInfo3, 3);
            long jUptimeMillis = SystemClock.uptimeMillis() - this.f1741b;
            if (p.this.o && jUptimeMillis >= 200) {
                p.this.o = false;
                return true;
            }
            return p.this.f1732a.onDoubleTap(engineIDWithGestureInfo3, motionEvent);
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (p.this.n != 1) {
                return false;
            }
            EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1744e;
            eAMapPlatformGestureInfo.mGestureState = 3;
            eAMapPlatformGestureInfo.mGestureType = 8;
            eAMapPlatformGestureInfo.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
            int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1744e);
            if (p.this.f1735d != null) {
                try {
                    p.this.f1735d.onSingleTap(motionEvent.getX(), motionEvent.getY());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return p.this.f1732a.onSingleTapConfirmed(engineIDWithGestureInfo, motionEvent);
        }
    }

    /* JADX INFO: compiled from: GlMapGestureDetector.java */
    private class d extends ao.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1750b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1751c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1752d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Point f1753e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float[] f1754f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private float f1755g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private float[] f1756h;
        private float i;
        private EAMapPlatformGestureInfo j;

        private d() {
            this.f1750b = false;
            this.f1751c = false;
            this.f1752d = false;
            this.f1753e = new Point();
            this.f1754f = new float[10];
            this.f1755g = 0.0f;
            this.f1756h = new float[10];
            this.i = 0.0f;
            this.j = new EAMapPlatformGestureInfo();
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x00a7 A[Catch: all -> 0x0112, TRY_LEAVE, TryCatch #2 {all -> 0x0112, blocks: (B:8:0x0080, B:10:0x008e, B:19:0x00a3, B:21:0x00a7), top: B:85:0x0080 }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00c7  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x010f  */
        @Override // com.amap.api.mapcore.util.ao.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(com.amap.api.mapcore.util.ao r18) {
            /*
                Method dump skipped, instruction units count: 429
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.p.d.a(com.amap.api.mapcore.util.ao):boolean");
        }

        @Override // com.amap.api.mapcore.util.ao.a
        public boolean b(ao aoVar) {
            EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.j;
            eAMapPlatformGestureInfo.mGestureState = 1;
            eAMapPlatformGestureInfo.mGestureType = 4;
            eAMapPlatformGestureInfo.mLocation = new float[]{aoVar.a().getX(), aoVar.a().getY()};
            int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.j);
            int iB = (int) aoVar.b();
            int iC = (int) aoVar.c();
            this.f1752d = false;
            Point point = this.f1753e;
            point.x = iB;
            point.y = iC;
            this.f1750b = false;
            this.f1751c = false;
            p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, ScaleGestureMapMessage.obtain(100, 1.0f, iB, iC));
            try {
                if (p.this.f1732a.getUiSettings().isRotateGesturesEnabled() && !p.this.f1732a.isLockMapAngle(engineIDWithGestureInfo)) {
                    p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, RotateGestureMapMessage.obtain(100, p.this.f1732a.getMapAngle(engineIDWithGestureInfo), iB, iC));
                }
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onScaleRotateBegin");
                th.printStackTrace();
            }
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:51:0x0127  */
        @Override // com.amap.api.mapcore.util.ao.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void c(com.amap.api.mapcore.util.ao r12) {
            /*
                Method dump skipped, instruction units count: 329
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.p.d.c(com.amap.api.mapcore.util.ao):void");
        }
    }

    /* JADX INFO: compiled from: GlMapGestureDetector.java */
    private class c implements am.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private EAMapPlatformGestureInfo f1748b;

        private c() {
            this.f1748b = new EAMapPlatformGestureInfo();
        }

        @Override // com.amap.api.mapcore.util.am.a
        public boolean a(am amVar) {
            if (p.this.i) {
                return true;
            }
            try {
                if (p.this.f1732a.getUiSettings().isScrollGesturesEnabled()) {
                    if (!p.this.p) {
                        EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1748b;
                        eAMapPlatformGestureInfo.mGestureState = 2;
                        eAMapPlatformGestureInfo.mGestureType = 3;
                        eAMapPlatformGestureInfo.mLocation = new float[]{amVar.c().getX(), amVar.c().getY()};
                        int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1748b);
                        PointF pointFD = amVar.d();
                        float f2 = p.this.j == 0 ? 4.0f : 1.0f;
                        if (Math.abs(pointFD.x) <= f2 && Math.abs(pointFD.y) <= f2) {
                            return false;
                        }
                        if (p.this.j == 0) {
                            p.this.f1732a.getGLMapEngine().clearAnimations(engineIDWithGestureInfo, false);
                        }
                        p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, MoveGestureMapMessage.obtain(101, pointFD.x, pointFD.y));
                        p.l(p.this);
                    }
                }
                return true;
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onMove");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.mapcore.util.am.a
        public boolean b(am amVar) {
            try {
                if (!p.this.f1732a.getUiSettings().isScrollGesturesEnabled()) {
                    return true;
                }
                EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1748b;
                eAMapPlatformGestureInfo.mGestureState = 1;
                eAMapPlatformGestureInfo.mGestureType = 3;
                eAMapPlatformGestureInfo.mLocation = new float[]{amVar.c().getX(), amVar.c().getY()};
                p.this.f1732a.addGestureMapMessage(p.this.f1732a.getEngineIDWithGestureInfo(this.f1748b), MoveGestureMapMessage.obtain(100, 0.0f, 0.0f));
                return true;
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onMoveBegin");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.mapcore.util.am.a
        public void c(am amVar) {
            try {
                if (p.this.f1732a.getUiSettings().isScrollGesturesEnabled()) {
                    EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1748b;
                    eAMapPlatformGestureInfo.mGestureState = 3;
                    eAMapPlatformGestureInfo.mGestureType = 3;
                    eAMapPlatformGestureInfo.mLocation = new float[]{amVar.c().getX(), amVar.c().getY()};
                    int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1748b);
                    if (p.this.j > 0) {
                        p.this.f1732a.setGestureStatus(engineIDWithGestureInfo, 5);
                    }
                    p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, MoveGestureMapMessage.obtain(102, 0.0f, 0.0f));
                }
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onMoveEnd");
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: GlMapGestureDetector.java */
    private class b implements al.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private EAMapPlatformGestureInfo f1746b;

        private b() {
            this.f1746b = new EAMapPlatformGestureInfo();
        }

        @Override // com.amap.api.mapcore.util.al.a
        public boolean a(al alVar) {
            EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1746b;
            eAMapPlatformGestureInfo.mGestureState = 2;
            eAMapPlatformGestureInfo.mGestureType = 6;
            boolean z = false;
            eAMapPlatformGestureInfo.mLocation = new float[]{alVar.c().getX(), alVar.c().getY()};
            try {
                if (!p.this.f1732a.getUiSettings().isTiltGesturesEnabled()) {
                    return true;
                }
                int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1746b);
                if (p.this.f1732a.isLockMapCameraDegree(engineIDWithGestureInfo) || p.this.l > 3) {
                    return false;
                }
                float f2 = alVar.d().x;
                float f3 = alVar.d().y;
                if (!p.this.i) {
                    PointF pointFA = alVar.a(0);
                    PointF pointFA2 = alVar.a(1);
                    if ((pointFA.y > 10.0f && pointFA2.y > 10.0f) || (pointFA.y < -10.0f && pointFA2.y < -10.0f)) {
                        z = true;
                    }
                    if (z) {
                        float f4 = 10;
                        if (Math.abs(f3) > f4 && Math.abs(f2) < f4) {
                            p.this.i = true;
                        }
                    }
                }
                if (p.this.i) {
                    p.this.i = true;
                    float f5 = f3 / 6.0f;
                    if (Math.abs(f5) > 1.0f) {
                        p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, HoverGestureMapMessage.obtain(101, f5));
                        p.m(p.this);
                    }
                }
                return true;
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onHove");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.mapcore.util.al.a
        public boolean b(al alVar) {
            EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1746b;
            eAMapPlatformGestureInfo.mGestureState = 1;
            eAMapPlatformGestureInfo.mGestureType = 6;
            eAMapPlatformGestureInfo.mLocation = new float[]{alVar.c().getX(), alVar.c().getY()};
            try {
                if (!p.this.f1732a.getUiSettings().isTiltGesturesEnabled()) {
                    return true;
                }
                int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1746b);
                if (p.this.f1732a.isLockMapCameraDegree(engineIDWithGestureInfo)) {
                    return false;
                }
                p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, HoverGestureMapMessage.obtain(100, p.this.f1732a.getCameraDegree(engineIDWithGestureInfo)));
                return true;
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onHoveBegin");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.mapcore.util.al.a
        public void c(al alVar) {
            EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1746b;
            eAMapPlatformGestureInfo.mGestureState = 3;
            eAMapPlatformGestureInfo.mGestureType = 6;
            eAMapPlatformGestureInfo.mLocation = new float[]{alVar.c().getX(), alVar.c().getY()};
            try {
                if (p.this.f1732a.getUiSettings().isTiltGesturesEnabled()) {
                    int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1746b);
                    if (p.this.f1732a.isLockMapCameraDegree(engineIDWithGestureInfo)) {
                        return;
                    }
                    if (p.this.f1732a.getCameraDegree(engineIDWithGestureInfo) >= 0.0f && p.this.m > 0) {
                        p.this.f1732a.setGestureStatus(engineIDWithGestureInfo, 7);
                    }
                    p.this.i = false;
                    p.this.f1732a.addGestureMapMessage(engineIDWithGestureInfo, HoverGestureMapMessage.obtain(102, p.this.f1732a.getCameraDegree(engineIDWithGestureInfo)));
                }
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onHoveEnd");
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: GlMapGestureDetector.java */
    private class e extends ap.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        EAMapPlatformGestureInfo f1757a;

        private e() {
            this.f1757a = new EAMapPlatformGestureInfo();
        }

        @Override // com.amap.api.mapcore.util.ap.b, com.amap.api.mapcore.util.ap.a
        public void a(ap apVar) {
            try {
                if (p.this.f1732a.getUiSettings().isZoomGesturesEnabled()) {
                    float f2 = 10;
                    if (Math.abs(apVar.d()) > f2 || Math.abs(apVar.e()) > f2 || apVar.b() >= 200) {
                        return;
                    }
                    p.this.q = true;
                    EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.f1757a;
                    eAMapPlatformGestureInfo.mGestureState = 2;
                    eAMapPlatformGestureInfo.mGestureType = 2;
                    eAMapPlatformGestureInfo.mLocation = new float[]{apVar.c().getX(), apVar.c().getY()};
                    int engineIDWithGestureInfo = p.this.f1732a.getEngineIDWithGestureInfo(this.f1757a);
                    p.this.f1732a.setGestureStatus(engineIDWithGestureInfo, 4);
                    p.this.f1732a.zoomOut(engineIDWithGestureInfo);
                }
            } catch (Throwable th) {
                hn.c(th, "GLMapGestrureDetector", "onZoomOut");
                th.printStackTrace();
            }
        }
    }
}