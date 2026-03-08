package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* JADX INFO: compiled from: ScaleGestureDetector.java */
/* JADX INFO: loaded from: classes.dex */
public class an {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f149a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f150b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f151c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private MotionEvent f152d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private MotionEvent f153e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f154f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f155g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f156h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private long q;
    private final float r;
    private float s;
    private float t;
    private boolean u;
    private boolean v;
    private int w;
    private int x;
    private boolean y;
    private int z = 0;
    private int A = 0;

    /* JADX INFO: compiled from: ScaleGestureDetector.java */
    public interface a {
        boolean a(an anVar);

        boolean b(an anVar);

        void c(an anVar);
    }

    public MotionEvent a() {
        return this.f153e;
    }

    public an(Context context, a aVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f149a = context;
        this.f150b = aVar;
        this.r = viewConfiguration.getScaledEdgeSlop();
    }

    public void a(int i, int i2) {
        this.z = i;
        this.A = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:165:0x02d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 921
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.an.a(android.view.MotionEvent):boolean");
    }

    private int a(MotionEvent motionEvent, int i, int i2) {
        int pointerCount = motionEvent.getPointerCount();
        int iFindPointerIndex = motionEvent.findPointerIndex(i);
        for (int i3 = 0; i3 < pointerCount; i3++) {
            if (i3 != i2 && i3 != iFindPointerIndex) {
                float f2 = this.r;
                float f3 = this.s;
                float f4 = this.t;
                float fA = a(motionEvent, i3);
                float fB = b(motionEvent, i3);
                if (fA >= f2 && fB >= f2 && fA <= f3 && fB <= f4) {
                    return i3;
                }
            }
        }
        return -1;
    }

    private static float a(MotionEvent motionEvent, int i) {
        if (i < 0) {
            return Float.MIN_VALUE;
        }
        if (i == 0) {
            return motionEvent.getRawX();
        }
        return motionEvent.getX(i) + (motionEvent.getRawX() - motionEvent.getX());
    }

    private static float b(MotionEvent motionEvent, int i) {
        if (i < 0) {
            return Float.MIN_VALUE;
        }
        if (i == 0) {
            return motionEvent.getRawY();
        }
        return motionEvent.getY(i) + (motionEvent.getRawY() - motionEvent.getY());
    }

    private void b(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.f153e;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        this.f153e = MotionEvent.obtain(motionEvent);
        this.l = -1.0f;
        this.m = -1.0f;
        this.n = -1.0f;
        MotionEvent motionEvent3 = this.f152d;
        int iFindPointerIndex = motionEvent3.findPointerIndex(this.w);
        int iFindPointerIndex2 = motionEvent3.findPointerIndex(this.x);
        int iFindPointerIndex3 = motionEvent.findPointerIndex(this.w);
        int iFindPointerIndex4 = motionEvent.findPointerIndex(this.x);
        if (iFindPointerIndex < 0 || iFindPointerIndex2 < 0 || iFindPointerIndex3 < 0 || iFindPointerIndex4 < 0) {
            this.v = true;
            if (this.f151c) {
                this.f150b.c(this);
                return;
            }
            return;
        }
        float x = motionEvent3.getX(iFindPointerIndex);
        float y = motionEvent3.getY(iFindPointerIndex);
        float x2 = motionEvent3.getX(iFindPointerIndex2);
        float y2 = motionEvent3.getY(iFindPointerIndex2);
        float x3 = motionEvent.getX(iFindPointerIndex3);
        float y3 = motionEvent.getY(iFindPointerIndex3);
        float x4 = motionEvent.getX(iFindPointerIndex4) - x3;
        float y4 = motionEvent.getY(iFindPointerIndex4) - y3;
        this.f156h = x2 - x;
        this.i = y2 - y;
        this.j = x4;
        this.k = y4;
        this.f154f = x3 + (x4 * 0.5f);
        this.f155g = y3 + (y4 * 0.5f);
        this.q = motionEvent.getEventTime() - motionEvent3.getEventTime();
        this.o = motionEvent.getPressure(iFindPointerIndex3) + motionEvent.getPressure(iFindPointerIndex4);
        this.p = motionEvent3.getPressure(iFindPointerIndex) + motionEvent3.getPressure(iFindPointerIndex2);
    }

    private void l() {
        MotionEvent motionEvent = this.f152d;
        if (motionEvent != null) {
            motionEvent.recycle();
            this.f152d = null;
        }
        MotionEvent motionEvent2 = this.f153e;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
            this.f153e = null;
        }
        this.u = false;
        this.f151c = false;
        this.w = -1;
        this.x = -1;
        this.v = false;
    }

    public float b() {
        return this.f154f;
    }

    public float c() {
        return this.f155g;
    }

    public float d() {
        if (this.l == -1.0f) {
            float f2 = this.j;
            float f3 = this.k;
            this.l = (float) Math.sqrt((f2 * f2) + (f3 * f3));
        }
        return this.l;
    }

    public float e() {
        return this.j;
    }

    public float f() {
        return this.k;
    }

    public float g() {
        if (this.m == -1.0f) {
            float f2 = this.f156h;
            float f3 = this.i;
            this.m = (float) Math.sqrt((f2 * f2) + (f3 * f3));
        }
        return this.m;
    }

    public float h() {
        return this.f156h;
    }

    public float i() {
        return this.i;
    }

    public float j() {
        if (this.n == -1.0f) {
            this.n = d() / g();
        }
        return this.n;
    }

    public long k() {
        return this.q;
    }
}