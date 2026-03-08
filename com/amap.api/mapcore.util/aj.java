package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* JADX INFO: compiled from: AbstractTwoFingerGestureDetector.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class aj extends ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected float f137a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected float f138b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected float f139c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected float f140d;
    private final float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;

    public aj(Context context) {
        super(context);
        this.s = 0.0f;
        this.t = 0.0f;
        this.u = 0.0f;
        this.v = 0.0f;
        this.n = ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    @Override // com.amap.api.mapcore.util.ak
    protected void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        MotionEvent motionEvent2 = this.f143g;
        int pointerCount = this.f143g.getPointerCount();
        int pointerCount2 = motionEvent.getPointerCount();
        if (pointerCount2 == 2 && pointerCount2 == pointerCount) {
            this.q = -1.0f;
            this.r = -1.0f;
            float x = motionEvent2.getX(0);
            float y = motionEvent2.getY(0);
            float x2 = motionEvent2.getX(1);
            float y2 = motionEvent2.getY(1);
            this.f137a = x2 - x;
            this.f138b = y2 - y;
            float x3 = motionEvent.getX(0);
            float y3 = motionEvent.getY(0);
            float x4 = motionEvent.getX(1);
            float y4 = motionEvent.getY(1);
            this.f139c = x4 - x3;
            this.f140d = y4 - y3;
            this.s = x3 - x;
            this.t = y3 - y;
            this.u = x4 - x2;
            this.v = y4 - y2;
        }
    }

    public PointF a(int i) {
        if (i == 0) {
            return new PointF(this.s, this.t);
        }
        return new PointF(this.u, this.v);
    }

    protected static float a(MotionEvent motionEvent, int i, int i2) {
        float x = (i2 + motionEvent.getX()) - motionEvent.getRawX();
        if (i < motionEvent.getPointerCount()) {
            return motionEvent.getX(i) + x;
        }
        return 0.0f;
    }

    protected static float b(MotionEvent motionEvent, int i, int i2) {
        float y = (i2 + motionEvent.getY()) - motionEvent.getRawY();
        if (i < motionEvent.getPointerCount()) {
            return motionEvent.getY(i) + y;
        }
        return 0.0f;
    }

    protected boolean c(MotionEvent motionEvent, int i, int i2) {
        if (this.l == 0 || this.m == 0) {
            DisplayMetrics displayMetrics = this.f141e.getResources().getDisplayMetrics();
            this.o = displayMetrics.widthPixels - this.n;
            this.p = displayMetrics.heightPixels - this.n;
        } else {
            this.o = this.l - this.n;
            this.p = this.m - this.n;
        }
        float f2 = this.n;
        float f3 = this.o;
        float f4 = this.p;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        float fA = a(motionEvent, 1, i);
        float fB = b(motionEvent, 1, i2);
        boolean z = rawX < f2 || rawY < f2 || rawX > f3 || rawY > f4;
        boolean z2 = fA < f2 || fB < f2 || fA > f3 || fB > f4;
        return (z && z2) || z || z2;
    }
}